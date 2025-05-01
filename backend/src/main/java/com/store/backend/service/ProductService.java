package com.store.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.backend.entities.Product;
import com.store.backend.repository.ProductRepository;
import com.store.backend.service.exception.ResourceNotFoundException;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        Optional<Product> obj = productRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Product registerProduct(Product product) {
        return productRepository.save(product);
    }
    
    public Product updateProduct(Long id, Product productUpdated) {
        Optional<Product> productOpt = productRepository.findById(id);
        if (productOpt.isPresent()) {
            Product product = productOpt.get();

            product.setName(productUpdated.getName());
            product.setPrice(productUpdated.getPrice());
            product.setStock(productUpdated.getStock());

            return productRepository.save(product);
        } else {
            throw new ResourceNotFoundException(id);
        }
    }


    public void deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException(id);
        }
    }

}
