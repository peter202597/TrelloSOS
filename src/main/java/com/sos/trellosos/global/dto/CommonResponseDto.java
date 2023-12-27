package com.sos.trellosos.global.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommonResponseDto {

    private String msg;

    private Integer statusCode;

}
