package gov.ca.cwds.cals.service.mapper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** @author CWDS CALS API Team */
public class CamelCasePostProcessCommand implements PostProcessCommand {

  private static final Logger LOG = LoggerFactory.getLogger(CamelCasePostProcessCommand.class);

  private final Field field;

  private final Object target;

  public CamelCasePostProcessCommand(Field field, Object target) {
    this.field = field;
    this.target = target;
  }

  @Override
  public void execute() {

    String fieldName = field.getName();
    if (field.getType().isAssignableFrom(String.class)) {
      try {
        String actualString =
            reformatToCammelCase((String) PropertyUtils.getProperty(target, fieldName));
        PropertyUtils.setProperty(target, fieldName, actualString);
      } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
        LOG.warn("Can't proceed command execution for field: " + fieldName, e);
      }
    } else {

      String warnMessage =
          String.format(
              "String field expected but the field %s of %s is not a String. ",
              fieldName, target.getClass());

      LOG.warn(warnMessage);
    }
  }

  public static String reformatToCammelCase(String str) {
    String[] words = str.split("\\s+|\\_");

    for (int i = 0; i < words.length; i++) {
      words[i] = words[i].substring(0, 1).toUpperCase() + words[i].substring(1).toLowerCase();
    }

    return StringUtils.join(words, " ");
  }
}
