package com.store.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.backend.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long>{

}
