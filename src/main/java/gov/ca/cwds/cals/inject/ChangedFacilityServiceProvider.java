package gov.ca.cwds.cals.inject;

import com.google.inject.Inject;
import com.google.inject.Injector;
import gov.ca.cwds.cals.service.ChangedFacilityService;
import io.dropwizard.hibernate.UnitOfWorkAwareProxyFactory;

/**
 * @author CWDS TPT-2
 */
public class ChangedFacilityServiceProvider extends AbstractInjectProvider<ChangedFacilityService> {

  @Inject
  public ChangedFacilityServiceProvider(Injector injector,
      UnitOfWorkAwareProxyFactory unitOfWorkAwareProxyFactory) {
    super(injector, unitOfWorkAwareProxyFactory);
  }

  @Override
  public Class<ChangedFacilityService> getServiceClass() {
    return ChangedFacilityService.class;
  }
}
