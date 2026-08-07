package com.example.jpa_practice.member;


import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/members")
public class MemberController{

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<Void> register(@Valid @RequestBody MemberCreateRequestDTO memberCreateRequest)
    {
        Long memberId= memberService.register(
                memberCreateRequest.name(),
                memberCreateRequest.email()
        );

        URI location=URI.create("/members/"+memberId);

        return ResponseEntity
                .created(location)
                .build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDTO> findMember(@PathVariable Long id)
    {
        Member member=memberService.findMember(id);

        MemberResponseDTO memberResponse=new MemberResponseDTO(
                member.getId(),
                member.getName(),
                member.getEmail()
        );
        return ResponseEntity.ok(memberResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateMember(
            @PathVariable Long id,
            @Valid @RequestBody MemberUpdateRequestDTO request
    )
    {
        memberService.updateMember(id, request.name(), request.email());

        return ResponseEntity
                .noContent()
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id)
    {
        memberService.deleteMember(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}
