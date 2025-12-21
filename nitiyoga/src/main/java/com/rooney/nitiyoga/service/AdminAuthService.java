package com.rooney.nitiyoga.service;

import org.springframework.stereotype.Service;

@Service
public class AdminAuthService {

    // Backend-only passkey
    private final String adminPasskey = "nitiyoga@2026";

    public boolean verify(String inputPasskey) {
        return adminPasskey.equals(inputPasskey);
    }
}

