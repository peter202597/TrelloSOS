package com.sos.trellosos.domain.column.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

//컬럼 생성 요청 DTO
@Getter
public class ColumnRequestDto {

    @NotBlank
    private Integer columnSequence;
    private String columnName;

}
