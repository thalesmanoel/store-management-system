package com.store.backend.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_sale")
public class Sale {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Double totalPrice;
	
	@OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<SaleItem> items = new ArrayList<>();
	
	public Sale(){}
	
	public Sale(Double totalPrice, List<SaleItem> items) {
		this.totalPrice = totalPrice;
		this.items = items;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public List<SaleItem> getItems() {
		return items;
	}

	public void setItems(List<SaleItem> items) {
		this.items = items;
	}
}
