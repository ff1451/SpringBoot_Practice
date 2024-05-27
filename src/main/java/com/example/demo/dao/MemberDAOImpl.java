package com.example.demo.dao;

import com.example.demo.domain.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class MemberDAOImpl implements MemberDAO{

    private final JdbcTemplate jdbcTemplate;

    public MemberDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final Map<Long, Member> members = new HashMap<>();
    private static final AtomicLong autoincrement = new AtomicLong(1);

    private final RowMapper<Member> memberRowMapper = (resultSet, rowNum) -> new Member(
                resultSet.getString("name"),
                resultSet.getString("email"),
                resultSet.getString("password")
    );


    @Override
    public List<Member> getAll() {
        String sql = "SELECT * FROM member";
        return jdbcTemplate.query(sql, memberRowMapper);
    }

    @Override
    public Optional<Member> getById(Long id) {
        String sql = "SELECT * FROM member WHERE id = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, memberRowMapper, id));
    }
}
