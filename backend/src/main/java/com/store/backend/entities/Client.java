package com.store.backend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_client")
public class Client extends User{
	private static final long serialVersionUID = 1L;
	
	@Column(unique = true, nullable = false)
    private String cpf;
	
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
