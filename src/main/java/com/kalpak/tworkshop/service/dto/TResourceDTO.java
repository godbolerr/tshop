package com.kalpak.tworkshop.service.dto;


import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import com.kalpak.tworkshop.domain.Info;

/**
 * A DTO for the TResource entity.
 */
public class TResourceDTO implements Serializable {

    private String id;

    private String name;

    private String description;

    private String category;

    private String color;

    private String material;

    private Long unitPrice;

    private Long length;

    private Long width;

    private Long height;

    private Long weight;

    private Long innerId;

    private Long outerId;

    private String status;

    private String vendor;
    
    private List<Info> infos;

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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Long getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Long unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    public Long getWidth() {
        return width;
    }

    public void setWidth(Long width) {
        this.width = width;
    }

    public Long getHeight() {
        return height;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public Long getInnerId() {
        return innerId;
    }

    public void setInnerId(Long innerId) {
        this.innerId = innerId;
    }

    public Long getOuterId() {
        return outerId;
    }

    public void setOuterId(Long outerId) {
        this.outerId = outerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVendor() {
        return vendor;
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

        TResourceDTO tResourceDTO = (TResourceDTO) o;
        if(tResourceDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tResourceDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TResourceDTO{" +
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
