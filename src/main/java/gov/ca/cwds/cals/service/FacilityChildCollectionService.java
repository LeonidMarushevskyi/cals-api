package gov.ca.cwds.cals.service;

import com.google.inject.Inject;
import gov.ca.cwds.cals.persistence.dao.cms.IClientDao;
import gov.ca.cwds.cals.persistence.model.cms.BaseClient;
import gov.ca.cwds.cals.service.dto.FacilityChildDTO;
import gov.ca.cwds.cals.service.dto.FacilityChildrenDTO;
import gov.ca.cwds.cals.service.mapper.FacilityChildMapper;
import gov.ca.cwds.cals.web.rest.parameter.FacilityChildParameterObject;
import gov.ca.cwds.rest.api.Response;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;

import java.io.Serializable;
import java.util.List;

/**
 * @author CWDS CALS API Team
 */
public class FacilityChildCollectionService extends CrudServiceAdapter {

  private IClientDao<BaseClient> clientDao;
  private FacilityChildMapper facilityChildMapper;

  @Inject
  public FacilityChildCollectionService(IClientDao clientDao,
      FacilityChildMapper facilityChildMapper) {
    this.clientDao = clientDao;
    this.facilityChildMapper = facilityChildMapper;
  }

  @Override
  public Response find(Serializable params) {
    List<FacilityChildDTO> facilityChildDTOs = clientDao
        .stream((FacilityChildParameterObject) params)
        .map(client -> facilityChildMapper.toFacilityChildDTO(client)).collect(
            Collectors.toList());

    if (CollectionUtils.isEmpty(facilityChildDTOs)) {
      return null;
    } else {
      FacilityChildrenDTO facilityChildrenDTO = new FacilityChildrenDTO();
      facilityChildrenDTO.setChildren(facilityChildDTOs);
      return facilityChildrenDTO;
    }
  }
}
