package com.sos.trellosos.domain.board.controller;

import com.sos.trellosos.global.dto.CommonResponseDto;
import com.sos.trellosos.domain.board.Board;
import com.sos.trellosos.domain.board.dto.BoardRequestDto;
import com.sos.trellosos.domain.board.dto.BoardResponseDto;
import com.sos.trellosos.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/create")
    public CommonResponseDto createBoard(@RequestBody BoardRequestDto boardRequestDto){//@AuthenticationPrincipal UserDetailsImpl userDetails
        return boardService.createBoard(boardRequestDto);
    }

    @GetMapping("")
    public List<Board> getBoards(){//@AuthenticationPrincipal UserDetailsImpl userDetails
        return boardService.getBoards();
    }

    @GetMapping("/{boardId}")
    public BoardResponseDto getBoard(@PathVariable Long boardId){
        return boardService.getBoard(boardId);
    }

    @PatchMapping("/{boardId}")
    public BoardResponseDto updateBoard(
            @PathVariable Long boardId,
            @RequestBody BoardRequestDto boardRequestDto){
        return boardService.updateBoard(boardId,boardRequestDto);
    }

    @DeleteMapping ("/{boardId}")
    public CommonResponseDto deleteBoard(
            @PathVariable Long boardId
            ){
        return boardService.deleteBoard(boardId);
    }

    @PostMapping("/invite")
    public CommonResponseDto inviteUser(@RequestBody String userId){
        return boardService.inviteUser(userId);
    }
}
