package com.kalpak.tworkshop.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the TActivity entity.
 */
public class TActivityDTO implements Serializable {

    private String id;

    private String name;

    private String description;

    private String category;

    private String objective;

    private String concept;

    private Long activityTime;

    private Long totalTime;

    private String tags;

    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public Long getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(Long activityTime) {
        this.activityTime = activityTime;
    }

    public Long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Long totalTime) {
        this.totalTime = totalTime;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TActivityDTO tActivityDTO = (TActivityDTO) o;
        if(tActivityDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tActivityDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TActivityDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", category='" + getCategory() + "'" +
            ", objective='" + getObjective() + "'" +
            ", concept='" + getConcept() + "'" +
            ", activityTime='" + getActivityTime() + "'" +
            ", totalTime='" + getTotalTime() + "'" +
            ", tags='" + getTags() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
