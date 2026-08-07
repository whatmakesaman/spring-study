package com.example.jpa_practice.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PostUpdateRequestDTO(
        @NotBlank
        @Size(max=200)
        String title,

        @NotBlank
        String content
){
}
