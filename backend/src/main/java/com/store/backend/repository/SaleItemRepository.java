package com.store.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.backend.entities.SaleItem;

public interface SaleItemRepository extends JpaRepository<SaleItem, Long>{

}
