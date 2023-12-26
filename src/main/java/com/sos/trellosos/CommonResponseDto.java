package com.sos.trellosos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommonResponseDto {

    private String msg;

    private Integer statusCode;

}
