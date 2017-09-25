package poc;

import com.google.inject.Key;
import com.google.inject.name.Names;
import gov.ca.cwds.rest.BaseApiApplication;
import gov.ca.cwds.security.shiro.realms.JwtRealm;
import gov.ca.cwds.security.shiro.realms.PerryAccount;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;

import java.util.*;

/**
 * Created by dmitry.rudenko on 9/22/2017.
 */
public class AbacRealm extends JwtRealm {
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
    return this.getAuthenticationInfo(account());
  }

  private AuthenticationInfo getAuthenticationInfo(PerryAccount perryAccount) {
    List<Object> principals = new ArrayList<>();
    principals.add(perryAccount.getUser());
    principals.add(perryAccount);
    PrincipalCollection principalCollection = new SimplePrincipalCollection(principals, this.getName());
    return new SimpleAuthenticationInfo(principalCollection, "N/A");
  }

  protected PerryAccount account() {
    PerryAccount perryAccount = new PerryAccount();
    perryAccount.setUser("abac");
    perryAccount.setRoles(new HashSet<>(Arrays.asList("caseworker")));
    return perryAccount;
  }

  @SuppressWarnings("unchecked")
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    SimpleAuthorizationInfo authorizationInfo = (SimpleAuthorizationInfo) super.doGetAuthorizationInfo(principals);
    authorizationInfo.addObjectPermission(new AbacPermission());
    return authorizationInfo;
  }

}
