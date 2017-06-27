package gov.ca.cwds.cals.service;

import com.google.inject.Inject;
import gov.ca.cwds.cals.persistence.dao.cms.PlacementHomeDao;
import gov.ca.cwds.cals.persistence.model.cms.BaseCountyLicenseCase;
import gov.ca.cwds.cals.persistence.model.cms.BasePlacementHome;
import gov.ca.cwds.cals.persistence.model.cms.BaseStaffPerson;
import gov.ca.cwds.cals.persistence.model.cms.County;
import gov.ca.cwds.cals.persistence.model.fas.LpaInformation;
import gov.ca.cwds.cals.persistence.model.lisfas.LisFacFile;
import gov.ca.cwds.cals.persistence.dao.cms.CountiesDao;
import gov.ca.cwds.cals.persistence.dao.fas.LpaInformationDao;
import gov.ca.cwds.cals.service.dto.FacilityDTO;
import gov.ca.cwds.cals.service.mapper.FacilityMapper;
import gov.ca.cwds.cals.service.mapper.FasFacilityMapper;
import gov.ca.cwds.cals.web.rest.exception.UserFriendlyException;
import gov.ca.cwds.cals.web.rest.parameter.FacilityParameterObject;
import gov.ca.cwds.data.CrudsDao;
import gov.ca.cwds.rest.api.Request;
import gov.ca.cwds.rest.api.Response;
import gov.ca.cwds.rest.services.CrudsService;
import io.dropwizard.hibernate.UnitOfWork;

import java.io.Serializable;

import static gov.ca.cwds.cals.Constants.UnitOfWork.CMS;
import static gov.ca.cwds.cals.Constants.UnitOfWork.FAS;
import static gov.ca.cwds.cals.Constants.UnitOfWork.LIS;
import static gov.ca.cwds.cals.web.rest.exception.CalsExceptionInfo.DISTRICT_OFFICE_IS_UNEXPECTEDLY_UNKNOWN;
import static javax.ws.rs.core.Response.Status.EXPECTATION_FAILED;

/**
 *
 *  CRUD service for {@link gov.ca.cwds.cals.service.dto.FacilityDTO}
 *
 *  @author CALS API Team
 *
 */
public class FacilityService implements CrudsService {
    private CrudsDao<LisFacFile> lisDsLisFacFileDao;
    private CrudsDao<LisFacFile> fasDsLisFacFileDao;
    private PlacementHomeDao placementHomeDao;
    private CountiesDao countiesDao;
    protected FacilityMapper facilityMapper;
    private FasFacilityMapper fasFacilityMapper;
    private LpaInformationDao lpaInformationDao;

    @Inject
    public FacilityService(CrudsDao<LisFacFile> lisDsLisFacFileDao,
                           CrudsDao<LisFacFile> fasDsLisFacFileDao,
                           PlacementHomeDao placementHomeDao, LpaInformationDao lpaInformationDao,
                           CountiesDao countiesDao, FacilityMapper facilityMapper, FasFacilityMapper fasFacilityMapper) {
        this.lisDsLisFacFileDao = lisDsLisFacFileDao;
        this.fasDsLisFacFileDao = fasDsLisFacFileDao;
        this.placementHomeDao = placementHomeDao;
        this.lpaInformationDao = lpaInformationDao;
        this.countiesDao = countiesDao;
        this.facilityMapper = facilityMapper;
        this.fasFacilityMapper = fasFacilityMapper;
    }

    @Override
    public Response find(Serializable params) {
        return findByParameterObject((FacilityParameterObject) params);
    }

    protected FacilityDTO findById(String id) {
        return findByParameterObject(newFacilityParameterObject(id));
    }

    private FacilityDTO findByParameterObject(FacilityParameterObject parameterObject) {
        FacilityDTO facilityDTO = null;
        if (LIS.equals(parameterObject.getUnitOfWork())) {
            LisFacFile lisDsLisFacFile = findLisFacilityByLicenseNumber(parameterObject);
            LpaInformation lpaInformation = lisDsLisFacFile != null ? findAssignedWorkerInformation(lisDsLisFacFile) : null;
            facilityDTO = facilityMapper.toFacilityDTO(lisDsLisFacFile, lpaInformation);

            LisFacFile fasDsLisFacFile = findFasFacilityByLicenseNumber(parameterObject);
            facilityDTO = fasFacilityMapper.toFacilityDTO(facilityDTO, fasDsLisFacFile);
        } else if (CMS.equals(parameterObject.getUnitOfWork())) {
            BasePlacementHome placementHome = findFacilityById(parameterObject);
            facilityDTO = facilityMapper.toFacilityDTO(placementHome);
        }
        return facilityDTO;
    }

    @UnitOfWork(LIS)
    protected LisFacFile findLisFacilityByLicenseNumber(FacilityParameterObject parameterObject) {
        return lisDsLisFacFileDao.find(parameterObject.getLicenseNumber());
    }

    @UnitOfWork(FAS)
    protected LisFacFile findFasFacilityByLicenseNumber(FacilityParameterObject parameterObject) {
        return fasDsLisFacFileDao.find(parameterObject.getLicenseNumber());
    }

    @UnitOfWork(FAS)
    protected LpaInformation findAssignedWorkerInformation(LisFacFile lisFacFile) {
        if (lisFacFile.getFacDoNbr() == null) {
            throw new UserFriendlyException(DISTRICT_OFFICE_IS_UNEXPECTEDLY_UNKNOWN, EXPECTATION_FAILED);
        }
        String lpaCode = String.format("%02d", lisFacFile.getFacDoNbr().getDoNbr()) + lisFacFile.getFacDoEvalCode();
        return lpaInformationDao.findByLpaCode(lpaCode);
    }

    @UnitOfWork(CMS)
    protected BasePlacementHome findFacilityById(FacilityParameterObject parameterObject) {
        // todo refactor to java8
        // todo code duplication in FacilityCollectionService.findFacilities
        BasePlacementHome placementHome = placementHomeDao.findByParameterObject(parameterObject);
        if (placementHome != null) {
            BaseCountyLicenseCase countyLicenseCase = placementHome.getCountyLicenseCase();
            if (countyLicenseCase != null) {
                BaseStaffPerson staffPerson = countyLicenseCase.getStaffPerson();
                if (staffPerson != null) {
                    County county = countiesDao.findByLogicalId(staffPerson.getCntySpfcd());
                    staffPerson.setCounty(county);
                }
            }
        }
        return placementHome;
    }

    @Override
    public Response delete(Serializable serializable) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Response create(Request request) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Response update(Serializable serializable, Request request) {
        throw new UnsupportedOperationException();
    }

    public static FacilityParameterObject newFacilityParameterObject(String facilityNumber) {
        FacilityParameterObject parameterObject;
        try {
            Integer licenseNumber = Integer.valueOf(facilityNumber);
            parameterObject = new FacilityParameterObject(licenseNumber, LIS);
        } catch (NumberFormatException e) {
            parameterObject = new FacilityParameterObject(facilityNumber, CMS);
        }
        return parameterObject;
    }
}
