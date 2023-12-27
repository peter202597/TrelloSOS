package com.sos.trellosos.domain.board.repository;

import com.sos.trellosos.domain.board.BoardUsers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardUserRepository extends JpaRepository<BoardUsers,Long> {
}
