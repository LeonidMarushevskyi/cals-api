package poc;

import com.google.inject.Key;
import com.google.inject.name.Names;
import gov.ca.cwds.rest.BaseApiApplication;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.PermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

import java.util.Optional;

/**
 * Created by dmitry.rudenko on 9/25/2017.
 */
public class AbacPermissionResolver implements PermissionResolver {
  @Override
  public Permission resolvePermission(String permissionString) {
    if (permissionString.matches("^[^:,*]+:[^:,*]+:[^:,*]+$")) {
      String[] parts = permissionString.split(":");
      String handlerName = parts[0] + ":" + parts[1];
      Optional<PermissionHandler> permissionHandler = findPermissionHandler(handlerName);
      if (permissionHandler.isPresent()) {
        return new AbacPermission(parts[2], permissionHandler.get(), permissionString);
      }
    }
    return new WildcardPermission(permissionString);
  }

  private Optional<PermissionHandler> findPermissionHandler(String name) {
    try {
      PermissionHandler permissionHandler = BaseApiApplication.getInjector().getInstance(Key.get(PermissionHandler.class, Names.named(name)));
      return Optional.of(permissionHandler);
    } catch (Exception e) {
      return Optional.empty();
    }
  }
}
