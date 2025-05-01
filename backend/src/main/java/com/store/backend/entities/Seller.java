package com.store.backend.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_seller")
public class Seller extends User{
	private static final long serialVersionUID = 1L;
	
	private Date registrationDate;
	
	@OneToMany(mappedBy = "seller")
	private List<Sale> sales = new ArrayList<>();

	public Seller() {}
	
	public Seller(String name, String email, String password, Date registrationDate) {
		super(name, email, password);
		this.registrationDate = registrationDate;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
}
