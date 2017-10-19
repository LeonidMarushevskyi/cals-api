package gov.ca.cwds.cals.persistence.dao.cms;

import com.google.inject.Inject;
import gov.ca.cwds.cals.persistence.model.cms.SubstituteCareProviderUc;
import gov.ca.cwds.data.BaseDaoImpl;
import gov.ca.cwds.inject.CmsSessionFactory;
import org.hibernate.SessionFactory;

/**
 * @author CWDS CALS API Team
 */
public class XaSubstituteCareProviderUCDao extends BaseDaoImpl<SubstituteCareProviderUc> {

  @Inject
  public XaSubstituteCareProviderUCDao(@CmsSessionFactory SessionFactory sessionFactory) {
    super(sessionFactory);
  }
}