package com.sos.trellosos.domain.board.entity;

import com.sos.trellosos.domain.board.entity.Board;
import com.sos.trellosos.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class BoardUser {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    public BoardUser(Board board, User user) {
        this.user = user;
        this.board = board;
    }

}

