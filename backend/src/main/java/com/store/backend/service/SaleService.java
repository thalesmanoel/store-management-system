package com.store.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.backend.dto.SaleDTO;
import com.store.backend.dto.SaleItemDTO;
import com.store.backend.dto.SaleItemViewDTO;
import com.store.backend.entities.Product;
import com.store.backend.entities.Sale;
import com.store.backend.entities.SaleItem;
import com.store.backend.entities.Seller;
import com.store.backend.repository.ProductRepository;
import com.store.backend.repository.SaleRepository;
import com.store.backend.repository.SellerRepository;
import com.store.backend.service.exception.ResourceNotFoundException;

@Service
public class SaleService {

	@Autowired
    private SaleRepository saleRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SellerRepository sellerRepository;
    

    public List<Sale> findAll() {
        return saleRepository.findAll();
    }

    public Sale findById(Long id) {
        Optional<Sale> obj = saleRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Sale registerSaleFromDTO(SaleDTO dto) {
    	
    	System.out.println("Recebido DTO: " + dto);

        Sale sale = new Sale();
        sale.setTotalPrice(0.0); 

        List<SaleItem> saleItems = new ArrayList<>();

        double total = 0.0;

        for (SaleItemDTO itemDTO : dto.getItems()) {
            Product product = productRepository.findById(itemDTO.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException(itemDTO.getProductId()));

            SaleItem item = new SaleItem();
            item.setProduct(product);
            item.setQuantity(itemDTO.getQuantity());
            item.setUnitPrice(itemDTO.getUnitPrice());
            item.setSale(sale); 

            total += item.getSubtotal();

            int updatedStock = product.getStock() - itemDTO.getQuantity();
            if (updatedStock < 0) {
                throw new RuntimeException("Estoque insuficiente para o produto: " + product.getName());
            }
            product.setStock(updatedStock);
            productRepository.save(product);

            saleItems.add(item);
        }

        sale.setItems(saleItems);
        sale.setTotalPrice(total);

        return saleRepository.save(sale);
    }
    
    public List<SaleItemViewDTO> getAllSaleItemsWithClientAndProductNames() {
        List<Sale> sales = saleRepository.findAll();
        List<SaleItemViewDTO> viewDTOList = new ArrayList<>();

        for (Sale sale : sales) {
            for (SaleItem item : sale.getItems()) {
                SaleItemViewDTO dto = new SaleItemViewDTO(
                    sale.getId(),
                    item.getProduct().getName(),
                    item.getQuantity(),
                    item.getUnitPrice(),
                    item.getSubtotal()
                );
                viewDTOList.add(dto);
            }
        }

        return viewDTOList;
    }

    public void deleteSale(Long id) {
        if (saleRepository.existsById(id)) {
            saleRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException(id);
        }
    }

}
