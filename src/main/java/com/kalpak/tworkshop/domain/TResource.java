package com.kalpak.tworkshop.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A TResource.
 */
@Document(collection = "t_resource")
public class TResource implements Serializable {

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
    
    List<Info> infos;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public TResource name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public TResource description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public TResource category(String category) {
        this.category = category;
        return this;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getColor() {
        return color;
    }

    public TResource color(String color) {
        this.color = color;
        return this;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMaterial() {
        return material;
    }

    public TResource material(String material) {
        this.material = material;
        return this;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Long getUnitPrice() {
        return unitPrice;
    }

    public TResource unitPrice(Long unitPrice) {
        this.unitPrice = unitPrice;
        return this;
    }

    public void setUnitPrice(Long unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Long getLength() {
        return length;
    }

    public TResource length(Long length) {
        this.length = length;
        return this;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    public Long getWidth() {
        return width;
    }

    public TResource width(Long width) {
        this.width = width;
        return this;
    }

    public void setWidth(Long width) {
        this.width = width;
    }

    public Long getHeight() {
        return height;
    }

    public TResource height(Long height) {
        this.height = height;
        return this;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    public Long getWeight() {
        return weight;
    }

    public TResource weight(Long weight) {
        this.weight = weight;
        return this;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public Long getInnerId() {
        return innerId;
    }

    public TResource innerId(Long innerId) {
        this.innerId = innerId;
        return this;
    }

    public void setInnerId(Long innerId) {
        this.innerId = innerId;
    }

    public Long getOuterId() {
        return outerId;
    }

    public TResource outerId(Long outerId) {
        this.outerId = outerId;
        return this;
    }

    public void setOuterId(Long outerId) {
        this.outerId = outerId;
    }

    public String getStatus() {
        return status;
    }

    public TResource status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVendor() {
        return vendor;
    }

    public TResource vendor(String vendor) {
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
        TResource tResource = (TResource) o;
        if (tResource.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tResource.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TResource{" +
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

	/**
	 * @return the infos
	 */
	public List<Info> getInfos() {
		return infos;
	}

	/**
	 * @param infos the infos to set
	 */
	public void setInfos(List<Info> infos) {
		this.infos = infos;
	}
    
    
}
