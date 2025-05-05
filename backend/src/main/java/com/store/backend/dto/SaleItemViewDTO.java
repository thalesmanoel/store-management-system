package com.store.backend.dto;

public class SaleItemViewDTO {
    private Long saleId;
    private String clientName;
    private String productName;
    private int quantity;
    private double unitPrice;
    private double subtotal;

    public SaleItemViewDTO() {}

    public SaleItemViewDTO(Long saleId, String clientName, String productName,
                           int quantity, double unitPrice, double subtotal) {
        this.saleId = saleId;
        this.clientName = clientName;
        this.productName = productName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.subtotal = subtotal;
    }

    public Long getSaleId() {
        return saleId;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public String toString() {
        return "SaleItemViewDTO{" +
                "saleId=" + saleId +
                ", clientName='" + clientName + '\'' +
                ", productName='" + productName + '\'' +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", subtotal=" + subtotal +
                '}';
    }
}