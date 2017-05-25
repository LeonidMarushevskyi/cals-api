package gov.ca.cwds.cals.service.dto;

import java.util.Objects;

/**
 * A DTO for the PersonRace entity.
 */

public class PersonRaceDTO extends BaseDTO {

    private Long id;

    private Long personId;

    private Long raceId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public Long getRaceId() {
        return raceId;
    }

    public void setRaceId(Long raceTypeId) {
        this.raceId = raceTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PersonRaceDTO personRaceDTO = (PersonRaceDTO) o;

        return Objects.equals(id, personRaceDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}
