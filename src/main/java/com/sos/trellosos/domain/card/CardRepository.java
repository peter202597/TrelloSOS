package com.sos.trellosos.domain.card;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {

  Optional<Card> findByCardName(String cardName);
}
