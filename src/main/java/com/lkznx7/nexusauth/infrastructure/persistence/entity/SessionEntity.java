package com.lkznx7.nexusauth.infrastructure.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "sessions")
public class SessionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
