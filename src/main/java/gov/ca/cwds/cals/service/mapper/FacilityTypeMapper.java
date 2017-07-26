package gov.ca.cwds.cals.service.mapper;

import gov.ca.cwds.cals.persistence.model.lisfas.LisTableFile;
import gov.ca.cwds.cals.service.dto.FacilityTypeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author CWDS CALS API Team
 */
@Mapper(uses = TrailingSpacesRemovalPostMappingProcessor.class)
public interface FacilityTypeMapper {

    @Mapping(target = "code", source = "tblFacTypeCode")
    @Mapping(target = "description", source = "tblFacTypeDesc")
    FacilityTypeDTO toFacilityTypeDTO(LisTableFile lisTableFile);

    @Mapping(target = "code", source = "lgcId")
    @Mapping(target = "description", source = "shortDsc")
    FacilityTypeDTO toFacilityTypeDTO(gov.ca.cwds.cals.persistence.model.cms.FacilityType facilityType);

}
