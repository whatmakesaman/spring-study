package com.example.jpa_practice.post;

import com.example.jpa_practice.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PostRepository extends JpaRepository<Post,Long> {

    List<Post> findAllByMemberId(Long memberId);
}
