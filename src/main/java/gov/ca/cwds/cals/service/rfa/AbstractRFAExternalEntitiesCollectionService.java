package gov.ca.cwds.cals.service.rfa;

import gov.ca.cwds.cals.persistence.dao.calsns.RFAExternalEntityDao;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFAExternalEntity;
import gov.ca.cwds.cals.service.CrudServiceAdapter;
import gov.ca.cwds.cals.service.dto.rfa.RFAExternalEntityDTO;
import gov.ca.cwds.cals.service.rfa.factory.RFAExternalEntityFactory;
import gov.ca.cwds.cals.web.rest.parameter.RFAExternalEntityParameterObject;
import gov.ca.cwds.rest.api.Response;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author CWDS CALS API Team
 */
public class AbstractRFAExternalEntitiesCollectionService<
    T extends RFAExternalEntity<D>,
    D extends RFAExternalEntityDTO>
    extends CrudServiceAdapter {

  private final RFAExternalEntityFactory<T, D> factory;
  private RFAExternalEntityDao<T, D> dao;

  public AbstractRFAExternalEntitiesCollectionService(
      RFAExternalEntityDao<T, D> dao, RFAExternalEntityFactory<T, D> factory) {
    this.dao = dao;
    this.factory = factory;
  }

  @Override
  public Response find(Serializable params) {
    if (!(params instanceof RFAExternalEntityParameterObject)) {
      throw new IllegalStateException("RFA1aApplicantParameterObject is expected here");
    }
    Stream<T> entities =
        dao.findAllByFormId(((RFAExternalEntityParameterObject) params).getFormId());
    List<D> collectDTOs = entities.map(T::getEntityDTO).collect(Collectors.toList());
    return factory.createCollectionDTO(collectDTOs);
  }
}