package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.MemoryMemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    public Member join(Member member) {
        return memberRepository.createMember(member);
    }

    public Member getMemberById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("회원 id 조회 실패: "+ id));
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAllMember();
    }

    public Member updateMember(Member member) {
        if(member.getId()==null) {
            throw new IllegalArgumentException("회원 id 조회 실패");
        }
        return memberRepository.updateMember(member);
    }

    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }
}
