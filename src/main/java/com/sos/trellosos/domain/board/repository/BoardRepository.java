package com.sos.trellosos.domain.board.repository;

import com.sos.trellosos.domain.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board,Long> {
}
