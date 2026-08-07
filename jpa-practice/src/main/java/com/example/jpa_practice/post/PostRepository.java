package com.example.jpa_practice.post;

import com.example.jpa_practice.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostRepository extends JpaRepository<Post,Long> {
}
