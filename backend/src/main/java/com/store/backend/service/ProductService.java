package com.store.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.backend.dto.ProductRequestDTO;
import com.store.backend.dto.ProductResponseDTO;
import com.store.backend.entities.Product;
import com.store.backend.repository.ProductRepository;
import com.store.backend.service.exception.ResourceNotFoundException;

@Service
public class ProductService {

	@Autowired
    private ProductRepository productRepository;

    public List<ProductResponseDTO> findAll() {
        List<Product> products = productRepository.findAll();
        List<ProductResponseDTO> dtos = new ArrayList<>();
        for (Product product : products) {
            dtos.add(new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getRegistrationDate(),
                product.getStock(),
                product.getPrice()
            ));
        }
        return dtos;
    }

    public ProductResponseDTO findById(Long id) {
        Optional<Product> obj = productRepository.findById(id);
        Product product = obj.orElseThrow(() -> new ResourceNotFoundException(id));
        return new ProductResponseDTO(
            product.getId(),
            product.getName(),
            product.getRegistrationDate(),
            product.getStock(),
            product.getPrice()
        );
    }

    public ProductResponseDTO registerProduct(ProductRequestDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setRegistrationDate(dto.getRegistrationDate());
        product.setStock(dto.getStock());
        product.setPrice(dto.getPrice());
        Product saved = productRepository.save(product);

        return new ProductResponseDTO(
            saved.getId(),
            saved.getName(),
            saved.getRegistrationDate(),
            saved.getStock(),
            saved.getPrice()
        );
    }

    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO dto) {
        Optional<Product> productOpt = productRepository.findById(id);
        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            product.setName(dto.getName());
            product.setPrice(dto.getPrice());
            product.setStock(dto.getStock());

            Product updated = productRepository.save(product);

            return new ProductResponseDTO(
                updated.getId(),
                updated.getName(),
                updated.getRegistrationDate(),
                updated.getStock(),
                updated.getPrice()
            );
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
