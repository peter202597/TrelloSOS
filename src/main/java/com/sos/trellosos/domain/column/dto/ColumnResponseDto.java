package com.sos.trellosos.domain.column.dto;

import com.sos.trellosos.domain.column.entity.Columns;
import com.sos.trellosos.global.dto.CommonResponseDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

//컬럼 생성 응답 DTO
@Getter
public class ColumnResponseDto extends CommonResponseDto {

    private Long id;
    private Long sequence;
    private String columnName;

    public ColumnResponseDto(Columns columns) {
        this.id = columns.getId();
        this.sequence = columns.getSequence();
        this.columnName = columns.getColumnName();
    }
}
