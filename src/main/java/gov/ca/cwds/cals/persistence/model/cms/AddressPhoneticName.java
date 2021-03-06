package gov.ca.cwds.cals.persistence.model.cms;

import gov.ca.cwds.data.persistence.PersistentObject;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @author CWDS CALS API Team
 *
 * The converted (phonetic) ADDRESS of the CLIENT or PLACEMENT HOME generated by the SSA-Name3
 * algorithm to be used for Client Search. For each address, there could be up to 25 Phonetic Keys
 * produced by the SSA-Name3 algorithm.
 */
@Entity
@Table(name = "ADR_PHTT")
@IdClass(AddressPhoneticNamePK.class)
@SuppressWarnings({"squid:S3437"}) //LocalDate is serializable
public class AddressPhoneticName implements PersistentObject {

  private static final long serialVersionUID = 1835731644198893633L;

  /**
   * PHONETIC_NAME - The five characters phonetic search key generated by the SSA-Name3 algorithm to
   * be used for Address Search.
   */
  @Id
  @Column(name = "PHONETC_NM", nullable = false, length = 8)
  private String phonetcNm;

  /**
   * PRIMARY_NAME_ID - The logical foreign key representing the ID from the entity which contains
   * the complete name of the ADDRESS for whom the Phonetic Name was defined.
   */
  @Id
  @Column(name = "PRMRY_NMID", nullable = false, length = 10)
  private String prmryNmid;

  /**
   * PRIMARY_NAME_CODE - This code defines each type of address entity for whom a specific Phonetic
   * Name was defined.
   */
  @Id
  @Column(name = "PRMRY_NMCD", nullable = false, length = 1)
  private String prmryNmcd;

  /**
   * GOVERNMENT_ENTITY_TYPE - The system generated number which represents the specific county
   * (e.g., Napa, Sacramento, Fresno, etc.) within the state of California for a specific Address
   */
  @Basic
  @Column(name = "GVR_ENTC", nullable = false)
  private Short gvrEntc;

  /**
   * MATCH_CODE - The Address Match Code is an 8 character code that consists of all the characters
   * entered in the Street Number field (up to 8 characters) plus however many characters from the
   * Street Name field are needed to fill out all 8 characters of the Address Match Code. The
   * Address Match Code will not contain any blanks or non-alphanumeric characters such as /, #, &,
   * or . (period). If the Street Number field is blank, then the Address Match Code will be made up
   * of the first 8 alphanumeric characters of the Street Name field.
   */
  @Basic
  @Column(name = "MATCH_CODE", nullable = false, length = 8)
  private String matchCode;

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

  public String getPhonetcNm() {
    return phonetcNm;
  }

  public void setPhonetcNm(String phonetcNm) {
    this.phonetcNm = phonetcNm;
  }

  public String getPrmryNmid() {
    return prmryNmid;
  }

  public void setPrmryNmid(String prmryNmid) {
    this.prmryNmid = prmryNmid;
  }

  public String getPrmryNmcd() {
    return prmryNmcd;
  }

  public void setPrmryNmcd(String prmryNmcd) {
    this.prmryNmcd = prmryNmcd;
  }

  public Short getGvrEntc() {
    return gvrEntc;
  }

  public void setGvrEntc(Short gvrEntc) {
    this.gvrEntc = gvrEntc;
  }

  public String getMatchCode() {
    return matchCode;
  }

  public void setMatchCode(String matchCode) {
    this.matchCode = matchCode;
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
  public Serializable getPrimaryKey() {
    return new AddressPhoneticNamePK(phonetcNm, prmryNmid, prmryNmcd);
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
