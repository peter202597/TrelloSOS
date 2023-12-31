package com.sos.trellosos.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

  WORKER_NOT_FOUND(HttpStatus.NOT_FOUND, "작업자가 존재하지 않습니다"),
  COLUMN_NOT_FOUND(HttpStatus.NOT_FOUND, "컬럼이 존재하지 않습니다"),
  CARD_NOT_FOUND(HttpStatus.NOT_FOUND, "카드가 존재하지 않습니다."),
  USER_NOT_MATCHES(HttpStatus.UNAUTHORIZED, "board 생성자만 수정/삭제할 수 있습니다."),
  USER_NOT_INVITED(HttpStatus.UNAUTHORIZED, "board에 초대받은 사용자만 수정/삭제할 수 있습니다."),
  USER_NOT_MATCHES2(HttpStatus.UNAUTHORIZED, "아이디가 일치하지 않습니다."),
  PASSWORD_NOT_MATCHES(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다."),
  ALREADY_EXIST_USER(HttpStatus.CONFLICT, "중복된 닉네임 입니다."),
  ID_PW_SAME(HttpStatus.BAD_REQUEST, "비밀번호에 Username과 같은 값이 있으면 안됩니다."),
  FORM_NOT_EMAIL(HttpStatus.BAD_REQUEST, "이메일 형식이 잘못됐습니다."),
  BOARD_NOT_FOUND(HttpStatus.NOT_FOUND, "보드가 존재하지 않습니다."),
  USER_NOT_FOUND(HttpStatus.NOT_FOUND, "사용자가 존재하지 않습니다."),
  COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "댓글이 존재하지 않습니다."),
  INDEX_NOT_FOUND(HttpStatus.NOT_FOUND, "인덱스가 존재하지 않습니다."),
  UNKNOWN_ERROR(HttpStatus.BAD_REQUEST, "토큰이 존재하지 않습니다."),
  WRONG_TYPE_TOKEN(HttpStatus.BAD_REQUEST, "변조된 토큰입니다."),
  EXPIRED_TOKEN(HttpStatus.BAD_REQUEST, "만료된 토큰입니다."),
  UNSUPPORTED_TOKEN(HttpStatus.BAD_REQUEST, "변조된 토큰입니다."),
  ACCESS_DENIED(HttpStatus.BAD_REQUEST, "권한이 없습니다.");

  private HttpStatus status;
  private String message;
}