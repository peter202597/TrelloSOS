package com.sos.trellosos.domain.card;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CardRequestDto {

  private Long columnId;
  private String cardName;
  private String cardColor;
  private String cardDescription;
  private Integer order;
  private Long userId;

}
