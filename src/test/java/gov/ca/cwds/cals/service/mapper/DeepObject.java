package gov.ca.cwds.cals.service.mapper;

import java.util.Set;

/** @author CWDS CALS API Team */
public class DeepObject {

  private Set<DeepObject> deepObjectsList;
  @StringTransform(strategies = {StringTransformStrategy.CAMELCASE})
  @RemoveTrailingSpaces private String string;

  public String getString() {
    return string;
  }

  public void setString(String string) {
    this.string = string;
  }

  public Set<DeepObject> getDeepObjectsList() {
    return deepObjectsList;
  }

  public void setDeepObjectsList(Set<DeepObject> deepObjectsList) {
    this.deepObjectsList = deepObjectsList;
  }
}
