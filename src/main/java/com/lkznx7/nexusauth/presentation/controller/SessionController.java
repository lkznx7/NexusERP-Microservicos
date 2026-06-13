package com.lkznx7.nexusauth.presentation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sessions")
public class SessionController {

    @PostMapping
    public ResponseEntity<Void> create() {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }
}
