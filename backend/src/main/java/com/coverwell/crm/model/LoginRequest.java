package com.coverwell.crm.model;

public record LoginRequest(
        String email,
        String password
) {
}