package com.sos.trellosos.domain.column.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

//컬럼 생성 요청 DTO
@Getter
public class ColumnRequestDto {
    @NotBlank
    private Long boardId;
    @NotBlank
    private Long userId;
    @NotBlank
    private String columnName;

}
