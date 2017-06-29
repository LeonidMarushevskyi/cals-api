package gov.ca.cwds.cals.service.rfa.configuration;

import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFABaseEntity;
import gov.ca.cwds.cals.service.dto.BaseDTO;
import gov.ca.cwds.cals.service.dto.CollectionDTO;
import java.util.List;

/**
 * @author CWDS CALS API Team
 */
@SuppressWarnings("squid:S00119")
public interface RFAExternalEntityConfiguration<
    Entity extends RFABaseEntity,
    EntityDTO extends BaseDTO,
    EntitiesDTO extends CollectionDTO<EntityDTO>> {

  EntityDTO createEntityDTO();

  Entity createEntity();

  EntitiesDTO createEntitiesDTO(List<EntityDTO> collectDTOs);
}
