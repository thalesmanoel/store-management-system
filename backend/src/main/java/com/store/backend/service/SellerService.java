package com.store.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.backend.entities.Seller;
import com.store.backend.repository.SellerRepository;
import com.store.backend.service.exception.ResourceNotFoundException;

@Service
public class SellerService {
	@Autowired
	private SellerRepository sellerRepository;
	
	public List<Seller> findAll(){
		return sellerRepository.findAll();
	}
	
	public Seller findById(Long id) {
		Optional<Seller> obj = sellerRepository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Seller registerSeller(Seller seller) {
        return sellerRepository.save(seller);
    }
    
    public void deleteSeller(Long id) {
        sellerRepository.deleteById(id);
    }
	
}
