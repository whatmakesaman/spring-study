package com.example.jpa_practice;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record MemberCreateRequest(
        @NotBlank
        String name,

        @NotBlank
        @Email
        String email
){}
