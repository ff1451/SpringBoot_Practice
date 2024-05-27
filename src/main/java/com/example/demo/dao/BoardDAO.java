package com.example.demo.dao;

import com.example.demo.domain.Board;

import java.util.List;
import java.util.Optional;

public interface BoardDAO {
    List<Board> getAll();
    Optional<Board> getById(Long id);
}
