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
 * This entity type records the name, date of birth, gender, and other related information for all
 * the other children residing in the PLACEMENT_HOME. This may include all the natural and adopted
 * child(ren) of the SUBSTITUTE_CARE_PROVIDER but does not include CWS child(ren).
 */
@Entity
@Table(name = "OTH_KIDT")
@SuppressWarnings({"squid:S3437"}) //LocalDate is serializable
public class OtherChildrenInPlacementHome implements PersistentObject {

  private static final long serialVersionUID = -2605995189487561419L;

  /**
   * ID - A system generated number used to uniquely identify each OTHER_CHILDREN_IN_PLACEMENT_HOME.
   * This ID is composed of a base 62 Creation Timestamp and the STAFF_PERSON ID (a sequential 3
   * digit base 62 number generated by the system). This value eliminates the need for an additional
   * set of Creation Timestamp and Creation User ID which is needed to satisfy the Audit Trail
   * requirement.
   */
  @Id
  @Column(name = "IDENTIFIER", nullable = false, length = 10)
  private String identifier;

  /**
   * BIRTH_DATE - Date of birth of the other child residing in the PLACEMENT HOME.
   */
  @Basic
  @Column(name = "BIRTH_DT", nullable = true)
  private LocalDate birthDt;

  /**
   * GENDER_CODE - Indicates the gender of the other child residing in the PLACEMENT_HOME (e.g. F =
   * female, M = male).
   */
  @Basic
  @Column(name = "GENDER_CD", nullable = false, length = 1)
  private String genderCd;

  /**
   * NAME - The complete name of the other child residing in the PLACEMENT HOME.
   */
  @Basic
  @Column(name = "OTHCHLD_NM", nullable = false, length = 35)
  private String othchldNm;

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

  /**
   * FKPLC_HM_T - Mandatory Foreign key that RESIDES_IN a PLACEMENT_HOME.
   */
  @Basic
  @Column(name = "FKPLC_HM_T", nullable = false, length = 10)
  private String fkplcHmT;

  /**
   * ANNUAL_UNEARNED_INCOME_AMOUNT - This is the annual gross income of other children in the
   * adoptive home from AAP.
   */
  @Basic
  @Column(name = "YR_INC_AMT", nullable = true, precision = 2)
  private BigDecimal yrIncAmt;

  @OneToMany
  @JoinColumn(name = "FKOTH_KIDT")
  private List<OtherPeopleScpRelationship> otherPeopleScpRelationships;

  @OneToMany
  @JoinColumn(name = "RCPNT_ID")
  @Where(clause = "RCPNT_CD = 'H'") // RECIPIENT_CODE for Other Children in Placement Home (H)
  private List<BackgroundCheck> backgroundChecks;

  public String getIdentifier() {
    return identifier;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }

  public LocalDate getBirthDt() {
    return birthDt;
  }

  public void setBirthDt(LocalDate birthDt) {
    this.birthDt = birthDt;
  }

  public String getGenderCd() {
    return genderCd;
  }

  public void setGenderCd(String genderCd) {
    this.genderCd = genderCd;
  }

  public String getOthchldNm() {
    return othchldNm;
  }

  public void setOthchldNm(String othchldNm) {
    this.othchldNm = othchldNm;
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

  public String getFkplcHmT() {
    return fkplcHmT;
  }

  public void setFkplcHmT(String fkplcHmT) {
    this.fkplcHmT = fkplcHmT;
  }

  public BigDecimal getYrIncAmt() {
    return yrIncAmt;
  }

  public void setYrIncAmt(BigDecimal yrIncAmt) {
    this.yrIncAmt = yrIncAmt;
  }

  public List<OtherPeopleScpRelationship> getOtherPeopleScpRelationships() {
    return otherPeopleScpRelationships;
  }

  public void setOtherPeopleScpRelationships(
      List<OtherPeopleScpRelationship> otherPeopleScpRelationships) {
    this.otherPeopleScpRelationships = otherPeopleScpRelationships;
  }

  public List<BackgroundCheck> getBackgroundChecks() {
    return backgroundChecks;
  }

  public void setBackgroundChecks(
      List<BackgroundCheck> backgroundChecks) {
    this.backgroundChecks = backgroundChecks;
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
