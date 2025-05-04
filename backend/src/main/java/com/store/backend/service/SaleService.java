package com.store.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.backend.entities.Product;
import com.store.backend.entities.Sale;
import com.store.backend.entities.SaleItem;
import com.store.backend.repository.ProductRepository;
import com.store.backend.repository.SaleRepository;
import com.store.backend.service.exception.ResourceNotFoundException;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;
    
    @Autowired
    private ProductRepository productRepository;

    public List<Sale> findAll() {
        return saleRepository.findAll();
    }

    public Sale findById(Long id) {
        Optional<Sale> obj = saleRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Sale registerSale(Sale sale) {
    	double total = 0.0;
        for (SaleItem item : sale.getItems()) {
            item.setSale(sale); 
            total += item.getSubtotal();

            int currentStock = item.getProduct().getStock();
            item.getProduct().setStock(currentStock - item.getQuantity());
        }

        sale.setTotalPrice(total);

        return saleRepository.save(sale);
    }
    
    public void updateStock(List<SaleItem> saleItems) {
        for (SaleItem saleItem : saleItems) {
            Product product = saleItem.getProduct();
            int updatedQuantity = product.getStock() - saleItem.getQuantity();

            product.setStock(updatedQuantity);
            productRepository.save(product);
        }
    }

    public void deleteSale(Long id) {
        if (saleRepository.existsById(id)) {
            saleRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException(id);
        }
    }

}
