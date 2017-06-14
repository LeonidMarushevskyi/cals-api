package gov.ca.cwds.cals.service.dto.rs;

import gov.ca.cwds.cals.service.dto.FacilityDTO;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;

/**
 * @author CWDS TPT-2
 */
public class ReplicatedFacilityDTO extends FacilityDTO implements ReplicatedDTO {
  private static final long serialVersionUID = 2L;

  @NotNull
  private String replicationOperation;

  @NotNull
  private LocalDateTime replicationDate;

  public String getReplicationOperation() {
    return replicationOperation;
  }

  public void setReplicationOperation(String replicationOperation) {
    this.replicationOperation = replicationOperation;
  }

  public LocalDateTime getReplicationDate() {
    return replicationDate;
  }

  public void setReplicationDate(LocalDateTime replicationDate) {
    this.replicationDate = replicationDate;
  }
}
