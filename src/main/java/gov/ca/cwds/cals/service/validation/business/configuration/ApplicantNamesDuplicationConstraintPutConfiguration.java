package gov.ca.cwds.cals.service.validation.business.configuration;

import gov.ca.cwds.cals.Constants.BusinessRulesAgendaGroups;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aApplicant;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aForm;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantDTO;
import gov.ca.cwds.cals.service.validation.CalsSessionFactoryAware;
import gov.ca.cwds.cals.service.validation.RFA1aFormsDaoAware;
import gov.ca.cwds.cals.service.validation.business.parameters.BusinessValidationParameterObject;
import gov.ca.cwds.cals.service.validation.business.parameters.ThreeParametersRetrievingStrategy;
import java.util.Optional;
import org.hibernate.Hibernate;

/**
 * @author CWDS CALS API Team
 */
public final class ApplicantNamesDuplicationConstraintPutConfiguration
    implements DroolsValidationConfiguration<Object[]>, CalsSessionFactoryAware,
    RFA1aFormsDaoAware {

  public static final ApplicantNamesDuplicationConstraintPutConfiguration INSTANCE =
      new ApplicantNamesDuplicationConstraintPutConfiguration();

  private ApplicantNamesDuplicationConstraintPutConfiguration() {
  }

  @Override
  public String getAgendaGroup() {
    return BusinessRulesAgendaGroups.APPLICANT_NAMES_DUPLICATION_VALIDATION;
  }

  @Override
  public RFA1aForm getValidatedFact(Object[] input) {
    BusinessValidationParameterObject<ApplicantDTO> parameterObject =
        getBusinessValidationParameterObject(input);
    RFA1aForm form = getFormDao().find(parameterObject.getFormId());
    return buildModifiedForm(form, parameterObject.getEntityDTO());
  }

  public BusinessValidationParameterObject<ApplicantDTO> getBusinessValidationParameterObject(
      Object[] parameters) {
    return ThreeParametersRetrievingStrategy.INSTANCE
        .retrieveParameters(parameters, ApplicantDTO.class);
  }

  public RFA1aForm buildModifiedForm(RFA1aForm form, ApplicantDTO applicantDTO) {
    Hibernate.initialize(form.getApplicants());
    getCurrentSession().detach(form);
    Optional<RFA1aApplicant> foundApplicant = form.getApplicants().stream()
        .filter(applicant -> applicant.getId().equals(applicantDTO.getId()))
        .findAny();
    foundApplicant.ifPresent(applicant -> applicant.setApplicant(applicantDTO));
    return form;
  }

}
