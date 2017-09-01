package gov.ca.cwds.cals.service.rfa;

import static gov.ca.cwds.cals.exception.ExpectedExceptionInfo.RFA_1A_APPLICATION_NOT_FOUND_BY_ID;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

import gov.ca.cwds.cals.RequestResponse;
import gov.ca.cwds.cals.Utils;
import gov.ca.cwds.cals.exception.ExpectedException;
import gov.ca.cwds.cals.persistence.dao.calsns.RFA1aFormsDao;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aForm;
import gov.ca.cwds.cals.service.TypedCrudServiceAdapter;
import gov.ca.cwds.cals.service.rfa.factory.RFAInternalEntityConfiguration;
import java.time.LocalDateTime;

/**
 * @author CWDS CALS API Team.
 */
public abstract class AbstractRFAInternalEntityService<T extends RequestResponse> extends
    TypedCrudServiceAdapter<Long, T, T> {

  private final RFA1aFormsDao applicationDao;
  private RFAInternalEntityConfiguration<T> configuration;

  public AbstractRFAInternalEntityService(RFA1aFormsDao applicationDao,
      RFAInternalEntityConfiguration<T> configuration) {
    this.applicationDao = applicationDao;
    this.configuration = configuration;
  }

  @Override
  public T find(Long applicationId) {
    RFA1aForm form = applicationDao.find(applicationId);
    if (form == null) {
      throw new ExpectedException(RFA_1A_APPLICATION_NOT_FOUND_BY_ID, NOT_FOUND);
    }
    return configuration.getEntityFromTheForm(form);
  }

  @Override
  public T update(Long applicationId, T request) {
    RFA1aForm form = applicationDao.find(applicationId);
    if (form == null) {
      throw new ExpectedException(RFA_1A_APPLICATION_NOT_FOUND_BY_ID, NOT_FOUND);
    }
    form.setUpdateDateTime(LocalDateTime.now());
    form.setUpdateUserId(Utils.Id.getStaffPersonId());

    configuration.putEntityToTheForm(form, request);

    RFA1aForm updatedForm = applicationDao.update(form);
    return configuration.getEntityFromTheForm(updatedForm);
  }
}
