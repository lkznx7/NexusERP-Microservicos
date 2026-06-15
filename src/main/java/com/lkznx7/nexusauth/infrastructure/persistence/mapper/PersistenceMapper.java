package com.lkznx7.nexusauth.infrastructure.persistence.mapper;

import com.lkznx7.nexusauth.domain.entity.Permission;
import com.lkznx7.nexusauth.domain.entity.RefreshToken;
import com.lkznx7.nexusauth.domain.entity.Role;
import com.lkznx7.nexusauth.domain.entity.User;
import com.lkznx7.nexusauth.domain.enums.PermissionType;
import com.lkznx7.nexusauth.domain.enums.RoleType;
import com.lkznx7.nexusauth.domain.enums.UserStatus;
import com.lkznx7.nexusauth.domain.valueobject.Password;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PersistenceMapper {

    public User toDomain(com.lkznx7.nexusauth.infrastructure.persistence.entity.User entity) {
        if (entity == null) return null;
        var user = new User(
                entity.getId(),
                entity.getUsername(),
                entity.getEmail(),
                entity.getPassword() != null ? new Password(entity.getPassword()) : null,
                UserStatus.valueOf(entity.getStatus().name())
        );
        if (entity.getRoles() != null) {
            user.setRoles(entity.getRoles().stream().map(this::toDomain).collect(Collectors.toSet()));
        }
        return user;
    }

    public com.lkznx7.nexusauth.infrastructure.persistence.entity.User toEntity(User domain) {
        if (domain == null) return null;
        var entity = new com.lkznx7.nexusauth.infrastructure.persistence.entity.User();
        entity.setId(domain.getId());
        entity.setUsername(domain.getUsername());
        entity.setEmail(domain.getEmail());
        entity.setPassword(domain.getPassword());
        entity.setStatus(com.lkznx7.nexusauth.infrastructure.persistence.enums.UserStatus.valueOf(domain.getStatus().name()));
        if (domain.getRoles() != null) {
            entity.setRoles(domain.getRoles().stream().map(this::toEntity).collect(Collectors.toSet()));
        }
        return entity;
    }

    public Role toDomain(com.lkznx7.nexusauth.infrastructure.persistence.entity.Role entity) {
        if (entity == null) return null;
        var role = new Role(
                entity.getId(),
                RoleType.valueOf(entity.getType().name()),
                entity.getName()
        );
        if (entity.getPermissions() != null) {
            role.setPermissions(entity.getPermissions().stream().map(this::toDomain).collect(Collectors.toSet()));
        }
        return role;
    }

    public com.lkznx7.nexusauth.infrastructure.persistence.entity.Role toEntity(Role domain) {
        if (domain == null) return null;
        var entity = new com.lkznx7.nexusauth.infrastructure.persistence.entity.Role();
        entity.setId(domain.getId());
        entity.setType(com.lkznx7.nexusauth.infrastructure.persistence.enums.RoleType.valueOf(domain.getType().name()));
        entity.setName(domain.getName());
        if (domain.getPermissions() != null) {
            entity.setPermissions(domain.getPermissions().stream().map(this::toEntity).collect(Collectors.toSet()));
        }
        return entity;
    }

    public Permission toDomain(com.lkznx7.nexusauth.infrastructure.persistence.entity.Permission entity) {
        if (entity == null) return null;
        return new Permission(
                entity.getId(),
                PermissionType.valueOf(entity.getType().name()),
                entity.getName()
        );
    }

    public com.lkznx7.nexusauth.infrastructure.persistence.entity.Permission toEntity(Permission domain) {
        if (domain == null) return null;
        var entity = new com.lkznx7.nexusauth.infrastructure.persistence.entity.Permission();
        entity.setId(domain.getId());
        entity.setType(com.lkznx7.nexusauth.infrastructure.persistence.enums.PermissionType.valueOf(domain.getType().name()));
        entity.setName(domain.getName());
        return entity;
    }

    public RefreshToken toDomain(com.lkznx7.nexusauth.infrastructure.persistence.entity.RefreshToken entity) {
        if (entity == null) return null;
        return new RefreshToken(
                entity.getToken(),
                entity.getUserId(),
                entity.getExpiresAt(),
                entity.isRevoked()
        );
    }

    public com.lkznx7.nexusauth.infrastructure.persistence.entity.RefreshToken toEntity(RefreshToken domain) {
        if (domain == null) return null;
        var entity = new com.lkznx7.nexusauth.infrastructure.persistence.entity.RefreshToken();
        entity.setToken(domain.getToken());
        entity.setUserId(domain.getUserId());
        entity.setExpiresAt(domain.getExpiresAt());
        entity.setRevoked(domain.isRevoked());
        return entity;
    }

    public com.lkznx7.nexusauth.domain.entity.Session toDomain(com.lkznx7.nexusauth.infrastructure.persistence.entity.Session entity) {
        if (entity == null) return null;
        var session = new com.lkznx7.nexusauth.domain.entity.Session();
        session.setId(entity.getId());
        session.setUserId(entity.getUserId());
        session.setStatus(com.lkznx7.nexusauth.domain.enums.SessionStatus.valueOf(entity.getStatus().name()));
        session.setCreatedAt(entity.getCreatedAt());
        session.setExpiresAt(entity.getExpiresAt());
        session.setIpAddress(entity.getIpAddress());
        session.setUserAgent(entity.getUserAgent());
        return session;
    }

    public com.lkznx7.nexusauth.infrastructure.persistence.entity.Session toEntity(com.lkznx7.nexusauth.domain.entity.Session domain) {
        if (domain == null) return null;
        var entity = new com.lkznx7.nexusauth.infrastructure.persistence.entity.Session();
        entity.setId(domain.getId());
        entity.setUserId(domain.getUserId());
        entity.setStatus(com.lkznx7.nexusauth.infrastructure.persistence.enums.SessionStatus.valueOf(domain.getStatus().name()));
        entity.setCreatedAt(domain.getCreatedAt());
        entity.setExpiresAt(domain.getExpiresAt());
        entity.setIpAddress(domain.getIpAddress());
        entity.setUserAgent(domain.getUserAgent());
        return entity;
    }
}
