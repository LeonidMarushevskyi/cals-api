package poc;

import com.google.inject.Inject;
import gov.ca.cwds.cals.service.rfa.RFA1aFormService;
import gov.ca.cwds.cals.web.rest.parameter.RFA1aFormsParameterObject;

/**
 * Created by dmitry.rudenko on 9/22/2017.
 */

public class Rfa1aWritePermission implements PermissionHandler {

  @Inject
  private RFA1aFormService rfa1aFormService;

  @Override
  public boolean check(String id) {
    rfa1aFormService.find(new RFA1aFormsParameterObject(34L));
    return Long.valueOf(id) > 1;
  }
}
