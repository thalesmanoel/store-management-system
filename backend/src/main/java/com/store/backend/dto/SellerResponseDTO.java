package com.store.backend.dto;

import java.util.Date;

public class SellerResponseDTO {

	private Long id;
    private String name;
    private String email;
    private Date registrationDate;

    public SellerResponseDTO() {}

    public SellerResponseDTO(Long id, String name, String email, Date registrationDate) {

		this.id = id;
		this.name = name;
		this.email = email;
		this.registrationDate = registrationDate;
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

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
}
