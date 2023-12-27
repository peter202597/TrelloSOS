package com.sos.trellosos.domain.board;

import com.sos.trellosos.domain.user.User;
import jakarta.persistence.*;

@Entity
public class BoardUsers {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "User_Id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "Board_Id")
    private Board board;
}

