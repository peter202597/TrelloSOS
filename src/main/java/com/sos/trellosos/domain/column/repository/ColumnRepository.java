package com.sos.trellosos.domain.column.repository;

import com.sos.trellosos.domain.column.entity.Columns;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ColumnRepository extends JpaRepository<Columns,Long> {
}
