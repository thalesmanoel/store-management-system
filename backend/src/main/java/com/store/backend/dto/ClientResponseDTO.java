package com.store.backend.dto;

public class ClientResponseDTO {
	private Long id;
    private String name;
    private String email;
    private String cpf;
    
    public ClientResponseDTO() {}
    
	public ClientResponseDTO(Long id, String name, String email, String cpf) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.cpf = cpf;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
}
