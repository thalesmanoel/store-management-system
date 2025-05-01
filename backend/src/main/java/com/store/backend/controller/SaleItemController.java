package com.store.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.store.backend.entities.SaleItem;
import com.store.backend.service.SaleItemService;

@RestController
@RequestMapping(value = "/saleitem")
public class SaleItemController {

    @Autowired
    private SaleItemService saleItemService;

    @GetMapping("/get")
    public ResponseEntity<List<SaleItem>> findAll() {
        List<SaleItem> list = saleItemService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value="/get/{id}")
    public ResponseEntity<SaleItem> findById(@PathVariable("id") Long id) {
        SaleItem obj = saleItemService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping(value = "/post")
    public ResponseEntity<SaleItem> registerSaleItem(@RequestBody SaleItem saleItem) {
        SaleItem createdSaleItem = saleItemService.registerSaleItem(saleItem);
        return ResponseEntity.ok(createdSaleItem);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<SaleItem> update(@PathVariable("id") Long id, @RequestBody SaleItem obj) {
        obj = saleItemService.updateSaleItem(id, obj);
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSaleItem(@PathVariable("id") Long id) {
        saleItemService.deleteSaleItem(id);
        return ResponseEntity.noContent().build();
    }
}
