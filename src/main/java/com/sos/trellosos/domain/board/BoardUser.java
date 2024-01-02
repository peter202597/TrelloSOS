package com.sos.trellosos.domain.board;

import com.sos.trellosos.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class BoardUser {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "User_Id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "Board_Id")
    private Board board;

    public BoardUser(Board board, User user) {
        this.user = user;
        this.board = board;
    }

}

