package com.sos.trellosos.domain.board.service;

import com.sos.trellosos.CommonResponseDto;
import com.sos.trellosos.domain.board.Board;
import com.sos.trellosos.domain.board.dto.BoardRequestDto;
import com.sos.trellosos.domain.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    public CommonResponseDto createBoard(BoardRequestDto boardRequestDto) {

        Board board = new Board(boardRequestDto);
        boardRepository.save(board);

        return new CommonResponseDto("보드 생성 성공", HttpStatus.CREATED.value());
    }
}
