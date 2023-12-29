package com.sos.trellosos.domain.column.service;

import com.sos.trellosos.domain.column.dto.ColumnRequestDto;
import com.sos.trellosos.domain.column.dto.ColumnResponseDto;
import com.sos.trellosos.domain.column.dto.SequenceChangeRequestDto;
import com.sos.trellosos.domain.column.entity.Columns;
import com.sos.trellosos.domain.column.repository.ColumnRepository;
import com.sos.trellosos.global.dto.CommonResponseDto;
import jakarta.persistence.Column;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ColumnService {
    private final ColumnRepository columnRepository;

    //컬럼 생성
    public CommonResponseDto createColumn(ColumnRequestDto columnRequestDto) {
        Columns columns = new Columns(columnRequestDto);
        columnRepository.save(columns);
        return new CommonResponseDto("컬럼 생성 성공", HttpStatus.CREATED.value());
    }

    //컬럼 수정
    @Transactional
    public ColumnResponseDto updateColumn(Long columnId, ColumnRequestDto columnRequestDto) {
        Columns columns = columnRepository.findById(columnId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 컬럼 입니다.")
        );
        columns.updateColumn(columnRequestDto);

        return new ColumnResponseDto(columns);
    }

    //컬럼 삭제
    @Transactional
    public CommonResponseDto deleteColumn(Long columnId) {
        Columns columns = columnRepository.findById(columnId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 컬럼 입니다.")
        );
        columnRepository.delete(columns);
        return new CommonResponseDto(columnId + "컬럼 삭제 성공", HttpStatus.OK.value());
    }

    //컬럼 이동
//    public List<ColumnResponseDto> updateColumnSequence(Long columnId, SequenceChangeRequestDto request) {
//        Columns columns = columnRepository.findById(columnId)
//                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 컬럼 입니다.")
//        );
//        // 요청에서 받은 컬럼의 이동 방향에 따라 순서를 변경합니다.
//        if (request.getDirection().equals("up")) {
//            // 순서를 올리는 로직
//            columnToMove.sequenceUp();
//        } else if (request.getDirection().equals("down")) {
//            // 순서를 내리는 로직
//            columnToMove.sequenceDown();
//        }
//
//        // 변경된 컬럼을 저장합니다.
//        columnRepository.save(columnToMove);
//
//        // 변경된 컬럼 목록을 반환하거나 업데이트된 정보를 리턴할 수 있습니다.
//        List<Columns> updatedColumns = columnRepository.findAll(); // 업데이트된 컬럼 목록 조회
//        return convertToColumnResponseDtoList(updatedColumns);
//    }
}
