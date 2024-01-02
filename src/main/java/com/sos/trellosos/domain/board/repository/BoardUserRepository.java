package com.sos.trellosos.domain.board.repository;

import com.sos.trellosos.domain.board.entity.BoardUser;
import com.sos.trellosos.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardUserRepository extends JpaRepository<BoardUser,Long> {
    List<BoardUser> findByBoardId(Long boardId);
    List<BoardUser> findByUserId(Long userId);
    BoardUser findByUserIdAndBoardId(Long userId,Long boardId);
}