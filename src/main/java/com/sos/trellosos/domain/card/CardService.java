package com.sos.trellosos.domain.card;

import com.sos.trellosos.exception.CustomException;
import com.sos.trellosos.exception.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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
}
