package gov.ca.cwds.cals.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * A DTO for the FacilityAddress entity.
 */

public class FacilityAddressDTO extends BaseDTO {

    @JsonProperty("type")
    @ApiModelProperty(required = true, readOnly = true, value = "Address Type", example = "mail")
    private String type;

    private Long id;

    @JsonProperty("address")
    @ApiModelProperty(required = true, readOnly = true, value = "Address Object")
    private AddressDTO address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String addressTypeId) {
        this.type = addressTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FacilityAddressDTO facilityAddressDTO = (FacilityAddressDTO) o;

        if ( ! Objects.equals(id, facilityAddressDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}
