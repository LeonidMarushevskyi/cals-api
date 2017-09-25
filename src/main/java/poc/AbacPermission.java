package poc;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.WildcardPermission;

/**
 * Created by dmitry.rudenko on 9/25/2017.
 */
class AbacPermission extends WildcardPermission {
  private String id;
  private PermissionHandler permissionHandler;

  AbacPermission(String id, PermissionHandler permissionHandler, String wildCard) {
    super(wildCard);
    this.id = id;
    this.permissionHandler = permissionHandler;
  }

  AbacPermission() {

  }

  public boolean implies(Permission permission) {
    if (!(permission instanceof AbacPermission)) return false;
    AbacPermission abacPermission = (AbacPermission) permission;
    return abacPermission.id != null
            && abacPermission.permissionHandler != null
            && abacPermission.permissionHandler.check(id);
  }
}
