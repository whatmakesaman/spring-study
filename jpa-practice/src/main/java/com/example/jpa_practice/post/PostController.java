package com.example.jpa_practice.post;

import com.example.jpa_practice.member.MemberRepository;
import jakarta.persistence.GeneratedValue;
import jakarta.validation.Valid;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/posts")
public class PostController {


    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }



    @PostMapping
    public ResponseEntity<Void> createPost(@Valid @RequestBody PostCreateRequestDTO request)
    {
        Long postId= postService.createPost(
                request.memberId(),
                request.title(),
                request.content()
                );

        URI location=URI.create("/posts/"+postId);

        return ResponseEntity
                .created(location)
                .build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDTO> findPost(@PathVariable Long id)
    {
        PostResponseDTO response=postService.findPost(id);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<PostResponseDTO>> findAllPosts(
            @RequestParam(required = false) Long memberId
    ){
        List<PostResponseDTO> responses;

        if(memberId==null){
            responses=postService.findAllPosts();
        }
        else{
            responses=postService.findPostsByMemberId(memberId);
        }

        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePost(@PathVariable Long id
            , @Valid @RequestBody PostUpdateRequestDTO request)
    {
        postService.updatePost(id, request.title(), request.content());

        return ResponseEntity
                .noContent()
                .build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id)
    {
        postService.deletePost(id);

        return ResponseEntity
                .noContent()
                .build();
    }

}
