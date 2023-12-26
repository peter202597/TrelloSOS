package com.sos.trellosos.domain.board.controller;

import com.sos.trellosos.CommonResponseDto;
import com.sos.trellosos.domain.board.Board;
import com.sos.trellosos.domain.board.dto.BoardRequestDto;
import com.sos.trellosos.domain.board.dto.BoardResponseDto;
import com.sos.trellosos.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/create")
    public CommonResponseDto createBoard(BoardRequestDto boardRequestDto){//@AuthenticationPrincipal UserDetailsImpl userDetails
        return boardService.createBoard(boardRequestDto);
    }

    @GetMapping("")
    public List<Board> getBoards(){//@AuthenticationPrincipal UserDetailsImpl userDetails
        return boardService.getBoards();
    }

    @GetMapping("/{boardId}")
    public List<Board> getBoards(@PathVariable Long boardId){
        return boardService.getBoards();
    }

    @PatchMapping("/{boardId}")
    public BoardResponseDto getBoard(
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
}
