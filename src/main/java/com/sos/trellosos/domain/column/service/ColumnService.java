package com.sos.trellosos.domain.column.service;

import com.sos.trellosos.domain.board.Board;
import com.sos.trellosos.domain.column.dto.ColumnRequestDto;
import com.sos.trellosos.domain.column.dto.ColumnResponseDto;
import com.sos.trellosos.domain.column.entity.Column;
import com.sos.trellosos.domain.column.repository.ColumnRepository;
import com.sos.trellosos.global.dto.CommonResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ColumnService {
    private final ColumnRepository columnRepository;

    //컬럼 생성
    public CommonResponseDto createColumn(ColumnRequestDto columnRequestDto) {
        Column column = new Column(columnRequestDto);
        columnRepository.save(column);
        return new CommonResponseDto("컬럼 생성 성공", HttpStatus.CREATED.value());
    }

    //컬럼 수정
    @Transactional
    public ColumnResponseDto updateColumn(Long columnId, ColumnRequestDto columnRequestDto) {
        Column column = columnRepository.findById(columnId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 컬럼 입니다.")
        );
        column.updateColumn(columnRequestDto);

        return new ColumnResponseDto(column);
    }

    //컬럼 삭제
    @Transactional
    public CommonResponseDto deleteColumn(Long columnId) {
        Column column = columnRepository.findById(columnId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 컬럼 입니다.")
        );
        columnRepository.deleteById(columnId);
        return new CommonResponseDto(columnId + "컬럼 삭제 성공", HttpStatus.OK.value());
    }
}
