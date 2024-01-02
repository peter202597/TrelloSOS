package com.sos.trellosos.domain.board.controller;

import com.sos.trellosos.domain.board.dto.JoinUserRequestDto;
import com.sos.trellosos.global.dto.CommonResponseDto;
import com.sos.trellosos.domain.board.dto.BoardRequestDto;
import com.sos.trellosos.domain.board.dto.BoardResponseDto;
import com.sos.trellosos.domain.board.service.BoardService;
import com.sos.trellosos.domain.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/create")
    public CommonResponseDto createBoard(
            @RequestBody BoardRequestDto boardRequestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails){
        return boardService.createBoard(boardRequestDto,userDetails);
    }

    @GetMapping("")
    public List<BoardResponseDto> getBoards(
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
        return boardService.getBoards(userDetails);
    }

    @GetMapping("/{boardId}")
    public BoardResponseDto getBoard(
            @PathVariable Long boardId,
            @AuthenticationPrincipal UserDetailsImpl userDetails){
        return boardService.getBoard(boardId,userDetails);
    }

    @PatchMapping("/{boardId}")
    public BoardResponseDto updateBoard(
            @PathVariable Long boardId,
            @RequestBody BoardRequestDto boardRequestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails){
        return boardService.updateBoard(boardId,boardRequestDto,userDetails);
    }

    @DeleteMapping ("/{boardId}")
    public CommonResponseDto deleteBoard(
            @PathVariable Long boardId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
            ){
        return boardService.deleteBoard(boardId,userDetails);
    }

    @PostMapping("/{boardId}/invite")
    public CommonResponseDto inviteUser(
            @PathVariable Long boardId,
            @RequestBody JoinUserRequestDto joinUserRequestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails){
        return boardService.inviteUser(boardId,joinUserRequestDto,userDetails);
    }
}
