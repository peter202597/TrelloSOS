package com.sos.trellosos.domain.board.dto;

import com.sos.trellosos.domain.board.entity.Board;
import com.sos.trellosos.domain.column.dto.ColumnResponseDto;
import com.sos.trellosos.domain.column.entity.Columns;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class BoardResponseDto {
    private String boardName;
    private String backgroundColor;
    private String boardDescription;
    private List<ColumnResponseDto> columnsList;
    public BoardResponseDto(Board board) {
        this.boardName = board.getBoardName();
        this.backgroundColor = board.getBackgroundColor();
        this.boardDescription = board.getBoardDescription();
    }

    public BoardResponseDto(Board board, List<Columns> columnsList) {
        this.boardName = board.getBoardName();
        this.backgroundColor = board.getBackgroundColor();
        this.boardDescription = board.getBoardDescription();
        this.columnsList=new ArrayList<>();
        for(Columns columns : columnsList){
            this.columnsList.add(new ColumnResponseDto(columns));
        }
    }
}
