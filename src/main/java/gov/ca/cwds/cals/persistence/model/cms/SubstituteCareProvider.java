package gov.ca.cwds.cals.persistence.model.cms;

import gov.ca.cwds.data.persistence.PersistentObject;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.Where;

/**
 * @author CWDS CALS API Team
 *
 * A person providing daily care and supervision at a PLACEMENT_HOME, or the license holder for a
 * PLACEMENT_HOME. This person may be a prospective SUBSTITUTE_CARE_PROVIDER, certified, licensed
 * for 24-hour care of children, or exempt from licensing. Any address for the
 * SUBSTITUTE_CARE_PROVIDER other than that of the PLACEMENT_HOME should be documented in this
 * entity type. Identified caretaker(s) of the prospective adoptive child should also be documented
 * in this entity type. This individual could be a related or unrelated person.
 */
@Entity
@Table(name = "SB_PVDRT")
@SuppressWarnings({"squid:S3437"}) //LocalDate is serializable
public class SubstituteCareProvider implements PersistentObject {

  private static final long serialVersionUID = -3252555869369790341L;

  /**
   * ID - A unique, system generated number assigned to each SUBSTITUTE_CARE_PROVIDER. This ID is
   * composed of a base 62 Creation Timestamp and the STAFF_PERSON ID (a sequential 3 digit base 62
   * number generated by the system). This value eliminates the need for an additional set of
   * Creation Timestamp and Creation User ID which is needed to satisfy the Audit Trail
   * requirement.
   */
  @Id
  @Column(name = "IDENTIFIER", nullable = false, length = 10)
  private String identifier;

  /**
   * ADDITIONAL_PHONE_NUMBER - No longer in use. With the implementation of R6.3 on 11/7/09, this
   * attribute will no longer be used. Data previously stored in this attribute will instead be
   * stored in the PHONE_CONTACT_DETAIL.Phone_Number attribute.
   */
  @Basic
  @Column(name = "ADD_TEL_NO", nullable = false, precision = 0)
  private int addTelNo;

  /**
   * ADDITIONL_PHONE_EXTENSION_NUMBER - No longer in use. With the implementation of R6.3 on
   * 11/7/09, this attribute will no longer be used. Data previously stored in this attribute will
   * instead be stored in the PHONE_CONTACT_DETAIL.Phone_Extension_Number attribute.
   */
  @Basic
  @Column(name = "ADD_EXT_NO", nullable = false)
  private int addExtNo;

  /**
   * BIRTH_DATE - The date of birth of the SUBSTITUTE CARE PROVIDER.
   */
  @Basic
  @Column(name = "BIRTH_DT", nullable = true)
  private LocalDate birthDt;

  /**
   * CA_DRIVER_LICENSE_NUMBER - The driver license number of the SUBSTITUTE CARE  PROVIDER.
   */
  @Basic
  @Column(name = "CA_DLIC_NO", nullable = false, length = 8)
  private String caDlicNo;

  /**
   * CITY_NAME - The name of the city where the SUBSTITUTE CARE  PROVIDER lives or works, if
   * different from what is specified on the related PLACEMENT HOME or related FOSTER FAMILY AGENCY
   */
  @Basic
  @Column(name = "CITY_NM", nullable = false, length = 20)
  private String cityNm;

  /**
   * EMPLOYER_NAME - The name of the SUBSTITUTE CARE PROVIDER's employer.
   */
  @Basic
  @Column(name = "EMPLYR_NM", nullable = false, length = 35)
  private String emplyrNm;

  /**
   * FIRST_NAME - The first name of the SUBSTITUTE CARE PROVIDER.
   */
  @Basic
  @Column(name = "FIRST_NM", nullable = false, length = 20)
  private String firstNm;

  /**
   * FOREIGN_ADDRESS_IND_VAR - This indicator variable is used to indicate if there are any
   * occurrences of FOREIGN_ADDRESSs related to this SUBSTITUTE_CARE_PROVIDER. This will save
   * unnecessary processing time from searching for information that does not exist in the
   * database.
   */
  @Basic
  @Column(name = "FRG_ADRT_B", nullable = false, length = 1)
  private String frgAdrtB;

  /**
   * GENDER_IND - The SUBSTITUTE CARE PROVIDER's characteristic of  being male or female, with
   * permitted values of "M" or "F".
   */
  @Basic
  @Column(name = "GENDER_IND", nullable = false, length = 1)
  private String genderInd;

  /**
   * INDIAN_TRIBE_TYPE - The system generated number assigned to each type of Indian Tribe (e.g.
   * Apache, Sioux, etc) to which the SUBSTITUTE_CARE_PROVIDER is affiliated.
   */
  @Basic
  @Column(name = "IND_TRBC", nullable = false)
  private short indTrbc;

  /**
   * LAST_NAME - The last name of the SUBSTITUTE CARE PROVIDER.
   */
  @Basic
  @Column(name = "LAST_NM", nullable = false, length = 25)
  private String lastNm;

  /**
   * MIDDLE_INITIAL_NAME - The middle initial of the SUBSTITUTE CARE PROVIDER.
   */
  @Basic
  @Column(name = "MID_INI_NM", nullable = false, length = 1)
  private String midIniNm;

  /**
   * NAME_PREFIX_DESCRIPTION - The salutation form to be used in the mailing address of a SUBSTITUTE
   * CARE PROVIDER (e.g., Mr., Ms., Mrs., Dr., Miss, Rev., etc.).
   */
  @Basic
  @Column(name = "NMPRFX_DSC", nullable = false, length = 6)
  private String nmprfxDsc;

  /**
   * SOCIAL_SECURITY_NUMBER - The social security number for the SUBSTITUTE CARE PROVIDER.
   */
  @Basic
  @Column(name = "SS_NO", nullable = false, length = 9)
  private String ssNo;

  /**
   * STATE_CODE_TYPE - The system generated number which identifies the  State for the SUBSTITUTE
   * CARE PROVIDER's mailing  address (e.g., California, Texas, Nevada, etc.).
   */
  @Basic
  @Column(name = "STATE_C", nullable = false)
  private short stateC;

  /**
   * STREET_NAME - The actual name of the street associated with the SUBSTITUTE CARE PROVIDER's
   * address, if different from what is specified on the related PLACEMENT HOME or related FOSTER
   * FAMILY AGENCY.  Do not abbreviate if at all possible for matching purposes.
   */
  @Basic
  @Column(name = "STREET_NM", nullable = false, length = 40)
  private String streetNm;

  /**
   * STREET_NUMBER - The street or house number associated with the street name as part of the
   * SUBSTITUTE CARE PROVIDER's address, if different from what is specified on the related
   * PLACEMENT HOME or related FOSTER FAMILY AGENCY.  This may include the fractional or alphabetic
   * modifier, e.g., A-17, 119-10, 39.2, 100 1/2, etc.
   */
  @Basic
  @Column(name = "STREET_NO", nullable = false, length = 10)
  private String streetNo;

  /**
   * SUFFIX_TITLE_DESCRIPTION - The suffix name of a SUBSTITUTE CARE PROVIDER (e.g., Esq., M.D.,
   * Ph.D., D.D.S., etc.).
   */
  @Basic
  @Column(name = "SUFX_TLDSC", nullable = false, length = 4)
  private String sufxTldsc;

  /**
   * ZIP_NUMBER - The first five digits of the zip code for the  SUBSTITUTE CARE PROVIDER's
   * address.
   */
  @Basic
  @Column(name = "ZIP_NO", nullable = false)
  private int zipNo;

  /**
   * LAST_UPDATE_ID - The ID (a sequential 3 digit base 62 number generated by the system) of the
   * STAFF PERSON or batch program which made the last update to an occurrence of this entity type.
   */
  @Basic
  @Column(name = "LST_UPD_ID", nullable = false, length = 3)
  private String lstUpdId;

  /**
   * LAST_UPDATE_TIMESTAMP - The date and time of the most recent update to an occurrence of this
   * entity type.
   */
  @Basic
  @Column(name = "LST_UPD_TS", nullable = false)
  private LocalDateTime lstUpdTs;

  /**
   * ZIP_SUFFIX_NUMBER - The last four digits of the zip code for a SUBSTITUTE CARE PROVIDER's
   * address.
   */
  @Basic
  @Column(name = "ZIP_SFX_NO", nullable = false)
  private short zipSfxNo;

  /**
   * EDUCATION_TYPE - The system generated number which identifies the educational attainment of the
   * SUBSTITUTE_CARE_PROVIDER.
   */
  @Basic
  @Column(name = "EDUCATION", nullable = false)
  private short education;

  /**
   * EMPLOYMENT_STATUS_TYPE - The system generated number which identifies the employment status of
   * the SUBSTITUTE_CARE_PROVIDER.
   */
  @Basic
  @Column(name = "EMPL_STAT", nullable = false)
  private short emplStat;

  /**
   * PRIMARY_INCOME_TYPE - The system generated number which identifies the primary source of income
   * for the SUBSTITUTE_CARE_PROVIDER.
   */
  @Basic
  @Column(name = "PRIM_INC", nullable = false)
  private short primInc;

  /**
   * SECONDARY_INCOME_TYPE - The system generated number which identifies the secondary source of
   * income for the SUBSTITUTE_CARE_PROVIDER.
   */
  @Basic
  @Column(name = "SEC_INC", nullable = false)
  private short secInc;

  /**
   * ANNUAL_INCOME_AMOUNT - This is the total amount if the substitute care provide who has entered
   * into an Adoption Agreement with a licensed Adoption agency
   */
  @Basic
  @Column(name = "YR_INC_AMT", nullable = false, precision = 2)
  private BigDecimal yrIncAmt;

  /**
   * HISPANIC_ORIGIN_CODE - Notes if the individual is of Hispanic origin.
   */
  @Basic
  @Column(name = "HISP_CD", nullable = false, length = 1)
  private String hispCd;

  /**
   * MARITAL_STATUS_TYPE - Notes the marital status of the Adopting parents: Married, Not Married,
   * Separated at the time of the finalization of the adoption
   */
  @Basic
  @Column(name = "MRTL_STC", nullable = false)
  private short mrtlStc;

  /**
   * LIS_OWNERSHIP_IND - Indicates whether the Substitute Care Provider was brought over from and is
   * therefore owned by LIS. The values permitted are ˆY‰ or ˆN‰.
   */
  @Basic
  @Column(name = "LISOWNIND", nullable = false, length = 1)
  private String lisownind;

  /**
   * LIS_PERSON_ID - This is a unique identifier created by LIS for each Substitute Care Provider
   * they maintain.
   */
  @Basic
  @Column(name = "LIS_PER_ID", nullable = true, length = 10)
  private String lisPerId;

  /**
   * EMAIL_ADDRESS - The e-mail address for the SUBSTITUTE_CARE_PROVIDER.
   */
  @Basic
  @Column(name = "EMAIL_ADDR", nullable = true, length = 50)
  private String emailAddr;

  /**
   * ETH_UNABLE_TO_DET_REASON_CODE - Records the reason that 'Unable to Determine' was selected as
   * the Race/Ethnicity for an individual substitute care provider.
   */
  @Basic
  @Column(name = "ETH_UD_CD", nullable = true, length = 1)
  private String ethUdCd;

  /**
   * HISP_UNABLE_TO_DET_REASON_CODE - Records the reason that 'Unable to Determine' was selected as
   * the Hispanic or Latino Origin for an individual substitute care provider.
   */
  @Basic
  @Column(name = "HISP_UD_CD", nullable = true, length = 1)
  private String hispUdCd;

  /**
   * RESIDED_OUT_OF_STATE_IND - Indicates whether SUBSTITUTE CARE PROVIDER has resided out of the
   * State of California (Y), or has not resided out of the State of California (N) in the last five
   * (5) years.
   */
  @Basic
  @Column(name = "RESOST_IND", nullable = true, length = 1)
  private String resostInd;

  /**
   * PASSED_BACKGROUND_CHECK_CODE - Indicates whether the SUBSTITUTE CARE PROVIDER has passed all
   * background checks (Y), has not passed all background checks (N), or unknown (U).
   */
  @Basic
  @Column(name = "PASSBC_CD", nullable = true, length = 1)
  private String passbcCd;

  @OneToMany
  @JoinColumn(name = "RCPNT_ID")
  @Where(clause = "RCPNT_CD = 'S'") // RECIPIENT_CODE for Substitute Care Providers (S)
  private List<BackgroundCheck> backgroundChecks;


  @OneToMany
  @JoinColumn(name = "RCPNT_ID")
  @Where(clause = "RCPNT_CD = 'S'") // RECIPIENT_CODE for Substitute Care Providers (S)
  private List<OutOfStateCheck> outOfStateChecks;

  public String getIdentifier() {
    return identifier;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }

  public int getAddTelNo() {
    return addTelNo;
  }

  public void setAddTelNo(int addTelNo) {
    this.addTelNo = addTelNo;
  }

  public int getAddExtNo() {
    return addExtNo;
  }

  public void setAddExtNo(int addExtNo) {
    this.addExtNo = addExtNo;
  }

  public LocalDate getBirthDt() {
    return birthDt;
  }

  public void setBirthDt(LocalDate birthDt) {
    this.birthDt = birthDt;
  }

  public String getCaDlicNo() {
    return caDlicNo;
  }

  public void setCaDlicNo(String caDlicNo) {
    this.caDlicNo = caDlicNo;
  }

  public String getCityNm() {
    return cityNm;
  }

  public void setCityNm(String cityNm) {
    this.cityNm = cityNm;
  }

  public String getEmplyrNm() {
    return emplyrNm;
  }

  public void setEmplyrNm(String emplyrNm) {
    this.emplyrNm = emplyrNm;
  }

  public String getFirstNm() {
    return firstNm;
  }

  public void setFirstNm(String firstNm) {
    this.firstNm = firstNm;
  }

  public String getFrgAdrtB() {
    return frgAdrtB;
  }

  public void setFrgAdrtB(String frgAdrtB) {
    this.frgAdrtB = frgAdrtB;
  }

  public String getGenderInd() {
    return genderInd;
  }

  public void setGenderInd(String genderInd) {
    this.genderInd = genderInd;
  }

  public short getIndTrbc() {
    return indTrbc;
  }

  public void setIndTrbc(short indTrbc) {
    this.indTrbc = indTrbc;
  }

  public String getLastNm() {
    return lastNm;
  }

  public void setLastNm(String lastNm) {
    this.lastNm = lastNm;
  }

  public String getMidIniNm() {
    return midIniNm;
  }

  public void setMidIniNm(String midIniNm) {
    this.midIniNm = midIniNm;
  }

  public String getNmprfxDsc() {
    return nmprfxDsc;
  }

  public void setNmprfxDsc(String nmprfxDsc) {
    this.nmprfxDsc = nmprfxDsc;
  }

  public String getSsNo() {
    return ssNo;
  }

  public void setSsNo(String ssNo) {
    this.ssNo = ssNo;
  }

  public short getStateC() {
    return stateC;
  }

  public void setStateC(short stateC) {
    this.stateC = stateC;
  }

  public String getStreetNm() {
    return streetNm;
  }

  public void setStreetNm(String streetNm) {
    this.streetNm = streetNm;
  }

  public String getStreetNo() {
    return streetNo;
  }

  public void setStreetNo(String streetNo) {
    this.streetNo = streetNo;
  }

  public String getSufxTldsc() {
    return sufxTldsc;
  }

  public void setSufxTldsc(String sufxTldsc) {
    this.sufxTldsc = sufxTldsc;
  }

  public int getZipNo() {
    return zipNo;
  }

  public void setZipNo(int zipNo) {
    this.zipNo = zipNo;
  }

  public String getLstUpdId() {
    return lstUpdId;
  }

  public void setLstUpdId(String lstUpdId) {
    this.lstUpdId = lstUpdId;
  }

  public LocalDateTime getLstUpdTs() {
    return lstUpdTs;
  }

  public void setLstUpdTs(LocalDateTime lstUpdTs) {
    this.lstUpdTs = lstUpdTs;
  }

  public short getZipSfxNo() {
    return zipSfxNo;
  }

  public void setZipSfxNo(short zipSfxNo) {
    this.zipSfxNo = zipSfxNo;
  }

  public short getEducation() {
    return education;
  }

  public void setEducation(short education) {
    this.education = education;
  }

  public short getEmplStat() {
    return emplStat;
  }

  public void setEmplStat(short emplStat) {
    this.emplStat = emplStat;
  }

  public short getPrimInc() {
    return primInc;
  }

  public void setPrimInc(short primInc) {
    this.primInc = primInc;
  }

  public short getSecInc() {
    return secInc;
  }

  public void setSecInc(short secInc) {
    this.secInc = secInc;
  }

  public BigDecimal getYrIncAmt() {
    return yrIncAmt;
  }

  public void setYrIncAmt(BigDecimal yrIncAmt) {
    this.yrIncAmt = yrIncAmt;
  }

  public String getHispCd() {
    return hispCd;
  }

  public void setHispCd(String hispCd) {
    this.hispCd = hispCd;
  }

  public short getMrtlStc() {
    return mrtlStc;
  }

  public void setMrtlStc(short mrtlStc) {
    this.mrtlStc = mrtlStc;
  }

  public String getLisownind() {
    return lisownind;
  }

  public void setLisownind(String lisownind) {
    this.lisownind = lisownind;
  }

  public String getLisPerId() {
    return lisPerId;
  }

  public void setLisPerId(String lisPerId) {
    this.lisPerId = lisPerId;
  }

  public String getEmailAddr() {
    return emailAddr;
  }

  public void setEmailAddr(String emailAddr) {
    this.emailAddr = emailAddr;
  }

  public String getEthUdCd() {
    return ethUdCd;
  }

  public void setEthUdCd(String ethUdCd) {
    this.ethUdCd = ethUdCd;
  }

  public String getHispUdCd() {
    return hispUdCd;
  }

  public void setHispUdCd(String hispUdCd) {
    this.hispUdCd = hispUdCd;
  }

  public String getResostInd() {
    return resostInd;
  }

  public void setResostInd(String resostInd) {
    this.resostInd = resostInd;
  }

  public String getPassbcCd() {
    return passbcCd;
  }

  public void setPassbcCd(String passbcCd) {
    this.passbcCd = passbcCd;
  }

  public List<BackgroundCheck> getBackgroundChecks() {
    return backgroundChecks;
  }

  public void setBackgroundChecks(
      List<BackgroundCheck> backgroundChecks) {
    this.backgroundChecks = backgroundChecks;
  }

  public List<OutOfStateCheck> getOutOfStateChecks() {
    return outOfStateChecks;
  }

  public void setOutOfStateChecks(
      List<OutOfStateCheck> outOfStateChecks) {
    this.outOfStateChecks = outOfStateChecks;
  }

  @Override
  public boolean equals(Object o) {
    return EqualsBuilder.reflectionEquals(this, o);
  }

  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this);
  }

  @Override
  public Serializable getPrimaryKey() {
    return getIdentifier();
  }
}