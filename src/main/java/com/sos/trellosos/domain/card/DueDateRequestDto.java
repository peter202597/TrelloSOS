package com.sos.trellosos.domain.card;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class DueDateRequestDto {

  @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd-HH:mm", timezone = "Asia/Seoul")
  private LocalDateTime dueDate;

}
