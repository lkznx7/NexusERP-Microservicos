package com.lkznx7.nexusauth.infrastructure.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "permissions")
public class PermissionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
