package com.sos.trellosos.domain.card;

import java.time.LocalDateTime;
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
  String worker;

  public CardResponseDto(Card card) {
    this.cardName = card.getCardName();
    this.cardColor = card.getCardColor();
    this.cardDescription = card.getCardDescription();
    this.sequence = card.getSequence();
    this.dueDate = card.getDueDate();
  }

}
