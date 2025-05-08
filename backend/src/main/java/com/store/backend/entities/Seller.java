package com.store.backend.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_seller")
public class Seller extends User{
	private static final long serialVersionUID = 1L;
	
	@Column(nullable = false)
    private Date registrationDate;

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
