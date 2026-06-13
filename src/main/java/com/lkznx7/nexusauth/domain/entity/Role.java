package com.lkznx7.nexusauth.domain.entity;

import com.lkznx7.nexusauth.domain.enums.RoleType;

import java.util.Set;

public class Role {
    private Long id;
    private RoleType type;
    private String name;
    private Set<Permission> permissions;
}
