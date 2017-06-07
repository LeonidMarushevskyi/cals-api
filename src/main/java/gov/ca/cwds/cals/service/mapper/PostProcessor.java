package gov.ca.cwds.cals.service.mapper;

import java.util.List;
import org.mapstruct.AfterMapping;
import org.mapstruct.MappingTarget;

/** @author CWDS CALS API Team */
public abstract class PostProcessor {

  protected abstract List<PostProcessCommand> getPostProcessCommands(Object target);

  @AfterMapping
  public void process(@MappingTarget Object target) {
    List<PostProcessCommand> commands = getPostProcessCommands(target);
    for (PostProcessCommand command : commands) {
      command.execute();
    }
  }
}
