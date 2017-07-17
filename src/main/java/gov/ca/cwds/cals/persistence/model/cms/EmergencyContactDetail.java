package gov.ca.cwds.cals.persistence.model.cms;

import java.time.LocalDateTime;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/** @author CWDS CALS API Team */
@Entity
@Table(name = "EM_CNT_T")
public class EmergencyContactDetail {

  /**
   * ID - A system generated number used to uniquely identify each EMERGENCY CONTACT DETAIL. This ID
   * is composed of a base 62 Creation Timestamp and the STAFF PERSON ID (a sequential 3 digit base
   * 62 number generated by the system). This value eliminates the need for an additional set of
   * Creation Timestamp and Creation User ID which is needed to satisfy the Audit Trail requirement.
   */
  @Id
  @Column(name = "IDENTIFIER", nullable = false, length = 10)
  private String identifier;

  /**
   * ESTABLISHED_FOR_CODE - This code defines each type of source entity for which this EMERGENCY
   * CONTACT DETAIL pertains (e.g. P = PLACEMENT HOME, G = GROUP HOME ORGANIZATION, or N =
   * NON-FOSTER CARE PLACEMENT).
   */
  @Basic
  @Column(name = "ESTBLSH_CD", nullable = false, length = 1)
  private String estblshCd;

  /**
   * ESTABLISHED_FOR_ID - The logical foreign key representing the ID from the source entity for
   * which this particular EMERGENCY CONTACT DETAIL pertains (e.g. the ID from the PLACEMENT HOME,
   * GROUP HOME ORGANIZATION, or NON-FOSTER CARE PLACEMENT entity type)
   */
  @Basic
  @Column(name = "ESTBLSH_ID", nullable = false, length = 10)
  private String estblshId;

  /** CONTACT_NAME - The name of the person to contact in case of emergency. */
  @Basic
  @Column(name = "CNTCT_NME", nullable = true, length = 35)
  private String cntctNme;

  /**
   * PRIMARY_PHONE_NUMBER - The phone number where the contact person can be reached in event of an
   * emergency.
   */
  @Basic
  @Column(name = "PRI_PH_NO", nullable = true, precision = 0)
  private Integer priPhNo;

  /**
   * PRIMARY_PHONE_EXTENSION_NUMBER - The phone number extension where the contact person can be
   * reached in event of an emergency.
   */
  @Basic
  @Column(name = "PRI_PH_EXT", nullable = true)
  private Integer priPhExt;

  /**
   * ALTERNATE_PHONE_NUMBER - The alternate phone number where the contact person can be reached in
   * event of an emergency.
   */
  @Basic
  @Column(name = "ALT_PH_NO", nullable = true, precision = 0)
  private Integer altPhNo;

  /**
   * ALTERNATE_PHONE_EXTENSION_NUMBER - The alternate phone number extension where the contact
   * person can be reached in event of an emergency.
   */
  @Basic
  @Column(name = "ALT_PH_EXT", nullable = true)
  private Integer altPhExt;

  /** EMAIL_ADDRESS - The e-mail address for the EMERGENCY CONTACT person. */
  @Basic
  @Column(name = "EMAIL_ADDR", nullable = true, length = 50)
  private String emailAddr;

  /**
   * STREET_NUMBER - The street or house number associated with the street name as part of the
   * EMERGENCY CONTACT person's address. This may include the fractional or alphabetic modifier,
   * e.g., A-17, 119-10, 39.2, 100 ½, etc.
   */
  @Basic
  @Column(name = "STREET_NO", nullable = true, length = 10)
  private String streetNo;

  /**
   * STREET_NAME - The actual name of the street associated with the EMERGENCY CONTACT person's
   * address.
   */
  @Basic
  @Column(name = "STREET_NM", nullable = true, length = 40)
  private String streetNm;

  /** CITY_NAME - City name for the emergency contact. */
  @Basic
  @Column(name = "CITY_NM", nullable = true, length = 20)
  private String cityNm;

  /**
   * STATE_CODE_TYPE - The system generated number that identifies the state where the EMERGENCY
   * CONTACT person is located (e.g., California, Texas, Nevada, etc.).
   */
  @Basic
  @Column(name = "STATE_C", nullable = true)
  private Short stateC;

  /**
   * ZIP_NUMBER - The first five digits of the zip code for the EMERGENCY CONTACT person's address.
   */
  @Basic
  @Column(name = "ZIP_NO", nullable = true)
  private Integer zipNo;

  /**
   * ZIP_SUFFIX_NUMBER - The last four digits of the zip code for the EMERGENCY CONTACT person's
   * address.
   */
  @Basic
  @Column(name = "ZIP_SFX_NO", nullable = true)
  private Short zipSfxNo;

  /**
   * FOREIGN_ADDRESS_IND_VAR - This indicator variable is used to indicate if there are any
   * occurrences of FOREIGN ADDRESSes related to this EMERGENCY CONTACT DETAIL. This will save
   * unnecessary processing time from searching for information that does not exist in the database.
   */
  @Basic
  @Column(name = "FRG_ADRT_B", nullable = false, length = 1)
  private String frgAdrtB;

  /**
   * LAST_UPDATE_ID - The ID (a sequential 3 digit base 62 number generated by the system) of the
   * STAFF PERSON or batch program which made the last update to an occurrence of this entity type.
   */
  @Basic
  @Column(name = "LST_UPD_ID", nullable = false, length = 3)
  private String lstUpdId;

  /**
   * LAST_UPDATE_TIMESTAMP - The time and date of the most recent update to an occurrence of this
   * entity type.
   */
  @Basic
  @Column(name = "LST_UPD_TS", nullable = false)
  private LocalDateTime lstUpdTs;

  public String getIdentifier() {
    return identifier;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }

  public String getEstblshCd() {
    return estblshCd;
  }

  public void setEstblshCd(String estblshCd) {
    this.estblshCd = estblshCd;
  }

  public String getEstblshId() {
    return estblshId;
  }

  public void setEstblshId(String estblshId) {
    this.estblshId = estblshId;
  }

  public String getCntctNme() {
    return cntctNme;
  }

  public void setCntctNme(String cntctNme) {
    this.cntctNme = cntctNme;
  }

  public Integer getPriPhNo() {
    return priPhNo;
  }

  public void setPriPhNo(Integer priPhNo) {
    this.priPhNo = priPhNo;
  }

  public Integer getPriPhExt() {
    return priPhExt;
  }

  public void setPriPhExt(Integer priPhExt) {
    this.priPhExt = priPhExt;
  }

  public Integer getAltPhNo() {
    return altPhNo;
  }

  public void setAltPhNo(Integer altPhNo) {
    this.altPhNo = altPhNo;
  }

  public Integer getAltPhExt() {
    return altPhExt;
  }

  public void setAltPhExt(Integer altPhExt) {
    this.altPhExt = altPhExt;
  }

  public String getEmailAddr() {
    return emailAddr;
  }

  public void setEmailAddr(String emailAddr) {
    this.emailAddr = emailAddr;
  }

  public String getStreetNo() {
    return streetNo;
  }

  public void setStreetNo(String streetNo) {
    this.streetNo = streetNo;
  }

  public String getStreetNm() {
    return streetNm;
  }

  public void setStreetNm(String streetNm) {
    this.streetNm = streetNm;
  }

  public String getCityNm() {
    return cityNm;
  }

  public void setCityNm(String cityNm) {
    this.cityNm = cityNm;
  }

  public Short getStateC() {
    return stateC;
  }

  public void setStateC(Short stateC) {
    this.stateC = stateC;
  }

  public Integer getZipNo() {
    return zipNo;
  }

  public void setZipNo(Integer zipNo) {
    this.zipNo = zipNo;
  }

  public Short getZipSfxNo() {
    return zipSfxNo;
  }

  public void setZipSfxNo(Short zipSfxNo) {
    this.zipSfxNo = zipSfxNo;
  }

  public String getFrgAdrtB() {
    return frgAdrtB;
  }

  public void setFrgAdrtB(String frgAdrtB) {
    this.frgAdrtB = frgAdrtB;
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

  @Override
  public boolean equals(Object o) {
    return EqualsBuilder.reflectionEquals(this, o);
  }

  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this);
  }
}
