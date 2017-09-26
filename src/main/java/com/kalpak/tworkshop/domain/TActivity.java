package com.kalpak.tworkshop.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.io.Serializable;
import java.util.Objects;

/**
 * A TActivity.
 */
@Document(collection = "t_activity")
public class TActivity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String id;

    @Field("name")
    private String name;

    @Field("description")
    private String description;

    @Field("category")
    private String category;

    @Field("objective")
    private String objective;

    @Field("concept")
    private String concept;

    @Field("activity_time")
    private Long activityTime;

    @Field("total_time")
    private Long totalTime;

    @Field("tags")
    private String tags;

    @Field("status")
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

    public TActivity name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public TActivity description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public TActivity category(String category) {
        this.category = category;
        return this;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getObjective() {
        return objective;
    }

    public TActivity objective(String objective) {
        this.objective = objective;
        return this;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public String getConcept() {
        return concept;
    }

    public TActivity concept(String concept) {
        this.concept = concept;
        return this;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public Long getActivityTime() {
        return activityTime;
    }

    public TActivity activityTime(Long activityTime) {
        this.activityTime = activityTime;
        return this;
    }

    public void setActivityTime(Long activityTime) {
        this.activityTime = activityTime;
    }

    public Long getTotalTime() {
        return totalTime;
    }

    public TActivity totalTime(Long totalTime) {
        this.totalTime = totalTime;
        return this;
    }

    public void setTotalTime(Long totalTime) {
        this.totalTime = totalTime;
    }

    public String getTags() {
        return tags;
    }

    public TActivity tags(String tags) {
        this.tags = tags;
        return this;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getStatus() {
        return status;
    }

    public TActivity status(String status) {
        this.status = status;
        return this;
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
        TActivity tActivity = (TActivity) o;
        if (tActivity.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tActivity.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TActivity{" +
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
