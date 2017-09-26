package poc;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by dmitry.rudenko on 9/25/2017.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresAbacPermission {
  String[] value();
}
