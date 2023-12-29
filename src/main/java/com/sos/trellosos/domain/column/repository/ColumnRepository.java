package com.sos.trellosos.domain.column.repository;

import com.sos.trellosos.domain.column.entity.Columns;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColumnRepository extends JpaRepository<Columns,Long> {
}
