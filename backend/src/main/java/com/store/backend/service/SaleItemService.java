package com.store.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.backend.entities.SaleItem;
import com.store.backend.repository.SaleItemRepository;
import com.store.backend.service.exception.ResourceNotFoundException;

@Service
public class SaleItemService {

    @Autowired
    private SaleItemRepository saleItemRepository;

    public List<SaleItem> findAll() {
        return saleItemRepository.findAll();
    }

    public SaleItem findById(Long id) {
        Optional<SaleItem> obj = saleItemRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public SaleItem registerSaleItem(SaleItem saleItem) {
        return saleItemRepository.save(saleItem);
    }

    public void deleteSaleItem(Long id) {
        saleItemRepository.deleteById(id);
    }
}
