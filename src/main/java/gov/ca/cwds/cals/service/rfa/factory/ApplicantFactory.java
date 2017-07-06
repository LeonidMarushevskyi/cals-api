package gov.ca.cwds.cals.service.rfa.factory;

import gov.ca.cwds.cals.persistence.model.calsns.rfa.ApplicantDTO;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aApplicant;

/**
 * @author CWDS CALS API Team
 */
public class ApplicantFactory
    implements RFAExternalEntityFactory<RFA1aApplicant, ApplicantDTO> {

  public static final RFAExternalEntityFactory<RFA1aApplicant, ApplicantDTO>
      INSTANCE = new ApplicantFactory();

  private ApplicantFactory() {
  }

  @Override
  public ApplicantDTO createEntityDTO() {
    return new ApplicantDTO();
  }

  @Override
  public RFA1aApplicant createEntity() {
    return new RFA1aApplicant();
  }

  @Override
  public Class<RFA1aApplicant> getEntityClass() {
    return RFA1aApplicant.class;
  }

  @Override
  public String getFindAllByFormNamedQuery() {
    return RFA1aApplicant.NAMED_QUERY_FIND_ALL_BY_FORM;
  }

  @Override
  public String getFindByFormIdAndEntityIdNamedQuery() {
    return RFA1aApplicant.NAMED_QUERY_FIND_BY_FORM_ID_AND_APPLICANT_ID;
  }

}
