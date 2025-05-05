package com.store.backend.dto;

import java.util.List;

public class SaleDTO {
	private Long id;
    private Double total;
    private List<SaleItemDTO> items;

    public SaleDTO() {}

    public SaleDTO(Double total, List<SaleItemDTO> items) {
        this.total = total;
        this.items = items;
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<SaleItemDTO> getItems() {
        return items;
    }

    public void setItems(List<SaleItemDTO> items) {
        this.items = items;
    }
    
    @Override
    public String toString() {
        return "SaleDTO{" +
               ", items=" + items +
               '}';
    }
}
