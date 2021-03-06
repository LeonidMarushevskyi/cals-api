package gov.ca.cwds.cals.service.dto;

import static gov.ca.cwds.rest.api.domain.DomainObject.DATE_FORMAT;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @author CWDS CALS API Team
 */
@SuppressWarnings("squid:S3437")   //LocalDateTime is serializable
public class FacilityVisitDTO extends BaseDTO {

    @JsonProperty("visit_type")
    @ApiModelProperty(required = false, readOnly = false, value = "VisitType", example = "Renewal")
    private String visitType;

    @ApiModelProperty(required = false, readOnly = false, value = "Approval", example = "Approved")
    private String approval;

    @JsonProperty("visit_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
    @gov.ca.cwds.rest.validation.Date(format = DATE_FORMAT, required = false)
    @ApiModelProperty(required = false, readOnly = false, value = "yyyy-MM-dd", example = "2000-01-01")
    private LocalDateTime visitDate;

    @JsonProperty("visit_deferred_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
    @gov.ca.cwds.rest.validation.Date(format = DATE_FORMAT, required = false)
    @ApiModelProperty(required = false, readOnly = false, value = "yyyy-MM-dd", example = "2000-01-01")
    private LocalDateTime visitDeferredDate;

    public String getVisitType() {
        return visitType;
    }

    public void setVisitType(String visitType) {
        this.visitType = visitType;
    }

    public String getApproval() {
        return approval;
    }

    public void setApproval(String approval) {
        this.approval = approval;
    }

    public LocalDateTime getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDateTime visitDate) {
        this.visitDate = visitDate;
    }

    public LocalDateTime getVisitDeferredDate() {
        return visitDeferredDate;
    }

    public void setVisitDeferredDate(LocalDateTime visitDeferredDate) {
        this.visitDeferredDate = visitDeferredDate;
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
