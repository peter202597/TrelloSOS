package com.sos.trellosos.domain.column.entity;

import com.sos.trellosos.global.entity.Timestamped;
import com.sos.trellosos.domain.board.Board;
import com.sos.trellosos.domain.column.dto.ColumnRequestDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
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

    @jakarta.persistence.Column(unique = true)
    private Integer sequence;
    private String columnName;

    @ManyToOne
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    @Builder
    public Column(ColumnRequestDto columnRequestDto) {
        this.columnName = columnRequestDto.getColumnName();
    }

    //컬럼 수정
    public void updateColumn(ColumnRequestDto columnRequestDto) {
        this.sequence = columnRequestDto.getColumnSequence();
        this.columnName = columnRequestDto.getColumnName();
    }
}
