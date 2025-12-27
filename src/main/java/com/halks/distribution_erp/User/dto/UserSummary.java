package com.halks.distribution_erp.User.dto;

public record UserSummary(
        Long id,
        String username,
        String email,
        String firstName,
        String lastName
) { }
