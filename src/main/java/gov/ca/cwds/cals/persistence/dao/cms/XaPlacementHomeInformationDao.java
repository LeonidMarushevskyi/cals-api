package gov.ca.cwds.cals.persistence.dao.cms;

import com.google.inject.Inject;
import gov.ca.cwds.cals.inject.XaCmsSessionFactory;
import gov.ca.cwds.cals.persistence.model.cms.PlacementHomeInformation;
import gov.ca.cwds.data.BaseDaoImpl;
import org.hibernate.SessionFactory;

/**
 * @author CWDS CALS API Team
 */
public class XaPlacementHomeInformationDao extends BaseDaoImpl<PlacementHomeInformation> {

  @Inject
  public XaPlacementHomeInformationDao(@XaCmsSessionFactory SessionFactory sessionFactory) {
    super(sessionFactory);
  }
}
