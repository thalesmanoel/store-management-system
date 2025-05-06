package com.store.backend.dto;

import java.util.Date;

public class ProductResponseDTO {
	private Long id;
    private String name;
    private Date registrationDate;
    private Integer stock;
    private Double price;

    public ProductResponseDTO() {}

    public ProductResponseDTO(Long id, String name, Date registrationDate, Integer stock, Double price) {
        this.id = id;
        this.name = name;
        this.registrationDate = registrationDate;
        this.stock = stock;
        this.price = price;
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

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
