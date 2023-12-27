package com.sos.trellosos.domain.card;

import com.sos.trellosos.exception.CustomException;
import com.sos.trellosos.exception.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardService {

  private final CardRepository cardRepository;

//  private final BoardRepository boardRepository;
//  private final ColumnRepository columnRepository;
//  private final UserRepository userRepository;

  public CardResponseDto addCard(Long boardId, Long columnId, CardRequestDto requestDto) {
//    Board board = boardRepository.findById(boardId).orElseThrow(
//        () -> throw new CustomException(ErrorCode.BOARD_NOT_FOUND)
//    );
//
//    Column column = columnRepository.findById(columnId).orElseThrow(
//        () -> throw new CustomException(ErrorCode.COLUMN_NOT_FOUND)
//    );

    Card card = new Card(requestDto);
//    card.setColumn(column);

    Card savedCard = cardRepository.save(card);

    return new CardResponseDto(savedCard);
  }

  public Page<CardResponseDto> getCardPage(
      String boardName,
      String columnName,
      int page, int size, String sortBy, boolean isAsc) {
    //    Board board = boardRepository.findByBoardName(boardName).orElseThrow(
//        () -> throw new CustomException(ErrorCode.BOARD_NOT_FOUND)
//    );
//
//    Column column = columnRepository.findByColumnName(columnName).orElseThrow(
//        () -> throw new CustomException(ErrorCode.COLUMN_NOT_FOUND)
//    );

    Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
    Sort sort = Sort.by(direction, sortBy);
    Pageable pageable = PageRequest.of(page, size, sort);

    Page<Card> cardList = cardRepository.findAll(pageable);

    return cardList.map(CardResponseDto::new);
  }

  @Transactional
  public CardResponseDto updateCard(String boardName, String columnName, String cardName,
      CardRequestDto requestDto) {
    //    Board board = boardRepository.findByBoardName(boardName).orElseThrow(
//        () -> throw new CustomException(ErrorCode.BOARD_NOT_FOUND)
//    );
//
//    Column column = columnRepository.findByColumnName(columnName).orElseThrow(
//        () -> throw new CustomException(ErrorCode.COLUMN_NOT_FOUND)
//    );
    Card card = cardRepository.findByCardName(cardName).orElseThrow(
        () -> new CustomException(ErrorCode.CARD_NOT_FOUND)
    );

    card.update(requestDto);

    return new CardResponseDto(card);
  }

  @Transactional
  public void deleteCard(String boardName, String columnName, String cardName) {
    //    Board board = boardRepository.findByBoardName(boardName).orElseThrow(
//        () -> throw new CustomException(ErrorCode.BOARD_NOT_FOUND)
//    );
//
//    Column column = columnRepository.findByColumnName(columnName).orElseThrow(
//        () -> throw new CustomException(ErrorCode.COLUMN_NOT_FOUND)
//    );

    Card card = cardRepository.findByCardName(cardName).orElseThrow(
        () -> new CustomException(ErrorCode.CARD_NOT_FOUND)
    );

    cardRepository.delete(card);
  }

}
