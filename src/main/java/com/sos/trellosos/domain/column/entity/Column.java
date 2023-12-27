package com.sos.trellosos.domain.column.entity;

import com.sos.trellosos.global.entity.Timestamped;
import com.sos.trellosos.domain.board.Board;
import com.sos.trellosos.domain.column.dto.ColumnCreateRequestDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "columns")
public class Column extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String columnName;
    private Integer sequence;

    @ManyToOne
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    public Column(ColumnCreateRequestDto columnCreateRequestDto) {
        this.columnName = columnCreateRequestDto.getColumnName();
    }
}
