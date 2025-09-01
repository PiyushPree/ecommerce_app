package com.empower.demo.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name="SHOPSY_PRODUCT")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	private String category;
	@Lob
	private String image;
	private Double old_price;
	private Double new_price;
	private Boolean available;
	private String descriptions;
	
	Product(){}

	public Product(Integer id, String name, String category, String image, Double old_price, Double new_price,
			Boolean available, String descriptions) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.image = image;
		this.old_price = old_price;
		this.new_price = new_price;
		this.available = available;
		this.descriptions = descriptions;
	}

	public String getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getimage() {
		return image;
	}

	public void setimage(String image) {
		this.image = image;
	}

	public Double getOld_price() {
		return old_price;
	}

	public void setOld_price(Double old_price) {
		this.old_price = old_price;
	}

	public Double getNew_price() {
		return new_price;
	}

	public void setNew_price(Double new_price) {
		this.new_price = new_price;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", category=" + category + ", image=" + image.length() + ", old_price="
				+ old_price + ", new_price=" + new_price + ", available=" + available + ", descriptions=" + descriptions
				+ "]";
	}

	
	

	
}
