package com.sos.trellosos.domain.card;


import com.sos.trellosos.global.dto.CommonResponseDto;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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

  @PostMapping("/cards")
  public ResponseEntity<CardResponseDto> createCard(@RequestBody CreateCardRequestDto requestDto) {
    CardResponseDto responseDto = cardService.createCard(requestDto);

    return ResponseEntity
        .status(HttpStatus.CREATED.value())
        .body(responseDto);
  }

  @GetMapping("/cards/{cardId}")
  public ResponseEntity<CardResponseDto> getCard(@PathVariable Long cardId) {
    CardResponseDto responseDto = cardService.getCard(cardId);

    return ResponseEntity
        .status(HttpStatus.OK.value())
        .body(responseDto);
  }

  @GetMapping("/cards")
  public ResponseEntity<Page<CardResponseDto>> getCardPage(
      @RequestParam("page") int page,
      @RequestParam("size") int size,
      @RequestParam("sortBy") String sortBy,
      @RequestParam("isAsc") boolean isAsc
  ) {
    Page<CardResponseDto> cardList = cardService.getCardPage(page - 1, size, sortBy, isAsc);

    return ResponseEntity
        .status(HttpStatus.OK.value())
        .body(cardList);
  }

  @PutMapping("cards/{cardId}")
  public ResponseEntity<CardResponseDto> updateCard(
      @PathVariable Long cardId,
      @Valid @RequestBody UpdateCardRequestDto requestDto
  ) {
    CardResponseDto responseDto = cardService.updateCard(cardId, requestDto);

    return ResponseEntity
        .status(HttpStatus.OK.value())
        .body(responseDto);
  }


  @DeleteMapping("/cards/{cardId}")
  public ResponseEntity<CommonResponseDto> deleteCard(@PathVariable Long cardId) {
    cardService.deleteCard(cardId);

    return ResponseEntity
        .status(HttpStatus.OK.value())
        .body(new CommonResponseDto("카드가 삭제되었습니다.", HttpStatus.OK.value()));
  }

  @PatchMapping("/cards/{cardId}/sequence")
  public ResponseEntity<List<CardResponseDto>> changeSequence(
      @PathVariable Long cardId,
      @RequestBody ChangeSequenceRequestDto requestDto) {

    return ResponseEntity
        .status(HttpStatus.OK.value())
        .body(cardService.changeSequence(cardId, requestDto));
  }

  @PatchMapping("/cards/{cardId}/column")
  public ResponseEntity<CardResponseDto> changeColumn(
      @PathVariable Long cardId,
      @RequestBody ChangeColumnRequestDto requestDto
  ) {
    CardResponseDto responseDto = cardService.changeColumn(cardId, requestDto);

    return ResponseEntity
        .status(HttpStatus.OK.value())
        .body(responseDto);
  }

  @DeleteMapping("/cards")
  public ResponseEntity<CommonResponseDto> deleteAll() {
    cardService.deleteAll();
    return ResponseEntity.
        status(HttpStatus.OK.value())
        .body(new CommonResponseDto("일괄삭제 완료", HttpStatus.OK.value()));
  }

  @PatchMapping("/cards/{cardId}/duedate")
  public ResponseEntity<CardResponseDto> changeDueDate(
      @PathVariable Long cardId,
      @RequestBody @Valid DueDateRequestDto requestDto
  ) {
    CardResponseDto responseDto = cardService.setDueDate(cardId, requestDto);

    return ResponseEntity
        .status(HttpStatus.OK.value())
        .body(responseDto);
  }

  @PatchMapping("/cards/{cardId}/workers")
  public ResponseEntity<CardResponseDto> allocateWorker(
      @PathVariable Long cardId,
      @RequestBody WorkerRequestDto requestDto
  ) {
    CardResponseDto responseDto = cardService.allocateWorker(cardId, requestDto);

    return ResponseEntity
        .status(HttpStatus.OK.value())
        .body(responseDto);
  }

  @DeleteMapping("/cards/{cardId}/workers")
  public ResponseEntity<CardResponseDto> detachWorker(
      @PathVariable Long cardId,
      @RequestBody WorkerRequestDto requestDto
  ) {
    CardResponseDto responseDto = cardService.detachWorker(cardId, requestDto);

    return ResponseEntity
        .status(HttpStatus.OK.value())
        .body(responseDto);
  }
}
