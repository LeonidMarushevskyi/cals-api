package gov.ca.cwds.cals.inject;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import gov.ca.cwds.cals.service.ChangedFacilityService;
import gov.ca.cwds.cals.service.ComplaintService;
import gov.ca.cwds.cals.service.CountiesService;
import gov.ca.cwds.cals.service.DictionariesService;
import gov.ca.cwds.cals.service.FacilityInspectionCollectionService;
import gov.ca.cwds.cals.service.FacilityInspectionService;
import gov.ca.cwds.cals.service.FacilityService;
import gov.ca.cwds.cals.service.rfa.RFA1aApplicantService;
import gov.ca.cwds.cals.service.rfa.RFA1aApplicantsCollectionService;
import gov.ca.cwds.cals.service.rfa.RFA1aApplicantsDeclarationService;
import gov.ca.cwds.cals.service.rfa.RFA1aApplicantsHistoryService;
import gov.ca.cwds.cals.service.rfa.RFA1aApplicantsRelationshipService;
import gov.ca.cwds.cals.service.rfa.RFA1aChildDesiredService;
import gov.ca.cwds.cals.service.rfa.RFA1aFormService;
import gov.ca.cwds.cals.service.rfa.RFA1aFormsCollectionService;
import gov.ca.cwds.cals.service.rfa.RFA1aMinorChildService;
import gov.ca.cwds.cals.service.rfa.RFA1aMinorChildrenCollectionService;
import gov.ca.cwds.cals.service.rfa.RFA1aOtherAdultService;
import gov.ca.cwds.cals.service.rfa.RFA1aOtherAdultsCollectionService;
import gov.ca.cwds.cals.service.rfa.RFA1aReferencesService;
import gov.ca.cwds.cals.service.rfa.RFA1aResidenceService;
import gov.ca.cwds.cals.service.rfa.RFA1bCollectionService;
import gov.ca.cwds.cals.service.rfa.RFA1bService;
import gov.ca.cwds.cals.service.rfa.RFA1cCollectionService;
import gov.ca.cwds.cals.service.rfa.RFA1cService;
import gov.ca.cwds.cals.service.validation.business.DroolsService;
import gov.ca.cwds.data.cms.SystemCodeDao;
import gov.ca.cwds.data.cms.SystemMetaDao;
import gov.ca.cwds.rest.api.domain.cms.SystemCodeCache;
import gov.ca.cwds.rest.services.cms.CachingSystemCodeService;
import gov.ca.cwds.rest.services.cms.SystemCodeService;

/**
 * Identifies all CALS API business layer (services) classes available for dependency injection by
 * Guice.
 *
 * @author CALS API Team
 */
public class ServicesModule extends AbstractModule {

  /**
   * Default constructor
   */
  public ServicesModule() {
    // Do nothing - Default constructor
  }

  @Override
  protected void configure() {
    bind(ComplaintService.class);
    bind(FacilityInspectionCollectionService.class);
    bind(FacilityInspectionService.class);
    bind(CountiesService.class);
    bind(DictionariesService.class);
    bind(FacilityService.class).toProvider(FacilityServiceProvider.class);
    bind(ChangedFacilityService.class).toProvider(ChangedFacilityServiceProvider.class);

    // RFA
    bind(RFA1aFormService.class).toProvider(RFA1aFormServiceProvider.class);
    bind(RFA1aFormsCollectionService.class);
    bind(RFA1aApplicantService.class);
    bind(RFA1aApplicantsCollectionService.class);
    bind(RFA1aMinorChildService.class);
    bind(RFA1aMinorChildrenCollectionService.class);
    bind(RFA1bService.class);
    bind(RFA1bCollectionService.class);
    bind(RFA1cService.class);
    bind(RFA1cCollectionService.class);
    bind(RFA1aOtherAdultService.class);
    bind(RFA1aOtherAdultsCollectionService.class);
    bind(RFA1aResidenceService.class);
    bind(RFA1aApplicantsRelationshipService.class);
    bind(RFA1aApplicantsHistoryService.class);
    bind(RFA1aReferencesService.class);
    bind(RFA1aChildDesiredService.class);
    bind(RFA1aApplicantsDeclarationService.class);
    bind(DroolsService.class);
  }

  @Provides
  public SystemCodeService provideSystemCodeService(SystemCodeDao systemCodeDao,
      SystemMetaDao systemMetaDao) {
    final long secondsToRefreshCache = 15L * 24 * 60 * 60; // 15 days
    return new CachingSystemCodeService(systemCodeDao, systemMetaDao, secondsToRefreshCache, false);
  }

  @Provides
  public SystemCodeCache provideSystemCodeCache(SystemCodeService systemCodeService) {
    SystemCodeCache systemCodeCache = (SystemCodeCache) systemCodeService;
    systemCodeCache.register();
    return systemCodeCache;
  }
}
