package com.sos.trellosos.domain.board.service;


import com.sos.trellosos.domain.board.dto.JoinUserRequestDto;
import com.sos.trellosos.domain.board.entity.Board;
import com.sos.trellosos.domain.board.dto.BoardRequestDto;
import com.sos.trellosos.domain.board.dto.BoardResponseDto;
import com.sos.trellosos.domain.board.entity.BoardUser;
import com.sos.trellosos.domain.board.repository.BoardRepository;
import com.sos.trellosos.domain.board.repository.BoardUserRepository;
import com.sos.trellosos.domain.security.UserDetailsImpl;
import com.sos.trellosos.domain.user.User;
import com.sos.trellosos.domain.user.UserRepository;

import com.sos.trellosos.global.dto.CommonResponseDto;
import com.sos.trellosos.global.exception.CustomException;
import com.sos.trellosos.global.exception.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final BoardUserRepository boardUserRepository;

    //보드 생성
    @Transactional
    public CommonResponseDto createBoard(BoardRequestDto boardRequestDto, UserDetailsImpl userDetails) {
        userCheck(userDetails);
        Board board = new Board(boardRequestDto);
        board.inviteUser(userDetails.getUser());
        boardRepository.save(board);
        return new CommonResponseDto("보드 생성 성공", HttpStatus.CREATED.value());
    }

    //전체 보드 조회
    public List<BoardResponseDto> getBoards(UserDetailsImpl userDetails) {
        userCheck(userDetails);
        List<BoardUser> boardList = boardUserRepository.findByUserId(userDetails.getUser().getId());
        List<BoardResponseDto> boardResponseDtoList = new ArrayList<>();
        for (BoardUser board : boardList) {
            boardResponseDtoList.add(new BoardResponseDto(board.getBoard())); //두번째 파라미터로 isOwner
        }
        return boardResponseDtoList;
    }

    //보드 단건 조회
    public BoardResponseDto getBoard(Long boardId, UserDetailsImpl userDetails) {
        userCheck(userDetails);
        BoardUser boardUser = boardUserRepository.findByUserIdAndBoardId(userDetails.getUser().getId(),boardId);
        return new BoardResponseDto(boardUser.getBoard(),boardUser.getBoard().getColumns());
    }

    //보드 수정
    @Transactional
    public BoardResponseDto updateBoard(Long boardId, BoardRequestDto boardRequestDto, UserDetailsImpl userDetails) {
        List<BoardUser> boardUserList = boardUserRepository.findByBoardId(boardId);
        Board board = boardCheck(boardId);
        boolean flag = false;
        
        for (BoardUser boardUser : boardUserList) {
            System.out.println("boardUser.getUser().getId() = " + boardUser.getUser().getId());
            if (boardUser.getUser().getId().equals(userDetails.getUser().getId())) {
                flag=true;
            }
        }
        if(flag){
            board.updateBoard(boardRequestDto);
        }else{
            throw new CustomException(ErrorCode.USER_NOT_INVITED);
        }
        return new BoardResponseDto(board);
    }

    //보드 삭제
    @Transactional
    public CommonResponseDto deleteBoard(Long boardId, UserDetailsImpl userDetails) {
        List<BoardUser> boardUserList = boardUserRepository.findByBoardId(boardId);
        userCheck(userDetails);
        boardCheck(boardId);
        boolean flag = false;
        for (BoardUser boardUser : boardUserList) {
            System.out.println("boardUser.getUser().getId() = " + boardUser.getUser().getId());
            if (boardUser.getUser().getId().equals(userDetails.getUser().getId())) {
                flag=true;
            }
        }
        if(flag){
            return new CommonResponseDto(boardId + "번 보드 삭제 성공", HttpStatus.OK.value());
        }else{
            return new CommonResponseDto("보드 사용자로 등록되어 있지 않습니다.", HttpStatus.BAD_REQUEST.value());
        }
    }

    //유저 등록
    @Transactional
    public CommonResponseDto inviteUser(Long boardId, JoinUserRequestDto joinUserRequestDto, UserDetailsImpl userDetails) {
        userCheck(userDetails);
        Board board = boardCheck(boardId);
        User user = userRepository.findById(joinUserRequestDto.getUserId()).orElseThrow(
                () -> new CustomException(ErrorCode.USER_NOT_MATCHES2)
        );
        board.inviteUser(user);
        return new CommonResponseDto(joinUserRequestDto.getUserId() + "유저 등록 성공", HttpStatus.OK.value());
    }

    private void userCheck(UserDetailsImpl userDetails) {
        userRepository.findById(userDetails.getUser().getId())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }

    private Board boardCheck(Long boardId) {
        return boardRepository.findById(boardId).orElseThrow(
                () -> new CustomException(ErrorCode.BOARD_NOT_FOUND)
        );
    }
}
