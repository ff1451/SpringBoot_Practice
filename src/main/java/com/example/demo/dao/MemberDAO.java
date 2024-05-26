package com.example.demo.dao;

import com.example.demo.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberDAO {
    Member join(Member member);
    Optional<Member> getMemberById(Long id);
    List<Member> getAllMembers();
    Member updateMember(Member member);
    void deleteMember(Long id);
}
