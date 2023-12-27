package com.sos.trellosos.domain.column.dto;

import com.sos.trellosos.domain.column.entity.Column;
import com.sos.trellosos.global.dto.CommonResponseDto;
import lombok.Getter;

//컬럼 생성 응답 DTO
@Getter
public class ColumnResponseDto extends CommonResponseDto {

    private Long id;
    private Integer sequence;
    private String columnName;

    public ColumnResponseDto(Column saveColumn) {
        this.id = saveColumn.getId();
        this.sequence = saveColumn.getSequence();
        this.columnName = saveColumn.getColumnName();
    }
}
