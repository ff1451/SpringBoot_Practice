package com.example.demo.service;

import com.example.demo.domain.Member;

import java.util.List;

public interface MemberService {
    Member join(Member member);
    Member getMemberById(Long id);
    List<Member> getAllMembers();
    Member updateMember(Member member);
    void deleteMember(Long id);
}
