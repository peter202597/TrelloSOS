package com.sos.trellosos.domain.card;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CardController {

  private final CardService cardService;

  @PostMapping("/boards/{boardId}/columns/{columnId}/cards")
  public ResponseEntity<CardResponseDto> addCard(
      @PathVariable Long boardId,
      @PathVariable Long columnId,
      @RequestBody CardRequestDto requestDto
      ) {
    CardResponseDto responseDto = cardService.addCard(boardId, columnId, requestDto);

    return ResponseEntity.status(HttpStatus.CREATED.value()).body(responseDto);
  }
}
