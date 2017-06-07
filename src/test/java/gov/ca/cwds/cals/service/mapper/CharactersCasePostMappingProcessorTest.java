package gov.ca.cwds.cals.service.mapper;

import static org.junit.Assert.assertEquals;

import gov.ca.cwds.cals.service.dto.FacilityTypeDTO;
import java.util.Collections;
import java.util.Set;
import org.junit.Test;

/** @author CWDS CALS API Team */
public class CharactersCasePostMappingProcessorTest {

  private StringTransformMappingProcessor processor = new StringTransformMappingProcessor();

  @Test
  public void applyTest() throws Exception {

    FacilityTypeDTO facilityTypeDTO = new FacilityTypeDTO();
    facilityTypeDTO.setCode("123");
    facilityTypeDTO.setDescription("TEST TYPE");

    FacilityTypeDTO facilityTypeDTOExpected = new FacilityTypeDTO();
    facilityTypeDTOExpected.setCode("123");
    facilityTypeDTOExpected.setDescription("Test Type");

    processor.process(facilityTypeDTO);

    assertEquals(facilityTypeDTOExpected, facilityTypeDTO);
  }

  @Test
  public void processTest() {
    DeepObject insideCollectionDeepObject = new DeepObject();
    insideCollectionDeepObject.setString("insideCollectionDeepObject    ");
    Set<DeepObject> deepObjectList = Collections.singleton(insideCollectionDeepObject);

    DeepObject deepObject = new DeepObject();
    deepObject.setString("deepObject     ");
    deepObject.setDeepObjectsList(deepObjectList);
    RootObject rootObject = new RootObject();
    rootObject.setDeepObject(deepObject);
    rootObject.setInteger(1);
    rootObject.setNullString(null);
    rootObject.setRootString("rootString     ");

    processor.process(rootObject);

    assertEquals("DeepObject", deepObject.getString());
    assertEquals("RootString", rootObject.getRootString());
    assertEquals("InsideCollectionDeepObject", insideCollectionDeepObject.getString());
  }
}
