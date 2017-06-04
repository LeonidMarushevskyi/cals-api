package gov.ca.cwds.cals.persistence.model.cms;

import gov.ca.cwds.data.persistence.PersistentObject;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;

/**
 * @author CWDS CALS API Team
 */
@Entity
@Table(name = "LIC_VSTT")
@SuppressWarnings("squid:S3437") //LocalDate is serializable
public class LicensingVisit implements PersistentObject {

    private static final long serialVersionUID = -8288205929550791793L;

    private String identifier;
    private String lstUpdId;
    private Timestamp lstUpdTs;
    private LocalDate visitDate;
    private String visitNote;
    private VisitType visitType;
    private String fkcntyCst;
    private String cntySpfcd;

    @Id
    @Column(name = "IDENTIFIER", nullable = false, length = 10)
    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Basic
    @Column(name = "LST_UPD_ID", nullable = false, length = 3)
    public String getLstUpdId() {
        return lstUpdId;
    }

    public void setLstUpdId(String lstUpdId) {
        this.lstUpdId = lstUpdId;
    }

    @Basic
    @Column(name = "LST_UPD_TS", nullable = false)
    public Timestamp getLstUpdTs() {
        return lstUpdTs;
    }

    public void setLstUpdTs(Timestamp lstUpdTs) {
        this.lstUpdTs = lstUpdTs;
    }

    @Basic
    @Column(name = "VISIT_DT", nullable = false)
    public LocalDate getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
    }

    @Basic
    @Column(name = "VISIT_NOTE", nullable = false, length = 254)
    public String getVisitNote() {
        return visitNote;
    }

    public void setVisitNote(String visitNote) {
        this.visitNote = visitNote;
    }

    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VISIT_TP", referencedColumnName = "SYS_ID")
    public VisitType getVisitType() {
        return visitType;
    }

    public void setVisitType(VisitType visitType) {
        this.visitType = visitType;
    }

    @Basic
    @Column(name = "FKCNTY_CST", nullable = false, length = 10)
    public String getFkcntyCst() {
        return fkcntyCst;
    }

    public void setFkcntyCst(String fkcntyCst) {
        this.fkcntyCst = fkcntyCst;
    }

    @Basic
    @Column(name = "CNTY_SPFCD", nullable = false, length = 2)
    public String getCntySpfcd() {
        return cntySpfcd;
    }

    public void setCntySpfcd(String cntySpfcd) {
        this.cntySpfcd = cntySpfcd;
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
    @Transient
    public Serializable getPrimaryKey() {
        return getIdentifier();
    }
}
