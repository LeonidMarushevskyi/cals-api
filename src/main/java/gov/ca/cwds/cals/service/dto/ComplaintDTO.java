package gov.ca.cwds.cals.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import gov.ca.cwds.rest.api.Response;

import java.time.LocalDate;
import java.util.Objects;

import static gov.ca.cwds.rest.api.domain.DomainObject.DATE_FORMAT;

/**
 * @author CWDS CALS API Team
 */

public class ComplaintDTO extends BaseDTO implements Response {

    @JsonProperty("id")
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
    @gov.ca.cwds.rest.validation.Date(format = DATE_FORMAT, required = false)
    @JsonProperty("complaint_date")
    private LocalDate complaintDate;

    @JsonProperty("assigned_worker")
    private String assignedWorker;

    @JsonProperty("control_number")
    private String controlNumber;

    @JsonProperty("priority_level")
    private Integer priorityLevel;

    @JsonProperty("status")
    private String status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
    @gov.ca.cwds.rest.validation.Date(format = DATE_FORMAT, required = false)
    @JsonProperty("approval_date")
    private LocalDate approvalDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getComplaintDate() {
        return complaintDate;
    }

    public void setComplaintDate(LocalDate complaintDate) {
        this.complaintDate = complaintDate;
    }

    public String getAssignedWorker() {
        return assignedWorker;
    }

    public void setAssignedWorker(String assignedWorker) {
        this.assignedWorker = assignedWorker;
    }

    public String getControlNumber() {
        return controlNumber;
    }

    public void setControlNumber(String controlNumber) {
        this.controlNumber = controlNumber;
    }

    public Integer getPriorityLevel() {
        return priorityLevel;
    }

    public void setPriorityLevel(Integer priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(LocalDate approvalDate) {
        this.approvalDate = approvalDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ComplaintDTO complaintDTO = (ComplaintDTO) o;

        if ( ! Objects.equals(id, complaintDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }


}
