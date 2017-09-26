package poc;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

/**
 * Created by dmitry.rudenko on 9/22/2017.
 */
public class SecurityModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(PermissionHandler.class)
            .annotatedWith(Names.named("rfa1a:write"))
            .to(Rfa1aWritePermission.class);
  }
}
