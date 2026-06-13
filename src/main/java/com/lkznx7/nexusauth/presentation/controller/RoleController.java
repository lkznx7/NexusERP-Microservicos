package com.lkznx7.nexusauth.presentation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {

    @PostMapping("/dummy")
    public ResponseEntity<Void> dummy() {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }
}
