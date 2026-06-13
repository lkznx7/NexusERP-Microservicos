package com.lkznx7.nexusauth.domain.entity;

import com.lkznx7.nexusauth.domain.enums.UserStatus;
import com.lkznx7.nexusauth.domain.valueobject.Email;
import com.lkznx7.nexusauth.domain.valueobject.Password;
import com.lkznx7.nexusauth.domain.valueobject.UserId;

import java.util.Set;

public class User {
    private UserId id;
    private String username;
    private Email email;
    private Password password;
    private UserStatus status;
    private Set<Role> roles;
}
