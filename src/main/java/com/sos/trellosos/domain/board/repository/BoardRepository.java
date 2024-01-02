package com.sos.trellosos.domain.board.repository;

import com.sos.trellosos.domain.board.entity.Board;
import com.sos.trellosos.domain.board.entity.BoardUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board,Long> {
    List<Board> findByBoardUsers(BoardUser boardUsers);
}
