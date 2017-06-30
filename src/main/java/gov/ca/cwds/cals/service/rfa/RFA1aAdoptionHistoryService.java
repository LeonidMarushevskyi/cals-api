package gov.ca.cwds.cals.service.rfa;

import com.google.inject.Inject;
import gov.ca.cwds.cals.persistence.dao.calsns.RFA1aFormsDao;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.AdoptionHistory;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aForm;
import gov.ca.cwds.cals.service.rfa.factory.RFAInternalEntityConfiguration;

/**
 * @author CWDS CALS API Team
 */
public class RFA1aAdoptionHistoryService extends AbstractRFAInternalEntityService<AdoptionHistory> {

  @Inject
  public RFA1aAdoptionHistoryService(RFA1aFormsDao applicationDao) {

    super(
        applicationDao,
        new RFAInternalEntityConfiguration<AdoptionHistory>(AdoptionHistory.class) {

          @Override
          protected AdoptionHistory retrieveEntityFromTheForm(RFA1aForm form) {
            return form.getAdoptionHistory();
          }

          @Override
          protected void saveEntityToTheForm(RFA1aForm form, AdoptionHistory entity) {
            form.setAdoptionHistory(entity);
          }
        });
  }
}
