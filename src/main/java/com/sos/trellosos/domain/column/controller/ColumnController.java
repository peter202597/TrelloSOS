package com.sos.trellosos.domain.column.controller;

import com.sos.trellosos.CommonResponseDto;
import com.sos.trellosos.domain.column.dto.ColumnRequestDto;
import com.sos.trellosos.domain.column.service.ColumnService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards/{board_id}/columns")
public class ColumnController {
    private final ColumnService columnService;

    // 댓글 작성
    public CommonResponseDto createColumn(ColumnRequestDto columnRequestDto) {

        return columnService.createColumn(columnRequestDto);
    }
}
