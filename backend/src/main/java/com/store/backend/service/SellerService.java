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
	
	public Seller updateSeller(Long id, Seller sellerUpdated) {
        Optional<Seller> sellerOpt = sellerRepository.findById(id);
        if (sellerOpt.isPresent()) {
            Seller seller = sellerOpt.get();
            
            seller.setName(sellerUpdated.getName());
            seller.setEmail(sellerUpdated.getEmail());
            seller.setPassword(sellerUpdated.getPassword());
            seller.setRegistrationDate(sellerUpdated.getRegistrationDate());
            
            return sellerRepository.save(seller);
        }
        else 
        {
        	throw new ResourceNotFoundException(id);
        }
    }
    
	public void deleteSeller(Long id) {
	    if (sellerRepository.existsById(id)) {
	        sellerRepository.deleteById(id);
	    } else {
	        throw new ResourceNotFoundException(id);
	    }
	}

}
