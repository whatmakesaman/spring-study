package com.example.jpa_practice.post;

import com.example.jpa_practice.member.Member;
import com.example.jpa_practice.member.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public PostService(PostRepository postRepository, MemberRepository memberRepository) {
        this.postRepository = postRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Long createPost(
            Long memberId,
            String title,
            String content
    )
    {
        Member member=memberRepository
                .findById(memberId)
                .orElseThrow(
                        ()->new IllegalArgumentException(
                                "회원을 찾을 수 없습니다. id="+memberId
                        )
                );
        Post post=new Post(title,content, member);
        postRepository.save(post);
        return post.getId();
    }

    public PostResponseDTO findPost(Long id){

        Post post=postRepository.findById(id)
                .orElseThrow(
                        ()->new IllegalArgumentException(
                                "게시글을 찾을 수 없습니다 id=" + id)
                        );
        Member member=post.getMember();

        return new PostResponseDTO(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                member.getId(),
                member.getName()
        );
    }

    @Transactional(readOnly = true)
    public Page<PostResponseDTO> findAllPosts(Pageable pageable)
    {

        return postRepository.findAllWithMember(pageable)
                .map(post -> {
                    Member member=post.getMember();

                    return new PostResponseDTO(
                            post.getId(),
                            post.getTitle(),
                            post.getContent(),
                            member.getId(),
                            member.getName()
                    );
                });

    }
    @Transactional
    public void updatePost(
            Long id, String title, String content
    )
    {
        Post post=postRepository.findById(id)
                .orElseThrow(
                        ()->new IllegalArgumentException(
                                "게시글을 찾을 수 없습니다 id="+id
                        )
                );
        post.changeContent(title,content);
    }

    @Transactional
    public void deletePost(Long id)
    {
        Post post=postRepository.findById(id)
                .orElseThrow(
                        ()->new IllegalArgumentException("게시글 찾을 수 없습니다 id="+id)
                );
        postRepository.delete(post);
    }

    public List<PostResponseDTO> findPostsByMemberId(Long memberId)
    {
        return postRepository
                .findAllByMemberId(memberId)
                .stream()
                .map(post->{
                    Member member=post.getMember();

                    return new PostResponseDTO(
                            post.getId(),
                            post.getTitle(),
                            post.getContent(),
                            member.getId(),
                            member.getName()
                    );
                })
                .toList();
    }
}
