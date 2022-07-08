package com.engidash.auth.dto;

public record RegistrationRequestDTO(String firstName,
                                     String lastName,
                                     String username,
                                     String email,
                                     String password) {
}
