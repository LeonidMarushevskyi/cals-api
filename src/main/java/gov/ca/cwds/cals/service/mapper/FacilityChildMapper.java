package gov.ca.cwds.cals.service.mapper;


import gov.ca.cwds.cals.model.cms.Client;
import gov.ca.cwds.cals.model.cms.County;
import gov.ca.cwds.cals.model.cms.OutOfHomePlacement;
import gov.ca.cwds.cals.model.cms.PlacementEpisode;
import gov.ca.cwds.cals.model.cms.StaffPerson;
import gov.ca.cwds.cals.service.dto.FacilityChildDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

/**
 *
 *  @author CWDS CALS API Team
 *
 */
@Mapper(uses = {PersonMapper.class})
public interface FacilityChildMapper {

    FacilityChildMapper INSTANCE = Mappers.getMapper(FacilityChildMapper.class);

    List<FacilityChildDTO> toFacilityChildDTO(List<Client> clients);

    @Mapping(target = "id", source = "client.identifier")
    @Mapping(target = "person", source = "client")
    @Mapping(target = "dateOfPlacement", ignore = true)
    @Mapping(target = "countyOfOrigin", ignore = true)
    @Mapping(target = "assignedWorker", ignore = true)
    @Mapping(target = "facilityId", ignore = true)
    @Mapping(target = "messages", ignore = true)
    FacilityChildDTO toFacilityChildDTO(Client client);

    @Mapping(target = "assignedWorker", source = "staffPerson")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "person", ignore = true)
    @Mapping(target = "dateOfPlacement", ignore = true)
    @Mapping(target = "countyOfOrigin", ignore = true)
    @Mapping(target = "facilityId", ignore = true)
    @Mapping(target = "messages", ignore = true)
    FacilityChildDTO toFacilityChildDTO(@MappingTarget FacilityChildDTO facilityChildDTO, StaffPerson staffPerson);

    @Mapping(target = "countyOfOrigin", source = "shortDsc")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "person", ignore = true)
    @Mapping(target = "dateOfPlacement", ignore = true)
    @Mapping(target = "assignedWorker", ignore = true)
    @Mapping(target = "facilityId", ignore = true)
    @Mapping(target = "messages", ignore = true)
    FacilityChildDTO toFacilityChildDTO(@MappingTarget FacilityChildDTO facilityChildDTO, County county);

    @Mapping(target = "dateOfPlacement", source = "startDt")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "person", ignore = true)
    @Mapping(target = "countyOfOrigin", ignore = true)
    @Mapping(target = "assignedWorker", ignore = true)
    //todo: should we? @Mapping(target = "facilityId", source = "placementHome.identifier")
    @Mapping(target = "facilityId", ignore = true)
    @Mapping(target = "messages", ignore = true)
    FacilityChildDTO toFacilityChildDTO(@MappingTarget FacilityChildDTO facilityChildDTO, OutOfHomePlacement outOfHomePlacement);

    @AfterMapping
    default void after(@MappingTarget FacilityChildDTO facilityChildDTO, Client client) {
        Set<PlacementEpisode> placementEpisodes = client.getPlacementEpisodes();
        if (!placementEpisodes.isEmpty()) {
            //todo: consider how to choose correct placement episode from a set
            PlacementEpisode placementEpisode = placementEpisodes.iterator().next();
            County county = placementEpisode.getCounty();
            INSTANCE.toFacilityChildDTO(facilityChildDTO, county);

            StaffPerson staffPerson = placementEpisode.getStaffPerson();
            INSTANCE.toFacilityChildDTO(facilityChildDTO, staffPerson);

            Set<OutOfHomePlacement> outOfHomePlacements = placementEpisode.getOutOfHomePlacements();
            if (!outOfHomePlacements.isEmpty()) {
                //todo: consider how to choose correct out of home placement from a set
                OutOfHomePlacement outOfHomePlacement = outOfHomePlacements.iterator().next();
                INSTANCE.toFacilityChildDTO(facilityChildDTO, outOfHomePlacement);
            }
        }
    }
}
