package gov.ca.cwds.cals.persistence.model.calsns;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import gov.ca.cwds.data.ns.NsPersistentObject;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A EducationPoint.
 */
@Entity
@Table(name = "education_point")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EducationPoint extends NsPersistentObject {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
  @SequenceGenerator(name = "sequenceGenerator")
  private Long id;

  @NotNull
  @Column(name = "jhi_degree", nullable = false)
  private String degree;

  @NotNull
  @Column(name = "name", nullable = false)
  private String name;

  @ManyToOne
  private EducationLevelType type;

  @ManyToOne
  private Address educationalInstitutionAddress;

  @ManyToOne
  private Person person;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDegree() {
    return degree;
  }

  public EducationPoint degree(String degree) {
    this.degree = degree;
    return this;
  }

  public void setDegree(String degree) {
    this.degree = degree;
  }

  public String getName() {
    return name;
  }

  public EducationPoint name(String name) {
    this.name = name;
    return this;
  }

  public void setName(String name) {
    this.name = name;
  }

  public EducationLevelType getType() {
    return type;
  }

  public EducationPoint type(EducationLevelType educationLevelType) {
    this.type = educationLevelType;
    return this;
  }

  public void setType(EducationLevelType educationLevelType) {
    this.type = educationLevelType;
  }

  public Address getEducationalInstitutionAddress() {
    return educationalInstitutionAddress;
  }

  public EducationPoint educationalInstitutionAddress(Address address) {
    this.educationalInstitutionAddress = address;
    return this;
  }

  public void setEducationalInstitutionAddress(Address address) {
    this.educationalInstitutionAddress = address;
  }

  public Person getPerson() {
    return person;
  }

  public EducationPoint person(Person person) {
    this.person = person;
    return this;
  }

  public void setPerson(Person person) {
    this.person = person;
  }

  @Override
  public Serializable getPrimaryKey() {
    return getId();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EducationPoint educationPoint = (EducationPoint) o;
    if (educationPoint.getId() == null || getId() == null) {
      return false;
    }
    return Objects.equals(getId(), educationPoint.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(getId());
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
}