package poc;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;

/**
 * Created by dmitry.rudenko on 9/22/2017.
 */
public class SecurityModule extends AbstractModule {
  @Override
  protected void configure() {

  }

  @Provides
  @Named("rfa1a:write")
  Rfa1aWritePermission rfa1aWritePermission() {
    return new Rfa1aWritePermission();
  }

}
