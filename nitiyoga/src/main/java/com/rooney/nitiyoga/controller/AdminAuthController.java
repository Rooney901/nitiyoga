package com.rooney.nitiyoga.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rooney.nitiyoga.dto.PasskeyRequest;
import com.rooney.nitiyoga.service.AdminAuthService;

@RestController
@RequestMapping("/api/admin")
public class AdminAuthController {

    @Autowired
    private AdminAuthService adminAuthService;

    @PostMapping("/verify-passkey")
    public ResponseEntity<Void> verify(@RequestBody PasskeyRequest request) {
        if (adminAuthService.verify(request.getPasskey())) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}
