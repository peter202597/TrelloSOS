package com.sos.trellosos.domain.column.repository;

import com.sos.trellosos.domain.column.entity.Columns;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ColumnRepository extends JpaRepository<Columns,Long> {

    List<Columns> findAllByOrderBySequenceAsc();

    Integer countByBoardId(Long boardId);

    @Transactional
    @Modifying
    @Query("UPDATE Columns c SET c.sequence = c.sequence + 1 WHERE c.sequence BETWEEN ?1 AND ?2 AND c.board.id = ?3")
    void sequenceUp(int start, int end, Long boardId);

    @Transactional
    @Modifying
    @Query("UPDATE Columns c SET c.sequence = c.sequence - 1 WHERE c.sequence BETWEEN ?1 AND ?2 AND c.board.id = ?3")
    void sequenceDown(int start, int end, Long boardId);
}
