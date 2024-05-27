package com.example.demo.dao;

import com.example.demo.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberDAO {
    List<Member> getAll();
    Optional<Member> getById(Long id);
}
