package com.sos.trellosos.domain.column.entity;

import com.sos.trellosos.domain.user.User;
import com.sos.trellosos.global.entity.Timestamped;
import com.sos.trellosos.domain.board.Board;
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

    @Column(unique = true)
    private Long sequence;

    @Column(unique = true)
    private String columnName;

    public void sequenceUp(){
        this.sequence = Math.max(0, this.sequence - 1);
    }

    public void sequenceDown(){
        this.sequence = this.sequence + 1;
    }

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
        this.sequence = columnRequestDto.getColumnSequence();
        this.columnName = columnRequestDto.getColumnName();
    }
}
