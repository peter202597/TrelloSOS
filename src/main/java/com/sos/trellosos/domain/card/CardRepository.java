package com.sos.trellosos.domain.card;

import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CardRepository extends JpaRepository<Card, Long> {

  List<Card> findAllByOrderBySequenceAsc();

  Integer countByColumnsId(Long columnsId);

  @Transactional
  @Modifying
  @Query("UPDATE Card c SET c.sequence = c.sequence + 1 WHERE c.sequence BETWEEN ?1 AND ?2 AND c.columns.id = ?3")
  void incrementSequenceBetween(int start, int end, Long columnId);

  @Transactional
  @Modifying
  @Query("UPDATE Card c SET c.sequence = c.sequence - 1 WHERE c.sequence BETWEEN ?1 AND ?2 AND c.columns.id = ?3")
  void decrementSequenceBetween(int start, int end, Long columnId);



}
