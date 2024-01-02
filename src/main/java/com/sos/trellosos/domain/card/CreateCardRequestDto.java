package com.sos.trellosos.domain.card;

import lombok.Getter;

@Getter
public class CreateCardRequestDto {

  private Long columnId;
  private String cardName;
  private String cardColor;
  private String cardDescription;

}
