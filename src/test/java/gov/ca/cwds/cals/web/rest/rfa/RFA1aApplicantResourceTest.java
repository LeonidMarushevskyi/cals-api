package gov.ca.cwds.cals.web.rest.rfa;

import gov.ca.cwds.cals.Constants.API;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.Applicant;
import gov.ca.cwds.cals.service.dto.rfa.ApplicantsDTO;
import gov.ca.cwds.cals.web.rest.rfa.configuration.ExternalEntityConfiguration;

/**
 * @author CWDS CALS API Team
 */
public class RFA1aApplicantResourceTest extends
    BaseExternalEntityApiTest<Applicant, ApplicantsDTO> {

  @Override
  protected BaseExternalEntityApiHelper<Applicant, ApplicantsDTO> getExternalEntityApiHelper() {
    ExternalEntityConfiguration<Applicant, ApplicantsDTO> configuration =

        new ExternalEntityConfiguration<Applicant, ApplicantsDTO>(
            clientTestRule,
            Applicant.class,
            ApplicantsDTO.class,
            API.RFA_1A_APPLICANTS) {

          @Override
          protected String getCreateFixture() {
            //return "fixtures/rfa/rfa-1a-applicant.json";
            return "fixtures/rfa/stub.json";
          }

          @Override
          public void modifyEntity(Applicant entity) {
            entity.setFirstName("testFirstName");
          }

        };

    return new BaseExternalEntityApiHelper<>(clientTestRule, configuration);
  }
}
