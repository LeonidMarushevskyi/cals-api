package poc;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.WildcardPermission;

/**
 * Created by dmitry.rudenko on 9/25/2017.
 */
class AbacPermission extends WildcardPermission {
  private String id;
  private Authorizer authorizer;

  AbacPermission(String id, Authorizer authorizer, String wildCard) {
    super(wildCard);
    this.id = id;
    this.authorizer = authorizer;
  }

  AbacPermission() {
    super(AbacPermission.class.getName());
  }

  public boolean implies(Permission permission) {
    if (!(permission instanceof AbacPermission)) return false;
    AbacPermission abacPermission = (AbacPermission) permission;
    return abacPermission.check();
  }

  private boolean check() {
    return id != null
            && authorizer != null
            && authorizer.check(id);
  }
}
