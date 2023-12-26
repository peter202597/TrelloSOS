package com.sos.trellosos.domain.board.controller;

import com.sos.trellosos.CommonResponseDto;
import com.sos.trellosos.domain.board.dto.BoardRequestDto;
import com.sos.trellosos.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/create")
    public CommonResponseDto createBoard(BoardRequestDto boardRequestDto){//@AuthenticationPrincipal UserDetailsImpl userDetails
        return boardService.createBoard(boardRequestDto);
    }
}
