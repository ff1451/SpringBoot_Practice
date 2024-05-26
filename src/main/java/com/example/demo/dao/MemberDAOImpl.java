package com.example.demo.dao;

import com.example.demo.domain.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MemberDAOImpl implements MemberDAO{

    private final JdbcTemplate jdbcTemplate;

    public MemberDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Member> memberRowMapper = (resultSet, rowNum) -> new Member(
                resultSet.getString("name"),
                resultSet.getString("email"),
                resultSet.getString("password")
    );

    @Override
    public Member join(Member member) {
        String sql = "INSERT INTO member (name, email, password) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, member.getName(), member.getEmail(), member.getPassword());
        return member;
    }

    @Override
    public Optional<Member> getMemberById(Long id) {
        String sql = "SELECT * FROM member WHERE id = ?";
        try {
            Member member = jdbcTemplate.queryForObject(sql, new Object[]{id}, memberRowMapper);
            return Optional.ofNullable(member);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Member> getAllMembers() {
        String sql = "SELECT * FROM member";
        return jdbcTemplate.query(sql, memberRowMapper);
    }

    @Override
    public Member updateMember(Member member) {
        String sql = "UPDATE member SET name = ?, email = ?, password = ? WHERE id = ?";
        jdbcTemplate.update(sql, member.getName(), member.getEmail(), member.getPassword(), member.getMemberId());
        return member;
    }

    @Override
    public void deleteMember(Long id) {
        String sql = "DELETE FROM member WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
