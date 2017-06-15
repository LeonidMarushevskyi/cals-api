package gov.ca.cwds.cals.persistence.model.calsns;

import com.fasterxml.jackson.annotation.JsonIgnore;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.AgeGroupType;
import gov.ca.cwds.data.ns.NsPersistentObject;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ChildPreferences.
 */
@Entity
@Table(name = "child_preferences")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ChildPreferences extends NsPersistentObject {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
  @SequenceGenerator(name = "sequenceGenerator")
  private Long id;

  @ManyToMany
  @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
  @JoinTable(name = "child_preferences_age_group_types",
      joinColumns = @JoinColumn(name = "child_preferences_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "age_group_types_id", referencedColumnName = "id"))
  private Set<AgeGroupType> ageGroupTypes = new HashSet<>();

  @ManyToMany
  @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
  @JoinTable(name = "child_preferences_sibling_group_types",
      joinColumns = @JoinColumn(name = "child_preferences_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "sibling_group_types_id", referencedColumnName = "id"))
  private Set<SiblingGroupType> siblingGroupTypes = new HashSet<>();

  @OneToOne(mappedBy = "childPreferences")
  @JsonIgnore
  private Application application;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Set<AgeGroupType> getAgeGroupTypes() {
    return ageGroupTypes;
  }

  public ChildPreferences ageGroupTypes(Set<AgeGroupType> ageGroupTypes) {
    this.ageGroupTypes = ageGroupTypes;
    return this;
  }

  public ChildPreferences addAgeGroupTypes(AgeGroupType ageGroupType) {
    this.ageGroupTypes.add(ageGroupType);
    return this;
  }

  public ChildPreferences removeAgeGroupTypes(AgeGroupType ageGroupType) {
    this.ageGroupTypes.remove(ageGroupType);
    return this;
  }

  public void setAgeGroupTypes(Set<AgeGroupType> ageGroupTypes) {
    this.ageGroupTypes = ageGroupTypes;
  }

  public Set<SiblingGroupType> getSiblingGroupTypes() {
    return siblingGroupTypes;
  }

  public ChildPreferences siblingGroupTypes(Set<SiblingGroupType> siblingGroupTypes) {
    this.siblingGroupTypes = siblingGroupTypes;
    return this;
  }

  public ChildPreferences addSiblingGroupTypes(SiblingGroupType siblingGroupType) {
    this.siblingGroupTypes.add(siblingGroupType);
    return this;
  }

  public ChildPreferences removeSiblingGroupTypes(SiblingGroupType siblingGroupType) {
    this.siblingGroupTypes.remove(siblingGroupType);
    return this;
  }

  public void setSiblingGroupTypes(Set<SiblingGroupType> siblingGroupTypes) {
    this.siblingGroupTypes = siblingGroupTypes;
  }

  public Application getApplication() {
    return application;
  }

  public ChildPreferences application(Application application) {
    this.application = application;
    return this;
  }

  public void setApplication(Application application) {
    this.application = application;
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
    ChildPreferences childPreferences = (ChildPreferences) o;
    if (childPreferences.getId() == null || getId() == null) {
      return false;
    }
    return Objects.equals(getId(), childPreferences.getId());
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