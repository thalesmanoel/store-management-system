package com.store.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.store.backend.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{
}