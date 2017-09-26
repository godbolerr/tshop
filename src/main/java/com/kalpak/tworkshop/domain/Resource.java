package com.kalpak.tworkshop.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Resource.
 */
@Document(collection = "resource")
public class Resource implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String id;

    @Field("name")
    private String name;

    @Field("description")
    private String description;

    @Field("category")
    private String category;

    @Field("color")
    private String color;

    @Field("material")
    private String material;

    @Field("unit_price")
    private Long unitPrice;

    @Field("length")
    private Long length;

    @Field("width")
    private Long width;

    @Field("height")
    private Long height;

    @Field("weight")
    private Long weight;

    @Field("inner_id")
    private Long innerId;

    @Field("outer_id")
    private Long outerId;

    @Field("status")
    private String status;

    @Field("vendor")
    private String vendor;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Resource name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public Resource description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public Resource category(String category) {
        this.category = category;
        return this;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getColor() {
        return color;
    }

    public Resource color(String color) {
        this.color = color;
        return this;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMaterial() {
        return material;
    }

    public Resource material(String material) {
        this.material = material;
        return this;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Long getUnitPrice() {
        return unitPrice;
    }

    public Resource unitPrice(Long unitPrice) {
        this.unitPrice = unitPrice;
        return this;
    }

    public void setUnitPrice(Long unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Long getLength() {
        return length;
    }

    public Resource length(Long length) {
        this.length = length;
        return this;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    public Long getWidth() {
        return width;
    }

    public Resource width(Long width) {
        this.width = width;
        return this;
    }

    public void setWidth(Long width) {
        this.width = width;
    }

    public Long getHeight() {
        return height;
    }

    public Resource height(Long height) {
        this.height = height;
        return this;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    public Long getWeight() {
        return weight;
    }

    public Resource weight(Long weight) {
        this.weight = weight;
        return this;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public Long getInnerId() {
        return innerId;
    }

    public Resource innerId(Long innerId) {
        this.innerId = innerId;
        return this;
    }

    public void setInnerId(Long innerId) {
        this.innerId = innerId;
    }

    public Long getOuterId() {
        return outerId;
    }

    public Resource outerId(Long outerId) {
        this.outerId = outerId;
        return this;
    }

    public void setOuterId(Long outerId) {
        this.outerId = outerId;
    }

    public String getStatus() {
        return status;
    }

    public Resource status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVendor() {
        return vendor;
    }

    public Resource vendor(String vendor) {
        this.vendor = vendor;
        return this;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Resource resource = (Resource) o;
        if (resource.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), resource.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Resource{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", category='" + getCategory() + "'" +
            ", color='" + getColor() + "'" +
            ", material='" + getMaterial() + "'" +
            ", unitPrice='" + getUnitPrice() + "'" +
            ", length='" + getLength() + "'" +
            ", width='" + getWidth() + "'" +
            ", height='" + getHeight() + "'" +
            ", weight='" + getWeight() + "'" +
            ", innerId='" + getInnerId() + "'" +
            ", outerId='" + getOuterId() + "'" +
            ", status='" + getStatus() + "'" +
            ", vendor='" + getVendor() + "'" +
            "}";
    }
}
