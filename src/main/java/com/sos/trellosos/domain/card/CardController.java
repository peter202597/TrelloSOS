package com.sos.trellosos.domain.card;


import com.sos.trellosos.global.dto.CommonResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

  @GetMapping("/{boardName}/{columnName}/cards")
  public ResponseEntity<Page<CardResponseDto>> getCardPage(
      @PathVariable String boardName,
      @PathVariable String columnName,
      @RequestParam("page") int page,
      @RequestParam("size") int size,
      @RequestParam("sortBy") String sortBy,
      @RequestParam("isAsc") boolean isAsc
  ) {
    Page<CardResponseDto> cardList = cardService.getCardPage(boardName, columnName, page-1, size,
        sortBy, isAsc);

    return ResponseEntity.status(HttpStatus.OK.value()).body(cardList);
  }

  @PutMapping("/{boardName}/{columnName}/{cardName}")
  public ResponseEntity<CardResponseDto> updateCard(
      @PathVariable String boardName,
      @PathVariable String columnName,
      @PathVariable String cardName,
      @RequestBody CardRequestDto requestDto
  ) {
    CardResponseDto responseDto = cardService.updateCard(boardName, columnName, cardName,
        requestDto);

    return ResponseEntity.status(HttpStatus.OK.value()).body(responseDto);
  }

  @DeleteMapping("/{boardName}/{columnName}/{cardName}")
  public ResponseEntity<CommonResponseDto> deleteCard(
      @PathVariable String boardName,
      @PathVariable String columnName,
      @PathVariable String cardName
  ) {
    cardService.deleteCard(boardName, columnName, cardName);

    return ResponseEntity
        .status(HttpStatus.OK.value())
        .body(new CommonResponseDto(cardName + "카드가 삭제되었습니다.", HttpStatus.OK.value()));
  }


}
