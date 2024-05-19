package com.example.demo.repository;

import com.example.demo.domain.Member;

import java.util.List;
import java.util.Optional;



public interface MemberRepository {
    Member createMember(Member member);
    Optional<Member> findById(Long id);
    List<Member> findAllMember();
    void deleteById(Long id);
    Member updateMember(Member member);
}
