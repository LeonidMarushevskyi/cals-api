package gov.ca.cwds.cals.web.rest.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * @author CWDS CALS API Team
 */

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum CalsExceptionInfo {

  COMPLAINT_NOT_FOUND_BY_ID("1",
      "Facility complaint is not found by facility number and complaint id"),

  DISTRICT_OFFICE_IS_UNEXPECTEDLY_UNKNOWN("2",
      "Disctrict office (lis_fac_file.fac_do_nbr) is unexpectedly empty"),

  RFA_1A_APPLICANT_NOT_FOUND_BY_ID("3", "Applicant is not found by Form Id and applicant id");

  @JsonProperty("code")
  private final String code;

  @JsonProperty("message")
  private final String message;

  CalsExceptionInfo(String code, String message) {
    this.message = message;
    this.code = code;
  }

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this);
  }

}
