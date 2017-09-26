package poc;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;

import javax.script.*;
import java.lang.reflect.Parameter;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by dmitry.rudenko on 9/25/2017.
 */
public class AbacMethodInterceptor implements MethodInterceptor {
  private ScriptEngine scriptEngine;

  public AbacMethodInterceptor() {
    scriptEngine = new ScriptEngineManager().getEngineByName("groovy");
  }

  @Override
  public Object invoke(MethodInvocation methodInvocation) throws Throwable {
    String[] permissions = methodInvocation.getMethod().getAnnotation(RequiresAbacPermission.class).value();
    Set<String> resultPermissions = new HashSet<>();
    for (String permission : permissions) {
      if (isArgPermission(permission)) {
        checkArgPermission(permission, methodInvocation);
      } else if (isResultPermission(permission)) {
        resultPermissions.add(permission);
      }
    }

    Object result = methodInvocation.proceed();
    if(resultPermissions.isEmpty()) {
      return result;
    }
    if (result instanceof Collection) {
      return filterResult(resultPermissions, (Collection) result);
    } else {
      checkResultPermissions(resultPermissions, result);
      return result;
    }
  }

  private void checkArgPermission(String permission, MethodInvocation methodInvocation) throws Exception {
    ScriptContext scriptContext = new SimpleScriptContext();
    for (int i = 0; i < methodInvocation.getMethod().getParameterCount(); i++) {
      Parameter parameter = methodInvocation.getMethod().getParameters()[i];
      scriptContext.setAttribute(parameter.getName(), methodInvocation.getArguments()[i], ScriptContext.ENGINE_SCOPE);
    }
    permission = scriptEngine.eval("\"" + permission + "\"", scriptContext).toString();
    SecurityUtils.getSubject().checkPermission(permission);
  }

  private void checkResultPermissions(Set<String> permissions, Object result) throws AuthorizationException {
    permissions.forEach(permission -> {
      checkResultPermission(permission, result);
    });
  }

  private void checkResultPermission(String permission, Object result) throws AuthorizationException {
    ScriptContext scriptContext = new SimpleScriptContext();
    scriptContext.setAttribute("result", result, ScriptContext.ENGINE_SCOPE);
    try {
      permission = scriptEngine.eval("\"" + permission + "\"", scriptContext).toString();
    } catch (ScriptException e) {
      throw new AuthorizationException(e);
    }
    SecurityUtils.getSubject().checkPermission(permission);
  }

  private boolean isArgPermission(String permission) {
    return permission.contains("$arg");
  }

  private boolean isResultPermission(String permission) {
    return permission.contains("$result");
  }

  @SuppressWarnings("unchecked")
  private Collection filterResult(Set<String> permissions, Collection results) throws IllegalAccessException, InstantiationException, ScriptException {
    Collection out = results.getClass().newInstance();
    for (Object result : results) {
      try {
        checkResultPermissions(permissions, result);
        out.add(result);
      } catch (AuthorizationException e) {
        //ignore
      }
    }
    return out;
  }

}
