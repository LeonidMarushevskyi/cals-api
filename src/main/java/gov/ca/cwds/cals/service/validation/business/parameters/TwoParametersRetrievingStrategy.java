package gov.ca.cwds.cals.service.validation.business.parameters;

/**
 * @author CWDS CALS API Team
 */
public final class TwoParametersRetrievingStrategy extends AbstractRetrievingParametersStrategy {

  public static final TwoParametersRetrievingStrategy INSTANCE = new TwoParametersRetrievingStrategy();

  private TwoParametersRetrievingStrategy() {
    super((byte) 1, (byte) 2);
  }

}
