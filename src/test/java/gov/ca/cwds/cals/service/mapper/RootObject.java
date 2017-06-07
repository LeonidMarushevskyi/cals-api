package gov.ca.cwds.cals.service.mapper;

import static gov.ca.cwds.cals.service.mapper.StringTransformStrategy.CAMELCASE;
import static gov.ca.cwds.cals.service.mapper.StringTransformStrategy.REMOVE_TRAILING_SPACES;

/** @author CWDS CALS API Team */
public class RootObject {

  private DeepObject deepObject;

  //Wrong annotation (not String field type)
  @StringTransform(strategies = {CAMELCASE, REMOVE_TRAILING_SPACES}) @RemoveTrailingSpaces private Integer integer;

  private String nullString;
  @StringTransform(strategies = {CAMELCASE, REMOVE_TRAILING_SPACES})
  @RemoveTrailingSpaces private String rootString;

  public DeepObject getDeepObject() {
    return deepObject;
  }

  public void setDeepObject(DeepObject deepObject) {
    this.deepObject = deepObject;
  }

  public Integer getInteger() {
    return integer;
  }

  public void setInteger(Integer integer) {
    this.integer = integer;
  }

  public String getNullString() {
    return nullString;
  }

  public void setNullString(String nullString) {
    this.nullString = nullString;
  }

  public String getRootString() {
    return rootString;
  }

  public void setRootString(String rootString) {
    this.rootString = rootString;
  }
}
