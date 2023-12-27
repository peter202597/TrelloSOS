package com.sos.trellosos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommonResponseDto {

    private String msg;

    private Integer statusCode;

}
