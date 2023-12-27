package com.sos.trellosos.domain.card;

import com.sos.trellosos.exception.CustomException;
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


}
