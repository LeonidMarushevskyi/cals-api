package gov.ca.cwds.cals.persistence.model.calsns.rfa;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.PhoneType;
import gov.ca.cwds.cals.service.dto.BaseDTO;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @author CWDS CALS API Team
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Phone extends BaseDTO {

  private static final long serialVersionUID = 3691096439228739229L;

  private PhoneType phoneType;

  private String number;

  @JsonProperty("preferred")
  private boolean isPreferred;

  public PhoneType getPhoneType() {
    return phoneType;
  }

  public void setPhoneType(PhoneType phoneType) {
    this.phoneType = phoneType;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public boolean isPreferred() {
    return isPreferred;
  }

  public void setPreferred(boolean preferred) {
    isPreferred = preferred;
  }

  @Override
  public boolean equals(Object o) {
    return EqualsBuilder.reflectionEquals(this, o);
  }

  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this);
  }

  @Override
  public String toString() {
    return "Phone{"
        + "phoneType="
        + phoneType
        + ", number='"
        + number
        + '\''
        + ", isPreferred="
        + isPreferred
        + '}';
  }
}
