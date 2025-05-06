package com.store.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.backend.dto.SellerRequestDTO;
import com.store.backend.dto.SellerResponseDTO;
import com.store.backend.entities.Seller;
import com.store.backend.repository.SellerRepository;
import com.store.backend.service.exception.ResourceNotFoundException;

@Service
public class SellerService {
	@Autowired
    private SellerRepository sellerRepository;

    public List<SellerResponseDTO> findAll() {
        List<Seller> sellers = sellerRepository.findAll();
        List<SellerResponseDTO> dtos = new ArrayList<>();

        for (Seller seller : sellers) {
            dtos.add(toDTO(seller));
        }

        return dtos;
    }

    public SellerResponseDTO findById(Long id) {
        Seller seller = sellerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
        return toDTO(seller);
    }

    public SellerResponseDTO registerSeller(SellerRequestDTO dto) {
        Seller seller = new Seller();
        seller.setName(dto.getName());
        seller.setEmail(dto.getEmail());
        seller.setPassword(dto.getPassword());
        seller.setRegistrationDate(dto.getRegistrationDate());

        Seller saved = sellerRepository.save(seller);
        return toDTO(saved);
    }

    public SellerResponseDTO updateSeller(Long id, SellerRequestDTO dto) {
        Optional<Seller> sellerOpt = sellerRepository.findById(id);

        if (sellerOpt.isPresent()) {
            Seller seller = sellerOpt.get();

            seller.setName(dto.getName());
            seller.setEmail(dto.getEmail());
            seller.setPassword(dto.getPassword());
            seller.setRegistrationDate(dto.getRegistrationDate());

            Seller updated = sellerRepository.save(seller);
            return toDTO(updated);
        } else {
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

    private SellerResponseDTO toDTO(Seller seller) {
        SellerResponseDTO dto = new SellerResponseDTO();
        dto.setId(seller.getId());
        dto.setName(seller.getName());
        dto.setEmail(seller.getEmail());
        dto.setRegistrationDate(seller.getRegistrationDate());
        return dto;
    }

}
