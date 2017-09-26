package poc;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.shiro.SecurityUtils;

import javax.script.*;
import java.lang.reflect.Parameter;

/**
 * Created by dmitry.rudenko on 9/25/2017.
 */
public class AbacMethodInterceptor implements MethodInterceptor {
  @Override
  public Object invoke(MethodInvocation methodInvocation) throws Throwable {
    String permission = methodInvocation.getMethod().getAnnotation(RequiresAbacPermission.class).value();
    ScriptEngineManager factory = new ScriptEngineManager();
    ScriptEngine scriptEngine = factory.getEngineByName("groovy");
    ScriptContext scriptContext = new SimpleScriptContext();
    for(int i = 0; i < methodInvocation.getMethod().getParameterCount(); i++) {
      Parameter  parameter = methodInvocation.getMethod().getParameters()[i];
      scriptContext.setAttribute(parameter.getName(), methodInvocation.getArguments()[i],  ScriptContext.ENGINE_SCOPE);
    }
    permission = scriptEngine.eval("\"" + permission + "\"", scriptContext).toString();
    SecurityUtils.getSubject().checkPermission(permission);
    return methodInvocation.proceed();
  }
}
