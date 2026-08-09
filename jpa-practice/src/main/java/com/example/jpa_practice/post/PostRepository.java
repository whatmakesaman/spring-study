package com.example.jpa_practice.post;

import com.example.jpa_practice.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post,Long> {

    @Query(value = "select post from Post post join fetch post.member order by post.id desc",
    countQuery = "select count(post) from Post post")
    Page<Post> findAllWithMember(Pageable pageable);


    @Query(
            value="""
                    select post from Post post join fetch post.member 
                    where post.member.id=:memberId
                    order by post.id desc
                  """,
                countQuery = """
                        select count(post)
                        from Post post
                        where post.member.id=:memberId
                        """
    )
    Page<Post> findAllByMemberId(
            @Param("memberId") Long memberId,
            Pageable pageable);
}
