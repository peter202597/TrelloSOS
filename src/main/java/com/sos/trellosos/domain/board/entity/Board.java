package com.sos.trellosos.domain.board.entity;

import com.sos.trellosos.domain.user.User;
import jakarta.persistence.*;
import com.sos.trellosos.domain.board.dto.BoardRequestDto;
import com.sos.trellosos.global.entity.Timestamped;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "board")
public class Board extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String boardName;
    private String backgroundColor;
    private String boardDescription;
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<BoardUser> boardUsers = new ArrayList<>();

    //보드 생성
    public Board(BoardRequestDto boardRequestDto) {
        this.boardName = boardRequestDto.getBoardName();
        this.backgroundColor = boardRequestDto.getBackgroundColor();
        this.boardDescription = boardRequestDto.getBoardDescription();
    }

    //보드 수정
    public void updateBoard(BoardRequestDto boardRequestDto) {
        this.boardName = boardRequestDto.getBoardName();
        this.backgroundColor = boardRequestDto.getBackgroundColor();
        this.boardDescription = boardRequestDto.getBoardDescription();
    }

    public void inviteUser(User user) {
        BoardUser boardUser = new BoardUser(this, user);
        this.boardUsers.add(boardUser);
    }
}
