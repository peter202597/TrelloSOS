package com.sos.trellosos.domain.column.entity;

import com.sos.trellosos.domain.user.User;
import com.sos.trellosos.global.entity.Timestamped;
import com.sos.trellosos.domain.board.entity.Board;
import com.sos.trellosos.domain.column.dto.ColumnRequestDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Table(name = "columns")
public class Columns extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer sequence;

    @Column
    private String columnName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    @Builder
    public Columns(ColumnRequestDto columnRequestDto) {
        this.columnName = columnRequestDto.getColumnName();
    }

    //컬럼 수정
    public void updateColumn(ColumnRequestDto columnRequestDto) {
//        this.sequence = columnRequestDto.getColumnSequence();
        this.columnName = columnRequestDto.getColumnName();
    }
}
