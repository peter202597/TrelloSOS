package com.sos.trellosos.domain.column.service;

import com.sos.trellosos.domain.board.Board;
import com.sos.trellosos.domain.board.repository.BoardRepository;
import com.sos.trellosos.domain.column.dto.ChangeColumnSequenceRequestDto;
import com.sos.trellosos.domain.column.dto.ColumnRequestDto;
import com.sos.trellosos.domain.column.dto.ColumnResponseDto;
import com.sos.trellosos.domain.column.entity.Columns;
import com.sos.trellosos.domain.column.repository.ColumnRepository;
import com.sos.trellosos.domain.user.User;
import com.sos.trellosos.domain.user.UserRepository;
import com.sos.trellosos.global.dto.CommonResponseDto;
import com.sos.trellosos.global.exception.CustomException;
import com.sos.trellosos.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
//@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ColumnService {
    private final ColumnRepository columnRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    //컬럼 생성
    public CommonResponseDto createColumn(ColumnRequestDto columnRequestDto) {
        Columns columns = new Columns(columnRequestDto);
        Integer count = columnRepository.countByBoardId(columnRequestDto.getBoardId()) + 1;
        Board board = boardRepository.findById(columnRequestDto.getBoardId()).orElseThrow(
                () -> new CustomException(ErrorCode.BOARD_NOT_FOUND)
        );
        User user = userRepository.findById(columnRequestDto.getUserId()).orElseThrow(
                () -> new CustomException(ErrorCode.USER_NOT_FOUND)
        );
        columns.setBoard(board);
        columns.setUser(user);
        columns.setSequence(count);
        columnRepository.save(columns);
        return new CommonResponseDto("컬럼 생성 성공", HttpStatus.CREATED.value());
    }

    //컬럼 수정
    @Transactional
    public ColumnResponseDto updateColumn(Long columnId, ColumnRequestDto columnRequestDto) {
        Columns columns = findColumn(columnId);
        columns.updateColumn(columnRequestDto);

        return new ColumnResponseDto(columns);
    }

    //컬럼 삭제
    @Transactional
    public CommonResponseDto deleteColumn(Long columnId) {
        Columns columns = findColumn(columnId);
        columnRepository.delete(columns);
        return new CommonResponseDto(columnId + "컬럼 삭제 성공", HttpStatus.OK.value());
    }

    //컬럼 순서 이동
    @Transactional
    public List<ColumnResponseDto> changeColumnSequence(Long columnId, ChangeColumnSequenceRequestDto changeColumnSequenceRequestDto) {
        Integer newSequence = changeColumnSequenceRequestDto.getNewSequence();
        Columns column = findColumn(columnId);
        Integer currentSequence = column.getSequence();
        Long boardId = column.getBoard().getId();

        if (currentSequence < newSequence){
        columnRepository.sequenceDown(currentSequence + 1, newSequence, boardId);
        } else {columnRepository.sequenceUp(newSequence, currentSequence - 1, boardId);}
        column.setSequence(newSequence);

        return columnRepository.findAllByOrderBySequenceAsc().stream().map(ColumnResponseDto::new).toList();
    }

    //중복문 처리
    private Columns findColumn(Long columnId) {
        return columnRepository.findById(columnId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 컬럼 입니다.")
                );
    }
}
