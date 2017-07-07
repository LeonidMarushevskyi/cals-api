package gov.ca.cwds.cals.service.rfa.factory;

import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aOtherAdult;
import gov.ca.cwds.cals.service.dto.rfa.OtherAdultDTO;
import gov.ca.cwds.cals.service.dto.rfa.collection.OtherAdultCollectionDTO;
import java.util.List;

/**
 * @author CWDS CALS API Team
 */

public class OtherAdultFactory
    implements RFAExternalEntityFactory<RFA1aOtherAdult, OtherAdultDTO> {

  public static final RFAExternalEntityFactory<RFA1aOtherAdult, OtherAdultDTO>
      INSTANCE = new OtherAdultFactory();

  private OtherAdultFactory() {
  }

  @Override
  public OtherAdultDTO createEntityDTO() {
    return new OtherAdultDTO();
  }

  @Override
  public RFA1aOtherAdult createEntity() {
    return new RFA1aOtherAdult();
  }

  @Override
  public Class<RFA1aOtherAdult> getEntityClass() {
    return RFA1aOtherAdult.class;
  }

  @Override
  public String getFindAllByFormNamedQuery() {
    return RFA1aOtherAdult.NAMED_QUERY_FIND_ALL_BY_FORM;
  }

  @Override
  public String getFindByFormIdAndEntityIdNamedQuery() {
    return RFA1aOtherAdult.NAMED_QUERY_FIND_BY_FORM_ID_AND_CHILD_ID;
  }

  @Override
  public OtherAdultCollectionDTO createCollectionDTO(List<OtherAdultDTO> collectDTOs) {
    return new OtherAdultCollectionDTO(collectDTOs);
  }

}

