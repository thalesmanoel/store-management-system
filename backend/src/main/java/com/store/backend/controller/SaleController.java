package com.store.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.store.backend.dto.SaleDTO;
import com.store.backend.entities.Sale;
import com.store.backend.service.SaleService;

@RestController
@RequestMapping(value = "/sale")
@CrossOrigin("*")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @GetMapping("/get")
    public ResponseEntity<List<Sale>> findAll() {
        List<Sale> list = saleService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value="/get/{id}")
    public ResponseEntity<Sale> findById(@PathVariable("id") Long id) {
        Sale obj = saleService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping("/post")
    public ResponseEntity<Sale> registerSale(@RequestBody SaleDTO saleDTO) {
        Sale createdSale = saleService.registerSaleFromDTO(saleDTO);
        return ResponseEntity.ok(createdSale);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSale(@PathVariable("id") Long id) {
        saleService.deleteSale(id);
        return ResponseEntity.noContent().build();
    }
}
