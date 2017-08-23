package gov.ca.cwds.cals.service.rfa;

import static gov.ca.cwds.cals.Constants.SYSTEM_USER_ID;
import static gov.ca.cwds.cals.Constants.UnitOfWork.CALSNS;
import static gov.ca.cwds.cals.Constants.Validation.FORM_SUBMISSION_VALIDATION_SESSION;
import static gov.ca.cwds.cals.exception.ExpectedExceptionInfo.RFA_1A_APPLICATION_NOT_FOUND_BY_ID;
import static gov.ca.cwds.cals.exception.ExpectedExceptionInfo.TRANSITION_BACK_TO_DRAFT_IS_NOT_ALLOWED;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

import com.google.inject.Inject;
import gov.ca.cwds.cals.Constants.BusinessRulesAgendaGroups;
import gov.ca.cwds.cals.exception.BusinessValidationException;
import gov.ca.cwds.cals.exception.ExpectedException;
import gov.ca.cwds.cals.persistence.dao.calsns.RFA1aFormsDao;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aForm;
import gov.ca.cwds.cals.persistence.model.cms.legacy.PlacementHome;
import gov.ca.cwds.cals.service.FacilityService;
import gov.ca.cwds.cals.service.TypedCrudServiceAdapter;
import gov.ca.cwds.cals.service.dto.rfa.RFA1aFormDTO;
import gov.ca.cwds.cals.service.dto.rfa.RFAApplicationStatusDTO;
import gov.ca.cwds.cals.service.mapper.rfa.RFA1aFormMapper;
import gov.ca.cwds.cals.service.validation.business.DroolsService;
import gov.ca.cwds.cals.service.validation.business.configuration.DroolsFieldValidationConfiguration;
import gov.ca.cwds.cals.service.validation.business.configuration.DroolsValidationConfiguration;
import gov.ca.cwds.cals.web.rest.parameter.RFA1aFormsParameterObject;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.setup.Environment;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import javax.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author CWDS CALS API Team
 */
public class RFA1aFormService
    extends TypedCrudServiceAdapter<RFA1aFormsParameterObject, RFA1aFormDTO, RFA1aFormDTO> {

  private static final Logger LOG = LoggerFactory
      .getLogger(RFA1aFormService.class);

  @Inject
  private RFA1aFormsDao rfa1AFormsDao;

  @Inject
  private RFA1aFormMapper rfa1aFomMapper;

  @Inject
  private FacilityService facilityService;

  @Inject
  private DroolsService droolsService;

  @Inject
  private Environment environment;

  public RFA1aFormService() {
    // default constructor
  }

  @Override
  public RFA1aFormDTO create(RFA1aFormDTO formDTO) {

    RFA1aForm form = new RFA1aForm();
    rfa1aFomMapper.toRFA1aForm(form, formDTO);

    LocalDateTime now = LocalDateTime.now();
    form.setCreateDateTime(now);
    form.setCreateUserId(SYSTEM_USER_ID);
    form.setUpdateDateTime(now);
    form.setUpdateUserId(SYSTEM_USER_ID);
    form.setStatus(RFAApplicationStatus.DRAFT);
    form = rfa1AFormsDao.create(form);

    return rfa1aFomMapper.toRFA1aFormDTO(form);
  }

  @Override
  public RFA1aFormDTO find(RFA1aFormsParameterObject parameterObject) {
    RFA1aForm form = rfa1AFormsDao.find(parameterObject.getFormId());

    RFA1aFormDTO formDTO;
    if (parameterObject.isExpanded()) {
      formDTO = rfa1aFomMapper.toExpandedRFA1aFormDTO(form);
    } else {
      formDTO = rfa1aFomMapper.toRFA1aFormDTO(form);
    }

    return formDTO;
  }

  @Override
  public RFA1aFormDTO update(RFA1aFormsParameterObject parameterObject, RFA1aFormDTO formDTO) {
    RFA1aForm form = findFormById(
        parameterObject.getFormId());
    rfa1aFomMapper.toRFA1aForm(form, formDTO);
    updateForm(form);
    return rfa1aFomMapper.toRFA1aFormDTO(form);
  }

  private void updateForm(RFA1aForm form) {
    form.setUpdateDateTime(LocalDateTime.now());
    form.setUpdateUserId(SYSTEM_USER_ID);
    rfa1AFormsDao.update(form);
  }

  public RFAApplicationStatusDTO getApplicationStatus(Long formId) {
    RFA1aForm form = findFormById(formId);
    return new RFAApplicationStatusDTO(form.getStatus());
  }

  public void setApplicationStatus(Long formId, RFAApplicationStatusDTO statusDTO)
      throws BusinessValidationException {
    RFA1aForm form = findFormById(formId);
    RFAApplicationStatus newStatus = statusDTO.getStatus();
    if (form.getStatus() != newStatus) {
      if (form.getStatus() == RFAApplicationStatus.SUBMITTED
          && newStatus == RFAApplicationStatus.DRAFT) {
        throw new ExpectedException(TRANSITION_BACK_TO_DRAFT_IS_NOT_ALLOWED, BAD_REQUEST);
      }
      submitApplication(form, newStatus);
    }
  }

  private void submitApplication(RFA1aForm form, RFAApplicationStatus newStatus)
      throws BusinessValidationException {
    RFA1aFormDTO expandedFormDTO = rfa1aFomMapper.toExpandedRFA1aFormDTO(form);
    performSubmissionValidation(expandedFormDTO);

    PlacementHome storedPlacementHome = null;

    try {
      storedPlacementHome = facilityService.createPlacementHomeByRfaApplication(expandedFormDTO);
    } catch (Exception e) {
      LOG.error("Can not create Placement Home in database", e);
      throw e;
    }

    updateFormAfterPlacementHomeCreation(form.getId(), storedPlacementHome.getIdentifier(),
        newStatus);
  }


  @UnitOfWork(CALSNS)
  protected void updateFormAfterPlacementHomeCreation(
      Long formId, String placementHomeId, RFAApplicationStatus newStatus) {
    RFA1aForm form = rfa1AFormsDao.find(formId);
    form.setStatus(newStatus);
    form.setPlacementHomeId(placementHomeId);
    updateForm(form);
  }

  private void performSubmissionValidation(
      RFA1aFormDTO formDTO) throws BusinessValidationException {
    Validator validator = environment.getValidator();
    Set<String> validationMessages = new HashSet<>();
    Optional.ofNullable(validator.validate(formDTO))
        .ifPresent(violations -> violations
            .forEach(violation -> validationMessages.add(violation.getMessage())
            ));
    validationMessages.addAll(droolsService.validate(formDTO, createConfiguration()));
    if (!validationMessages.isEmpty()) {
      throw new BusinessValidationException(new ArrayList<>(validationMessages));
    }
  }

  private DroolsValidationConfiguration<RFA1aFormDTO> createConfiguration() {
    return new DroolsFieldValidationConfiguration<RFA1aFormDTO>() {

      @Override
      public String getDroolsSessionName() {
        return FORM_SUBMISSION_VALIDATION_SESSION;
      }

      @Override
      public String getAgendaGroup() {
        return BusinessRulesAgendaGroups.FORM_SUBMISSION_VALIDATION;
      }
    };
  }

  private RFA1aForm findFormById(Long formId) {
    RFA1aForm form = rfa1AFormsDao.find(formId);
    if (form == null) {
      throw new ExpectedException(RFA_1A_APPLICATION_NOT_FOUND_BY_ID, NOT_FOUND);
    }
    return form;
  }

}
