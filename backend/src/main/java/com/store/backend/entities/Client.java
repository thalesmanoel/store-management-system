package com.store.backend.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_client")
public class Client extends User{
	private static final long serialVersionUID = 1L;
	
	private String cpf;
	@OneToMany(mappedBy = "client")
	private List<Sale> sales = new ArrayList<>();

	
	public Client() {}
	
	public Client(String name, String email, String password, String cpf) {
		super(name, email, password);
		this.cpf = cpf;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
}
