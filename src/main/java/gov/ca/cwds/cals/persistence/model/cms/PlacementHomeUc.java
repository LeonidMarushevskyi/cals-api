package gov.ca.cwds.cals.persistence.model.cms;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.joda.time.LocalDateTime;

/**
 * @author CWDS CALS API Team
 */
@Entity
@Table(name = "PLCHM_UC")
public class PlacementHomeUc {

  
  @Id
  @Column(name = "PKPLC_HMT", nullable = false, length = 10)
  private String pkplcHmt;

  @Basic
  @Column(name = "CITY_NM", nullable = false, length = 20)
  private String cityNm;

  @Basic
  @Column(name = "GEO_RGNTCD", nullable = false, length = 2)
  private String geoRgntcd;

  @Basic
  @Column(name = "LA_VNDR_ID", nullable = false, length = 6)
  private String laVndrId;

  @Basic
  @Column(name = "LST_UPD_ID", nullable = false, length = 3)
  private String lstUpdId;

  @Basic
  @Column(name = "LST_UPD_TS", nullable = false)
  private LocalDateTime lstUpdTs;

  @Basic
  @Column(name = "LICENSE_NO", nullable = false, length = 9)
  private String licenseNo;

  @Basic
  @Column(name = "FACLTY_NM", nullable = false, length = 50)
  private String facltyNm;

  @Basic
  @Column(name = "STREET_NO", nullable = false, length = 10)
  private String streetNo;

  @Basic
  @Column(name = "STREET_NM", nullable = false, length = 40)
  private String streetNm;

  public String getPkplcHmt() {
    return pkplcHmt;
  }

  public void setPkplcHmt(String pkplcHmt) {
    this.pkplcHmt = pkplcHmt;
  }

  public String getCityNm() {
    return cityNm;
  }

  public void setCityNm(String cityNm) {
    this.cityNm = cityNm;
  }

  public String getGeoRgntcd() {
    return geoRgntcd;
  }

  public void setGeoRgntcd(String geoRgntcd) {
    this.geoRgntcd = geoRgntcd;
  }

  public String getLaVndrId() {
    return laVndrId;
  }

  public void setLaVndrId(String laVndrId) {
    this.laVndrId = laVndrId;
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

  public String getLicenseNo() {
    return licenseNo;
  }

  public void setLicenseNo(String licenseNo) {
    this.licenseNo = licenseNo;
  }

  public String getFacltyNm() {
    return facltyNm;
  }

  public void setFacltyNm(String facltyNm) {
    this.facltyNm = facltyNm;
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

  @Override
  public boolean equals(Object o) {
    return EqualsBuilder.reflectionEquals(this, o);
  }

  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this);
  }
}
