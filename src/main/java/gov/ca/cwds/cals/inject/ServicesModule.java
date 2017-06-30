package gov.ca.cwds.cals.inject;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import gov.ca.cwds.cals.persistence.dao.cms.ClientDao;
import gov.ca.cwds.cals.persistence.dao.cms.CountiesDao;
import gov.ca.cwds.cals.persistence.dao.cms.PlacementHomeDao;
import gov.ca.cwds.cals.persistence.dao.fas.LisFacFileFasDao;
import gov.ca.cwds.cals.persistence.dao.fas.LpaInformationDao;
import gov.ca.cwds.cals.persistence.dao.lis.LisFacFileLisDao;
import gov.ca.cwds.cals.service.ComplaintService;
import gov.ca.cwds.cals.service.CountiesService;
import gov.ca.cwds.cals.service.DictionariesService;
import gov.ca.cwds.cals.service.FacilityInspectionCollectionService;
import gov.ca.cwds.cals.service.FacilityInspectionService;
import gov.ca.cwds.cals.service.FacilityService;
import gov.ca.cwds.cals.service.mapper.FacilityChildMapper;
import gov.ca.cwds.cals.service.mapper.FacilityMapper;
import gov.ca.cwds.cals.service.mapper.FasFacilityMapper;
import gov.ca.cwds.cals.service.rfa.ApplicantsRelationshipService;
import gov.ca.cwds.cals.service.rfa.RFA1aFormService;
import gov.ca.cwds.cals.service.rfa.RFAApplicantService;
import gov.ca.cwds.cals.service.rfa.RFAApplicantsCollectionService;
import gov.ca.cwds.cals.service.rfa.RFAFormsCollectionService;
import gov.ca.cwds.cals.service.rfa.RFAMinorChildService;
import gov.ca.cwds.cals.service.rfa.RFAMinorChildrenCollectionService;
import gov.ca.cwds.cals.service.rfa.ResidenceService;
import io.dropwizard.hibernate.UnitOfWorkAwareProxyFactory;

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

    // RFA
    bind(RFA1aFormService.class);
    bind(RFAFormsCollectionService.class);
    bind(RFAApplicantService.class);
    bind(RFAApplicantsCollectionService.class);
    bind(RFAMinorChildService.class);
    bind(RFAMinorChildrenCollectionService.class);
    bind(ResidenceService.class);
    bind(ApplicantsRelationshipService.class);
  }

  @Provides
  @Inject
  @SuppressWarnings("squid:S00107")
    // Configuration
  FacilityService provideFacilityService(
      UnitOfWorkAwareProxyFactory unitOfWorkAwareProxyFactory,
      LisFacFileLisDao lisFacFileLisDao,
      LisFacFileFasDao lisFacFileFasDao,
      PlacementHomeDao placementHomeDao,
      LpaInformationDao lpaInformationDao,
      CountiesDao countiesDao,
      FacilityMapper facilityMapper,
      FasFacilityMapper fasFacilityMapper,
      ClientDao clientDao,
      FacilityChildMapper facilityChildMapper) {
    return unitOfWorkAwareProxyFactory.create(
        FacilityService.class,
        new Class[]{
            LisFacFileLisDao.class,
            LisFacFileFasDao.class,
            PlacementHomeDao.class,
            LpaInformationDao.class,
            CountiesDao.class,
            FacilityMapper.class,
            FasFacilityMapper.class,
            ClientDao.class,
            FacilityChildMapper.class
        },
        new Object[]{
            lisFacFileLisDao,
            lisFacFileFasDao,
            placementHomeDao,
            lpaInformationDao,
            countiesDao,
            facilityMapper,
            fasFacilityMapper,
            clientDao,
            facilityChildMapper
        });
  }
}
