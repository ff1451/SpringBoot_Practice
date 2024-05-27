package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.MemoryMemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public Member join(Member member) {
        return memberRepository.createMember(member);
    }

    @Override
    public Member getMemberById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("회원 id 조회 실패: "+ id));
    }

    @Override
    public List<Member> getAllMembers() {
        return memberRepository.findAllMember();
    }

    @Override
    public Member updateMember(Member member) {
        if(member.getId()==null) {
            throw new IllegalArgumentException("회원 id 조회 실패");
        }
        return memberRepository.updateMember(member);
    }

    @Override
    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }
}
