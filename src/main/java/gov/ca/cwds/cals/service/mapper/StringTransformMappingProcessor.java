package gov.ca.cwds.cals.service.mapper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.mapstruct.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** @author CWDS CALS API Team */
@Mapper
public class StringTransformMappingProcessor extends PostProcessor {

  private static final Logger LOG = LoggerFactory.getLogger(StringTransformMappingProcessor.class);

  @Override
  protected List<PostProcessCommand> getPostProcessCommands(Object target) {

    if (target == null || Collection.class.isAssignableFrom(target.getClass())) {
      return new ArrayList<>();
    }

    Class<?> targetClass = target.getClass();
    Field[] allFields = FieldUtils.getAllFields(targetClass);
    List<PostProcessCommand> commands = new LinkedList<>();
    for (Field field : allFields) {
      fillStringTransformCommands(field, target, commands);
    }
    return commands;
  }

  private void fillStringTransformCommands(
      Field field, Object target, final List<PostProcessCommand> commands) {
    try {
      if (field.isAnnotationPresent(StringTransform.class)
          && field.getType().isAssignableFrom(String.class)) {

        commands.add(new CamelCasePostProcessCommand(field, target));
      } else if (field.getType().isAssignableFrom(Collection.class)) {

        commands.addAll(
            applyForCollection((Collection) PropertyUtils.getProperty(target, field.getName())));
      } else if (!isSimpleValueType(field.getType()) && !field.getName().startsWith("$")) {

        commands.addAll(getPostProcessCommands(PropertyUtils.getProperty(target, field.getName())));
      }
    } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
      LOG.error("Can't get property", e);
    }
  }

  private Collection<? extends PostProcessCommand> applyForCollection(Collection<?> objects) {
    List<PostProcessCommand> commands = new LinkedList<>();
    objects.forEach(item -> commands.addAll(getPostProcessCommands(item)));
    return commands;
  }

  private boolean isSimpleValueType(Class<?> clazz) {
    return (ClassUtils.isPrimitiveOrWrapper(clazz)
        || String.class.isAssignableFrom(clazz)
        || LocalDate.class.isAssignableFrom(clazz)
        || LocalDateTime.class.isAssignableFrom(clazz));
  }
}
