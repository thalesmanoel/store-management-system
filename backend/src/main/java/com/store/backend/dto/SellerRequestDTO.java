package com.store.backend.dto;

import java.util.Date;

public class SellerRequestDTO {

	private String name;
    private String email;
    private String password;
    private Date registrationDate;

    public SellerRequestDTO() {}

    public SellerRequestDTO(String name, String email, String password, Date registrationDate) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.registrationDate = registrationDate;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
}
