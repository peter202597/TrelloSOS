package com.sos.trellosos.domain.column.repository;

import com.sos.trellosos.domain.column.entity.Column;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColumnRepository extends JpaRepository<Column,Long> {
}
