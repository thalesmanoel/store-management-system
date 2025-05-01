package com.store.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.store.backend.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
}
