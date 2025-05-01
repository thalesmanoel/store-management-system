package com.store.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.store.backend.entities.Seller;
import com.store.backend.service.SellerService;

@RestController
@RequestMapping(value="/seller")
public class SellerController {
	@Autowired
	private SellerService sellerService;
	
	@GetMapping
	public ResponseEntity<List<Seller>> findAll(){
		List<Seller> list = sellerService.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Seller> findById(@PathVariable("id") Long id){
		Seller obj = sellerService.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
    public ResponseEntity<Seller> registerSeller(@RequestBody Seller seller) {
        Seller createdSeller = sellerService.registerSeller(seller);
        return ResponseEntity.ok(createdSeller);
    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeller(@PathVariable Long id) {
        sellerService.deleteSeller(id);
        return ResponseEntity.noContent().build();
    }
}
