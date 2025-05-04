package com.store.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.store.backend.entities.Product;
import com.store.backend.service.ProductService;

@RestController
@RequestMapping(value = "/product")
@CrossOrigin("http://localhost:4200/")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/get")
    public ResponseEntity<List<Product>> findAll() {
        List<Product> list = productService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value="/get/{id}")
    public ResponseEntity<Product> findById(@PathVariable("id") Long id) {
        Product obj = productService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping(value = "/post")
    public ResponseEntity<Product> registerProduct(@RequestBody Product product) {
        Product createdProduct = productService.registerProduct(product);
        return ResponseEntity.ok(createdProduct);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<Product> update(@PathVariable("id") Long id, @RequestBody Product obj) {
        obj = productService.updateProduct(id, obj);
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
