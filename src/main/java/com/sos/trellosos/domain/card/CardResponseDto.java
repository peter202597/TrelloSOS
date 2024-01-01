package com.sos.trellosos.domain.card;

import com.sos.trellosos.domain.worker.Worker;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CardResponseDto {

  String cardName;
  String cardColor;
  String cardDescription;
  Integer sequence;
  LocalDateTime dueDate;
  List<String> workers = new ArrayList<>();

  public CardResponseDto(Card card) {
    this.cardName = card.getCardName();
    this.cardColor = card.getCardColor();
    this.cardDescription = card.getCardDescription();
    this.sequence = card.getSequence();
    this.dueDate = card.getDueDate();
    for (Worker worker : card.getWorkers()) {
      this.workers.add(worker.getUser().getUsername());
    }
  }

}
