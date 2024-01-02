package com.sos.trellosos.domain.column.controller;

import com.sos.trellosos.domain.column.dto.ChangeColumnSequenceRequestDto;
import com.sos.trellosos.domain.column.dto.ColumnRequestDto;
import com.sos.trellosos.domain.column.dto.ColumnResponseDto;
import com.sos.trellosos.domain.column.service.ColumnService;
import com.sos.trellosos.global.dto.CommonResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ColumnController {

    private final ColumnService columnService;

    //컬럼 생성
    @PostMapping("/columns")
    public CommonResponseDto createColumn(
            @RequestBody ColumnRequestDto columnRequestDto){
        return columnService.createColumn(columnRequestDto);
    }

    //컬럼 수정
    @PatchMapping("/columns/{columnId}")
    public ColumnResponseDto updateColumn(
            @PathVariable Long columnId,
            @RequestBody ColumnRequestDto columnRequestDto){
        return columnService.updateColumn(columnId,columnRequestDto);
    }

    //컬럼 삭제
    @DeleteMapping ("/columns/{columnId}")
    public CommonResponseDto deleteColumn(
            @PathVariable Long columnId){
        return columnService.deleteColumn(columnId);
    }

    //컬럼 번호 이동
    @PatchMapping("/columns/{columnId}/columnSequence")
    public List<ColumnResponseDto> changeSequence(
            @PathVariable Long columnId,
            @RequestBody ChangeColumnSequenceRequestDto changeColumnSequenceRequestDto){

        return columnService.changeColumnSequence(columnId, changeColumnSequenceRequestDto);
    }
}
