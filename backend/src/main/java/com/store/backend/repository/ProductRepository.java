package com.store.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.backend.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
