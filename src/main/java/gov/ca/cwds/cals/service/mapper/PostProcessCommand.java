package gov.ca.cwds.cals.service.mapper;

/** @author CWDS CALS API Team */
@FunctionalInterface
public interface PostProcessCommand {
  void execute();
}
