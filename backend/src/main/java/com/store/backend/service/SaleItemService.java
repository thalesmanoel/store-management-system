package com.store.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.backend.dto.SaleItemDTO;
import com.store.backend.entities.Product;
import com.store.backend.entities.Sale;
import com.store.backend.entities.SaleItem;
import com.store.backend.repository.ProductRepository;
import com.store.backend.repository.SaleItemRepository;
import com.store.backend.repository.SaleRepository;
import com.store.backend.service.exception.ResourceNotFoundException;

@Service
public class SaleItemService {

	@Autowired
    private SaleItemRepository saleItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SaleRepository saleRepository;

    public List<SaleItem> findAll() {
        return saleItemRepository.findAll();
    }

    public SaleItem findById(Long id) {
        return saleItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public SaleItem registerSaleItem(Long saleId, SaleItemDTO dto) {
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException(dto.getProductId()));

        Sale sale = saleRepository.findById(saleId)
                .orElseThrow(() -> new ResourceNotFoundException(saleId));

        SaleItem item = new SaleItem();
        item.setProduct(product);
        item.setSale(sale);
        item.setQuantity(dto.getQuantity());
        item.setUnitPrice(dto.getUnitPrice());

        return saleItemRepository.save(item);
    }

    public SaleItem updateSaleItem(Long id, SaleItemDTO dto) {
        SaleItem item = saleItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException(dto.getProductId()));

        item.setProduct(product);
        item.setQuantity(dto.getQuantity());
        item.setUnitPrice(dto.getUnitPrice());

        return saleItemRepository.save(item);
    }

    public void deleteSaleItem(Long id) {
        if (saleItemRepository.existsById(id)) {
            saleItemRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException(id);
        }
    }

}
