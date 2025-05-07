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

import com.store.backend.dto.SaleItemDTO;
import com.store.backend.entities.SaleItem;
import com.store.backend.service.SaleItemService;

@RestController
@RequestMapping(value = "/saleitem")
@CrossOrigin("*")
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

    @PostMapping("/post/{saleId}")
    public ResponseEntity<SaleItem> registerSaleItem(@PathVariable("saleId") Long saleId,
                                                     @RequestBody SaleItemDTO saleItemDTO) {
        SaleItem createdSaleItem = saleItemService.registerSaleItem(saleId, saleItemDTO);
        return ResponseEntity.ok(createdSaleItem);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<SaleItem> update(@PathVariable("id") Long id,
                                           @RequestBody SaleItemDTO saleItemDTO) {
        SaleItem updated = saleItemService.updateSaleItem(id, saleItemDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSaleItem(@PathVariable("id") Long id) {
        saleItemService.deleteSaleItem(id);
        return ResponseEntity.noContent().build();
    }
}
