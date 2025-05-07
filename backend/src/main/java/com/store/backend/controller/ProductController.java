package com.store.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.store.backend.dto.ProductRequestDTO;
import com.store.backend.dto.ProductResponseDTO;
import com.store.backend.service.ProductService;

@RestController
@RequestMapping(value = "/product")
@CrossOrigin("*")
public class ProductController {

	@Autowired
    private ProductService productService;

    @GetMapping("/get")
    public ResponseEntity<List<ProductResponseDTO>> findAll() {
        List<ProductResponseDTO> list = productService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value="/get/{id}")
    public ResponseEntity<ProductResponseDTO> findById(@PathVariable("id") Long id) {
        ProductResponseDTO obj = productService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping(value = "/post")
    public ResponseEntity<ProductResponseDTO> registerProduct(@RequestBody ProductRequestDTO dto) {
        ProductResponseDTO created = productService.registerProduct(dto);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductResponseDTO> update(@PathVariable("id") Long id, @RequestBody ProductRequestDTO dto) {
        ProductResponseDTO updated = productService.updateProduct(id, dto);
        return ResponseEntity.ok().body(updated);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
