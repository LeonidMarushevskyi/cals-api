package gov.ca.cwds.cals.persistence.model.cms;

import gov.ca.cwds.data.persistence.PersistentObject;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @author CWDS CALS API Team
 *
 * Phone contact information that includes phone numbers for different phone types such as home,
 * alternate, cellular, work, and fax.
 */
@Entity
@Table(name = "PH_CNT_T")
@SuppressWarnings({"squid:S3437"}) //LocalDate is serializable
public class PhoneContactDetail implements PersistentObject {

  private static final long serialVersionUID = -1631223734962642690L; //NOSONAR

  /**
   * THIRD_ID - This is a system generated unique number that supplements user supplied data in the
   * primary key. The Third ID attribute will be used as part of the new Primary Key in combination
   * with user supplied data. It will also be used alone as a separate key for direct access. This
   * Third ID is composed of a base 62 Creation Timestamp and the STAFF PERSON ID (a sequential 3
   * digit base 62 number generated by the system).
   */
  @Id
  @Column(name = "THIRD_ID", nullable = false, length = 10)
  private String thirdId;

  /**
   * PHONE_NUMBER - The area code and phone number for the Substitute Care Provider.
   */
  @Basic
  @Column(name = "PHONE_NO", nullable = false, precision = 0)
  private long phoneNo;

  /**
   * PHONE_EXTENSION_NUMBER - The phone extension number for the Substitute Care Provider.
   */
  @Basic
  @Column(name = "PHEXT_NO", nullable = true)
  private Integer phextNo;

  /**
   * PHONE_NUMBER_TYPE_CODE - This code represents the type of phone number. This value can be Home
   * (H), Alternate (A), Cellular (C), Work (W), or Fax (F).
   */
  @Basic
  @Column(name = "PHN_TYP_CD", nullable = false, length = 1)
  private String phnTypCd;

  /**
   * ESTABLISHED_FOR_CODE - This code defines each type of source entity for which this PHONE
   * CONTACT DETAIL pertains (e.g. S = SUBSTITUTE CARE PROVIDER).
   */
  @Basic
  @Column(name = "ESTBLSH_CD", nullable = false, length = 1)
  private String estblshCd;

  /**
   * ESTABLISHED_FOR_ID - The logical foreign key representing the ID from the source entity for
   * which this particular PHONE CONTACT DETAIL pertains (e.g. the ID from the SUBSTITUTE CARE
   * PROVIDER entity type)
   */
  @Basic
  @Column(name = "ESTBLSH_ID", nullable = false, length = 10)
  private String estblshId;

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

  public String getThirdId() {
    return thirdId;
  }

  public void setThirdId(String thirdId) {
    this.thirdId = thirdId;
  }

  public long getPhoneNo() {
    return phoneNo;
  }

  public void setPhoneNo(long phoneNo) {
    this.phoneNo = phoneNo;
  }

  public Integer getPhextNo() {
    return phextNo;
  }

  public void setPhextNo(Integer phextNo) {
    this.phextNo = phextNo;
  }

  public String getPhnTypCd() {
    return phnTypCd;
  }

  public void setPhnTypCd(String phnTypCd) {
    this.phnTypCd = phnTypCd;
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

  @Override
  public Serializable getPrimaryKey() {
    return getThirdId();
  }
}
