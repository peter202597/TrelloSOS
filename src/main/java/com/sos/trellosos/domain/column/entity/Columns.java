package com.sos.trellosos.domain.column.entity;

import com.sos.trellosos.domain.card.Card;
import com.sos.trellosos.domain.user.User;
import com.sos.trellosos.domain.worker.Worker;
import com.sos.trellosos.global.entity.Timestamped;
import com.sos.trellosos.domain.board.entity.Board;
import com.sos.trellosos.domain.column.dto.ColumnRequestDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "columns", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Card> cards = new ArrayList<>();

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
