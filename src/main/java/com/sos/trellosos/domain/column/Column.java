package com.sos.trellosos.domain.column;

import com.sos.trellosos.domain.column.dto.ColumnRequestDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "columns")
public class Column {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String columnName;

    public Column(ColumnRequestDto columnRequestDto) {
        this.columnName = columnRequestDto.getColumnName();
    }
}
