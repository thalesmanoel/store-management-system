package com.store.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.store.backend.entities.Sale;
import com.store.backend.service.SaleService;

@RestController
@RequestMapping(value = "/sale")
@CrossOrigin("http://localhost:4200/")
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

    @PostMapping(value = "/post")
    public ResponseEntity<Sale> registerSale(@RequestBody Sale sale) {
        Sale createdSale = saleService.registerSale(sale);
        return ResponseEntity.ok(createdSale);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSale(@PathVariable("id") Long id) {
        saleService.deleteSale(id);
        return ResponseEntity.noContent().build();
    }
}
