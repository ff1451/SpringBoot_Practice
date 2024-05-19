package com.example.demo.repository;

import com.example.demo.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.Map;

@Repository
public class MemoryMemberRepository implements MemberRepository {

    private static final Map<Long, Member> memberStore = new HashMap<>();
    private static long currentMemberId = 0L;

    @Override
    public Member createMember(Member member) {
        member.setMemberId(++currentMemberId);
        memberStore.put(member.getMemberId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(memberStore.get(id));
    }

    @Override
    public List<Member> findAllMember() {
        return new ArrayList<>(memberStore.values());
    }

    @Override
    public void deleteById(Long id) {
        memberStore.remove(id);
    }

    @Override
    public Member updateMember(Member member) {
        memberStore.put(member.getMemberId(), member);
        return member;
    }
}
