package com.sos.trellosos.domain.card;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateCardRequestDto {

  private Long columnId;
  private String cardName;
  private String cardColor;
  private String cardDescription;

}
