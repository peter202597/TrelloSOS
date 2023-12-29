package com.sos.trellosos.domain.board.repository;

import com.sos.trellosos.domain.board.BoardUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardUserRepository extends JpaRepository<BoardUser,Long> {
}
