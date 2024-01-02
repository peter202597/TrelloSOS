package com.sos.trellosos.domain.column.dto;

import com.sos.trellosos.domain.column.entity.Columns;
import com.sos.trellosos.global.dto.CommonResponseDto;
import lombok.Getter;

//컬럼 생성 응답 DTO
@Getter
public class ColumnResponseDto extends CommonResponseDto {

    private Long id;
    private Integer columnSequence;
    private String columnName;

    public ColumnResponseDto(Columns columns) {
        this.id = columns.getId();
        this.columnSequence = columns.getSequence();
        this.columnName = columns.getColumnName();
    }
}
