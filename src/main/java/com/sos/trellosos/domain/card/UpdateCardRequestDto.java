package com.sos.trellosos.domain.card;

import lombok.Getter;

@Getter
public class UpdateCardRequestDto {

  private String cardName;
  private String cardColor;
  private String cardDescription;
}
