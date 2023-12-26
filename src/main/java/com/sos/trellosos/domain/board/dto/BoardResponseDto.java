package com.sos.trellosos.domain.board.dto;

import com.sos.trellosos.domain.board.Board;

public class BoardResponseDto {
    private String boardName;
    private String backgroundColor;
    private String boardDescription;

    public BoardResponseDto(Board board) {
        this.boardName = board.getBoardName();
        this.backgroundColor = board.getBackgroundColor();
        this.boardDescription = board.getBoardDescription();
    }
}
