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

import com.store.backend.dto.SellerRequestDTO;
import com.store.backend.dto.SellerResponseDTO;
import com.store.backend.service.SellerService;

@RestController
@RequestMapping(value="/api/seller")
@CrossOrigin("*")
public class SellerController {
	@Autowired
    private SellerService sellerService;

    @GetMapping("/get")
    public ResponseEntity<List<SellerResponseDTO>> findAll() {
        List<SellerResponseDTO> list = sellerService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value="/get/{id}")
    public ResponseEntity<SellerResponseDTO> findById(@PathVariable("id") Long id) {
        SellerResponseDTO dto = sellerService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping(value = "/post")
    public ResponseEntity<SellerResponseDTO> registerSeller(@RequestBody SellerRequestDTO dto) {
        SellerResponseDTO created = sellerService.registerSeller(dto);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SellerResponseDTO> update(@PathVariable("id") Long id, @RequestBody SellerRequestDTO dto) {
        SellerResponseDTO updated = sellerService.updateSeller(id, dto);
        return ResponseEntity.ok().body(updated);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSeller(@PathVariable("id") Long id) {
        sellerService.deleteSeller(id);
        return ResponseEntity.noContent().build();
    }
}
