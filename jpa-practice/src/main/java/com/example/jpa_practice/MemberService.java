package com.example.jpa_practice;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Long register(String name, String email){
        Member member=new Member(name, email);
        memberRepository.save(member);
        return member.getId();
    }
    public Member findMember(Long id)
    {
        return memberRepository.findById(id)
                .orElseThrow((
            )->new IllegalArgumentException("회원을 찾을 수 없습니다"+id)
            );
    }

    @Transactional
    public void updateMember(
            Long id,
            String name,
            String email
    )
    {
        Member member=findMember(id);
        member.changeInfo(name,email);
    }

    @Transactional
    public void deleteMember(Long id){
        Member member=findMember(id);

        memberRepository.delete(member);
    }
}
