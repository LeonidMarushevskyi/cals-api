package gov.ca.cwds.cals.persistence.dao.cms.legacy;

import com.google.common.collect.ImmutableList;
import com.google.inject.Inject;
import gov.ca.cwds.cals.persistence.dao.cms.IClientDao;
import gov.ca.cwds.cals.persistence.model.cms.legacy.Client;
import gov.ca.cwds.cals.web.rest.parameter.FacilityChildParameterObject;
import gov.ca.cwds.data.BaseDaoImpl;
import gov.ca.cwds.inject.CmsSessionFactory;
import java.util.Collection;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.NoResultException;

/** @author CWDS CALS API Team */
public class ClientDao extends BaseDaoImpl<Client> implements IClientDao<Client> {
  private static final Logger LOG = LoggerFactory.getLogger(ClientDao.class);

  @Inject
  public ClientDao(@CmsSessionFactory SessionFactory sessionFactory) {
    super(sessionFactory);
  }

  public Client findByParameterObject(FacilityChildParameterObject parameterObject) {
    Session session = getSessionFactory().getCurrentSession();
    Class<Client> entityClass = getEntityClass();
    Query<Client> query =
        session.createNamedQuery(entityClass.getSimpleName() + ".find", entityClass);

    String licenseNumber = parameterObject.getLicenseNumber();
    query.setParameter("licenseNumber", licenseNumber);

    String childId = parameterObject.getChildId();
    query.setParameter("childId", childId);

    query.setMaxResults(1);

    Client client = null;
    try {
      client = query.getSingleResult();
    } catch (NoResultException e) {
      LOG.warn(
          "There is no result for licenseNumber = {} and childId = {}", licenseNumber, childId);
      LOG.debug(e.getMessage(), e);
    }

    return client;
  }

  public Collection<Client> findCollection(FacilityChildParameterObject parameterObject) {
    Session session = getSessionFactory().getCurrentSession();
    Class<Client> entityClass = getEntityClass();
    Query<Client> query =
        session.createNamedQuery(entityClass.getSimpleName() + ".findAll", entityClass);
    query.setParameter("licenseNumber", parameterObject.getLicenseNumber());
    ImmutableList.Builder<Client> entities = new ImmutableList.Builder<>();
    entities.addAll(query.list());
    return entities.build();
  }
}