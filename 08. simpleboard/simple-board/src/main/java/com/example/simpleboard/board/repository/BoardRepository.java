package com.example.simpleboard.board.repository;

import com.example.simpleboard.board.db.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
}
