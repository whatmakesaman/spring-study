package com.example.jpa_practice.member;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record MemberUpdateRequestDTO(

    @NotBlank
    String name,

    @NotBlank
    @Email
    String email
)
{}