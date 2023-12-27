package com.sos.trellosos.domain.board.service;

import com.sos.trellosos.CommonResponseDto;
import com.sos.trellosos.domain.board.Board;
import com.sos.trellosos.domain.board.dto.BoardRequestDto;
import com.sos.trellosos.domain.board.dto.BoardResponseDto;
import com.sos.trellosos.domain.board.repository.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    //private final UserRepository userRepository;
    //보드 생성
    @Transactional
    public CommonResponseDto createBoard(BoardRequestDto boardRequestDto) {
        Board board = new Board(boardRequestDto);
        boardRepository.save(board);
        return new CommonResponseDto("보드 생성 성공", HttpStatus.CREATED.value());
    }

    //전체 보드 조회
    public List<Board> getBoards() {
        return boardRepository.findAll();
    }

    //보드 수정
    @Transactional
    public BoardResponseDto updateBoard(Long boardId, BoardRequestDto boardRequestDto) {
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 보드 입니다.")
        );
        board.updateBoard(boardRequestDto);

        return new BoardResponseDto(board);
    }

    //보드 삭제
    @Transactional
    public CommonResponseDto deleteBoard(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 보드 입니다.")
        );
        boardRepository.deleteById(boardId);
        return new CommonResponseDto(boardId + "번 보드 삭제 성공", HttpStatus.OK.value());
    }

    public CommonResponseDto inviteUser(String userId) {

        return new CommonResponseDto(userId + "유저 등록 성공",HttpStatus.OK.value());
    }
}
