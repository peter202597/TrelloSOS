package com.sos.trellosos.domain.board.service;

import com.sos.trellosos.CommonResponseDto;
import com.sos.trellosos.domain.board.Board;
import com.sos.trellosos.domain.board.dto.BoardRequestDto;
import com.sos.trellosos.domain.board.dto.BoardResponseDto;
import com.sos.trellosos.domain.board.repository.BoardRepository;
import com.sos.trellosos.domain.board.repository.BoardUserRepository;
import com.sos.trellosos.domain.security.UserDetailsImpl;
import com.sos.trellosos.domain.user.User;
import com.sos.trellosos.domain.user.UserRepository;
import com.sos.trellosos.exception.CustomException;
import com.sos.trellosos.exception.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    //보드 생성
    @Transactional
    public CommonResponseDto createBoard(BoardRequestDto boardRequestDto, UserDetailsImpl userDetails) {
        userCheck(userDetails);
        Board board = new Board(boardRequestDto);
        boardRepository.save(board);
        return new CommonResponseDto("보드 생성 성공", HttpStatus.CREATED.value());
    }

    //전체 보드 조회
    public List<Board> getBoards(UserDetailsImpl userDetails) {
        userCheck(userDetails);
        return boardRepository.findAll();
    }

    //보드 단건 조회
    public BoardResponseDto getBoard(Long boardId,UserDetailsImpl userDetails) {
        userCheck(userDetails);
        Board board = boardCheck(boardId);
        return new BoardResponseDto(board);
    }
    //보드 수정
    @Transactional
    public BoardResponseDto updateBoard(Long boardId, BoardRequestDto boardRequestDto,UserDetailsImpl userDetails) {
        userCheck(userDetails);
        Board board = boardCheck(boardId);
        board.updateBoard(boardRequestDto);

        return new BoardResponseDto(board);
    }

    //보드 삭제
    @Transactional
    public CommonResponseDto deleteBoard(Long boardId,UserDetailsImpl userDetails) {
        userCheck(userDetails);
        boardCheck(boardId);
        boardRepository.deleteById(boardId);
        return new CommonResponseDto(boardId + "번 보드 삭제 성공", HttpStatus.OK.value());
    }

    //유저 등록
    @Transactional
    public CommonResponseDto inviteUser(Long boardId, Long userId,UserDetailsImpl userDetails) {
        userCheck(userDetails);
        Board board = boardCheck(boardId);

        User user = userRepository.findById(userId).orElseThrow(
                ()-> new CustomException(ErrorCode.USER_NOT_MATCHES2)
        );

        board.inviteUser(user);
        return new CommonResponseDto(userId + "유저 등록 성공",HttpStatus.OK.value());
    }

    private void userCheck(UserDetailsImpl userDetails) {
        userRepository.findById(userDetails.getUser().getId())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }
    private Board boardCheck(Long boardId){
        return boardRepository.findById(boardId).orElseThrow(
                () -> new CustomException(ErrorCode.BOARD_NOT_FOUND)
        );
    }
}
