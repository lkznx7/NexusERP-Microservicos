package com.lkznx7.nexusauth.infrastructure.persistence.repository;

import com.lkznx7.nexusauth.infrastructure.persistence.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaRoleRepository extends JpaRepository<RoleEntity, Long> {
}
