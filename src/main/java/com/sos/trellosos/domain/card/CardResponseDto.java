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
  LocalDateTime dueDate;

  public CardResponseDto(Card card) {
    this.cardName = card.getCardName();
    this.cardColor = card.getCardColor();
    this.cardDescription = card.getCardDescription();
    this.dueDate = card.getDueDate();
  }
}
