package com.lkznx7.nexusauth.infrastructure.persistence.repository;

import com.lkznx7.nexusauth.infrastructure.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaUserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String username);
}
