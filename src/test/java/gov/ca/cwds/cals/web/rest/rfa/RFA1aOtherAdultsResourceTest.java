package gov.ca.cwds.cals.web.rest.rfa;

import gov.ca.cwds.cals.Constants.API;
import gov.ca.cwds.cals.service.dto.rfa.OtherAdultDTO;
import gov.ca.cwds.cals.service.dto.rfa.collection.CollectionDTO;
import gov.ca.cwds.cals.web.rest.rfa.configuration.TestExternalEntityConfiguration;
import io.dropwizard.testing.FixtureHelpers;
import javax.ws.rs.core.GenericType;

/**
 * @author CWDS CALS API Team
 */

@SuppressWarnings("squid:S2187")
public class RFA1aOtherAdultsResourceTest extends
    BaseExternalEntityApiTest<OtherAdultDTO> {

  public static final String OTHER_ADULTS_FIXTURE = FixtureHelpers.fixture("fixtures/rfa/rfa-1a-other-adults.json");

  @Override
  protected BaseExternalEntityApiHelper<OtherAdultDTO> getExternalEntityApiHelper() {

    TestExternalEntityConfiguration<OtherAdultDTO> configuration =

        new TestExternalEntityConfiguration<OtherAdultDTO>(
            clientTestRule,
            OtherAdultDTO.class,
            API.RFA_1A_OTHER_ADULTS) {

          @Override
          protected String getFixture() {
            return OTHER_ADULTS_FIXTURE;
          }

          @Override
          public GenericType<CollectionDTO<OtherAdultDTO>> getCollectionDTOGenericType() {
            return new GenericType<CollectionDTO<OtherAdultDTO>>() {
            };
          }

          @Override
          public void modifyEntity(OtherAdultDTO entity) {
            entity.setFirstName("FIRST_NAME");
          }

        };

    return new BaseExternalEntityApiHelper<>(clientTestRule, configuration, formAHelper);
  }

}
