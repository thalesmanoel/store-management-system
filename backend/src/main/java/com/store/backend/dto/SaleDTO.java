package com.store.backend.dto;

import java.util.List;

public class SaleDTO {
	private Long id;
    private Long clientId;
    private Long sellerId;
    private Double total;
    private List<SaleItemDTO> items;

    public SaleDTO() {}

    public SaleDTO(Long clientId, Long sellerId, Double total, List<SaleItemDTO> items) {
        this.clientId = clientId;
        this.sellerId = sellerId;
        this.total = total;
        this.items = items;
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
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
               "clientId=" + clientId +
               ", sellerId=" + sellerId +
               ", items=" + items +
               '}';
    }
}
