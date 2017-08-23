package gov.ca.cwds.cals.persistence.dao.calsns;

import com.google.inject.Inject;
import gov.ca.cwds.cals.inject.CalsnsSessionFactory;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1cForm;
import gov.ca.cwds.cals.service.rfa.factory.RFA1cFactory;
import org.hibernate.SessionFactory;

/**
 * @author CWDS CALS API Team
 */

public class RFA1cDao extends RFAExternalEntityDao<RFA1cForm> {

  @Inject
  public RFA1cDao(@CalsnsSessionFactory SessionFactory sessionFactory) {
    super(sessionFactory, RFA1cFactory.INSTANCE);
  }

}
