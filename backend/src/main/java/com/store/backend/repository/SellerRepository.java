package com.store.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.backend.entities.Seller;

public interface SellerRepository extends JpaRepository<Seller, Long>{

}
