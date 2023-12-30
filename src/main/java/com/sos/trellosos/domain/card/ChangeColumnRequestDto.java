package com.sos.trellosos.domain.card;

import lombok.Getter;

@Getter
public class ChangeColumnRequestDto {

  private Long newColumnsId;
  private Integer newSequence;
}
