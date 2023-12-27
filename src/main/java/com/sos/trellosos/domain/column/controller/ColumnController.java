package com.sos.trellosos.domain.column.controller;

import com.sos.trellosos.domain.column.dto.ColumnCreateRequestDto;
import com.sos.trellosos.domain.column.dto.ColumnCreateResponseDto;
import com.sos.trellosos.domain.column.service.ColumnService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards/{board_id}/columns")
public class ColumnController {

    private final ColumnService columnService;

    // 컬럼 작성
    // @return 생성한 게시글 세부 정보를 반환함
    public createColumn() {}
    }
