package com.halks.distribution_erp.Auth;

import java.io.Serializable;

public record RegisterResponse(long id, String username, String email, String firstName,
                               String lastName) implements Serializable {
}
