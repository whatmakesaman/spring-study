package com.example.jpa_practice.post;

public record PostResponseDTO (
        Long id,
        String title,
        String content,
        Long memberId,
        String memberName
){

}
