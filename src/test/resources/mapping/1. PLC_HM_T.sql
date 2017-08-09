CREATE TABLE PLC_HM_T
(
    IDENTIFIER char(10) PRIMARY KEY NOT NULL, -- generated base on algorithm
    AGE_FRM_NO smallint DEFAULT 0 NOT NULL,
    AGE_TO_NO smallint DEFAULT 0 NOT NULL,
    AT_CAP_IND char(1) NOT NULL, -- N
    BCK_PERSNM char(35) DEFAULT ' ' NOT NULL,
    BCK_EXT_NO int DEFAULT 0 NOT NULL,
    BCK_TEL_NO decimal(10) DEFAULT 0 NOT NULL,
    CERTF_PNDT date DEFAULT NULL,
    CHLCR_PLCD char(1) NOT NULL, -- U
    CITY_NM char(20) NOT NULL, -- residence.addresses[x].type = Residential then residence.addresses[x].city
    CL_SRVDC smallint DEFAULT 0 NOT NULL,
    CONF_EFIND char(1) NOT NULL, -- N
    CUR_OCP_NO smallint NOT NULL, -- 0
    EMR_SHLTCD char(1) NOT NULL, - U
    FAX_NO decimal(10) DEFAULT 0 NOT NULL,
    FRG_ADRT_B char(1) NOT NULL, -- N
    GNDR_ACPCD char(1) DEFAULT ' ' NOT NULL,
    GEO_RGNTCD char(2) DEFAULT ' ' NOT NULL,
    GVR_ENTC smallint DEFAULT 0 NOT NULL,
    INHM_VSTCD char(1) NOT NULL, -- U`
    MAX_CAP_NO smallint DEFAULT 0 NOT NULL,
    LA_VNDR_ID char(6) DEFAULT ' ' NOT NULL,
    LICENSE_NO char(9) DEFAULT 'NULL',
    LIC_APL_DT date DEFAULT NULL,
    LIC_CAP_NO smallint DEFAULT 0 NOT NULL,
    LIC_EFCTDT date DEFAULT NULL,
    LIC_EXP_DT date DEFAULT NULL,
    LIC_STATDT date DEFAULT NULL,
    LIC_STC smallint DEFAULT 0 NOT NULL,
    LIC_BSNC smallint DEFAULT 0 NOT NULL,
    LICNSEE_NM char(50) DEFAULT ' ' NOT NULL,
    LICENSR_CD char(2) NOT NULL, -- NA
    FACLTY_NM char(50) DEFAULT ' ' NOT NULL, -- applicants.applicant1.last_name + ", " + applicants.applicant1.first_name + " & " + applicants.applicant2.first_name + " RFH". A good naming convention is: Last Name, First Names followed by type of facility (e.g., J, Mary & Thomas FFH).
    OPRTD_BYID char(10) DEFAULT 'NULL',
    OPRTD_BYCD char(1) DEFAULT 'NULL',
    P_CITY_NM char(20) DEFAULT ' ' NOT NULL,
    PYE_FSTNM char(20) DEFAULT ' ' NOT NULL,
    PYE_LSTNM char(25) DEFAULT ' ' NOT NULL,
    PYE_MIDNM char(1) DEFAULT ' ' NOT NULL,
    P_STATE_C smallint DEFAULT 0 NOT NULL,
    PSTREET_NM char(40) DEFAULT ' ' NOT NULL,
    PSTREET_NO char(10) DEFAULT ' ' NOT NULL,
    P_ZIP_NO int DEFAULT 0 NOT NULL,
    PLC_FCLC smallint NOT NULL, -- Resource Family Home code: 6914 (Resource Family Home)
    PRM_CNCTNM char(35) DEFAULT ' ' NOT NULL, -- applicants.applicant[1].first_name + applicants.applicant[1].last_name
    PRM_EXT_NO int DEFAULT 0 NOT NULL, -- applicants.applicant[1].phones.phone.extension where preferred=true if no preferred then just get first number extension
    PRM_SUBSID char(10) DEFAULT 'NULL', -- The logical foreign key representing the ID from the source entity of the Primary Care Provider for a particular PLACEMENT HOME.
    PRM_SUBSNM char(54) DEFAULT ' ' NOT NULL, -- applicants.applicant[1].name_prefix.value + " " + applicants.applicant[1].first_name + " " + applicants.applicant[1].middle_name + " " + applicants.applicant[1].last_name + " " + applicants.applicant[1].name_suffix.value
    PRM_TEL_NO decimal(10) DEFAULT 0 NOT NULL, --applicants.applicant[1].phones.number where phone.preferred=true if no preferred then just get first number
    PVD_TRNSCD char(1) NOT NULL, -- U
    PUB_TRNSCD char(1) NOT NULL, -- U
    F_STATE_C smallint DEFAULT 0 NOT NULL, -- residence.addresses[x].type = Residential then residence.addresses[x].state
    STREET_NM char(40) NOT NULL, -- residence.addresses[x].type = Residential then residence.addresses[x].street_address (need to split to number and street and get only street)
    STREET_NO char(10) DEFAULT ' ' NOT NULL, -- residence.addresses[x].type = Residential then residence.addresses[x].street_address (need to split to number and street and get only number)
    ZIP_NO int DEFAULT 0 NOT NULL, -- residence.addresses[x].type = Residential then residence.addresses[x].zip
    LST_UPD_ID char(3) NOT NULL, -- The ID (a sequential 3 digit base 62 number generated by the system) of the STAFF PERSON or batch program which made the last update to an occurrence of this entity type.
    LST_UPD_TS timestamp NOT NULL, -- Current time stamp
    ADDR_DSC char(120) DEFAULT ' ' NOT NULL, --residence.directions_to_home
    SPCHAR_DSC char(120) DEFAULT ' ' NOT NULL,
    CTYPRF_DSC char(240) DEFAULT ' ' NOT NULL,
    ED_PVR_DSC char(120) DEFAULT ' ' NOT NULL,
    ENV_FCTDSC char(60) DEFAULT ' ' NOT NULL,
    HAZRDS_DSC char(120) DEFAULT ' ' NOT NULL,
    LIS_PRFDSC char(210) DEFAULT ' ' NOT NULL,
    PETS_DSC char(60) DEFAULT ' ' NOT NULL,
    RLG_ACTDSC char(60) DEFAULT ' ' NOT NULL,
    PY_ZIP_SFX smallint DEFAULT 0 NOT NULL,
    ZIP_SFX_NO smallint DEFAULT 0 NOT NULL,
    AP_STAT_TP smallint DEFAULT 0 NOT NULL,
    FKCNTY_CST char(10) DEFAULT 'NULL',
    CERT_CMPLT char(1) DEFAULT ' ' NOT NULL,
    LA_P_CTYNM char(20) DEFAULT ' ' NOT NULL,
    LA_P_FSTNM char(20) DEFAULT ' ' NOT NULL,
    LA_P_LSTNM char(25) DEFAULT ' ' NOT NULL,
    LA_P_MIDNM char(1) DEFAULT ' ' NOT NULL,
    LP_STATE_C smallint DEFAULT 0 NOT NULL,

    LA_P_STNM char(40) DEFAULT ' ' NOT NULL,
    LA_P_STNO char(10) DEFAULT ' ' NOT NULL,
    LA_P_ZIPNO int DEFAULT 0 NOT NULL,
    LA_P_ZPSFX smallint DEFAULT 0 NOT NULL,
    LA_P_BSNSS char(30) DEFAULT ' ' NOT NULL,
    AP_STAT_DT date DEFAULT NULL,

    LA_P_PH_NO decimal(10) DEFAULT 0 NOT NULL,
    LA_P_PH_EX int DEFAULT 0 NOT NULL,
    ADHMONLY char(1) DEFAULT ' ' NOT NULL,
    PYE_EXT_NO int DEFAULT 0 NOT NULL,
    PYE_TEL_NO decimal(10) DEFAULT 0 NOT NULL,
    ARCASS_IND char(1) DEFAULT 'N' NOT NULL, -- N
    COMFAC_IND char(1) DEFAULT 'N' NOT NULL, -- N
    TRNHSG_IND char(1) DEFAULT 'N' NOT NULL, -- N
    TRNHSG_FAC char(1) DEFAULT 'N' NOT NULL, -- N
    NEWLIC_NO char(9) DEFAULT 'NULL',
    NEWLIC_UPD char(1) DEFAULT 'N' NOT NULL,
    OLDFAC_ID char(10) DEFAULT 'NULL',
    EM_CNT_B char(1) DEFAULT 'N' NOT NULL, -- N

    END_DT date DEFAULT NULL,
    PH_ENDC smallint DEFAULT NULL,
    END_COMDSC char(254) DEFAULT 'NULL'
);
CREATE UNIQUE INDEX PLCHM2 ON PLC_HM_T (GVR_ENTC, LICENSE_NO);
CREATE UNIQUE INDEX PLCHM7X ON PLC_HM_T (LA_VNDR_ID);
CREATE UNIQUE INDEX PLCHM6X ON PLC_HM_T (FACLTY_NM);
CREATE UNIQUE INDEX PLCHM5X ON PLC_HM_T (OPRTD_BYID, OPRTD_BYCD);
CREATE UNIQUE INDEX PLCHM66X ON PLC_HM_T (PRM_SUBSID);
CREATE UNIQUE INDEX PLCHM3X ON PLC_HM_T (STREET_NM);
CREATE UNIQUE INDEX PLCHM8X ON PLC_HM_T (STREET_NO, ZIP_NO);
CREATE UNIQUE INDEX PLCHM4X ON PLC_HM_T (FKCNTY_CST, GVR_ENTC);
CREATE UNIQUE INDEX PLCHM1X ON PLC_HM_T (FKCNTY_CST)