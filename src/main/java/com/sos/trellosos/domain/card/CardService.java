package com.sos.trellosos.domain.card;


import com.sos.trellosos.domain.column.repository.ColumnRepository;
import com.sos.trellosos.domain.user.User;
import com.sos.trellosos.domain.user.UserRepository;
import com.sos.trellosos.domain.worker.Worker;
import com.sos.trellosos.domain.worker.WorkerRepository;
import com.sos.trellosos.domain.column.entity.Columns;
import com.sos.trellosos.global.exception.CustomException;
import com.sos.trellosos.global.exception.ErrorCode;
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
  private final ColumnRepository columnRepository;
  private final UserRepository userRepository;
  private final WorkerRepository workerRepository;


  public CardResponseDto createCard(CardRequestDto requestDto) {
    Columns columns = columnRepository.findById(requestDto.getColumnId()).orElseThrow(
        () -> new CustomException(ErrorCode.COLUMN_NOT_FOUND)
    );

    Card card = new Card(requestDto);

    card.setColumns(columns);

    Card savedCard = cardRepository.save(card);

    return new CardResponseDto(savedCard);
  }

  public CardResponseDto getCard(Long cardId) {
    Card card = findCard(cardId);

    return new CardResponseDto(card);
  }

  public Page<CardResponseDto> getCardPage(int page, int size, String sortBy, boolean isAsc) {

    Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
    Sort sort = Sort.by(direction, sortBy);
    Pageable pageable = PageRequest.of(page, size, sort);

    Page<Card> cardList = cardRepository.findAll(pageable);

    return cardList.map(CardResponseDto::new);
  }

  @Transactional
  public CardResponseDto updateCard(Long cardId, CardRequestDto requestDto) {
    Card card = findCard(cardId);

    User user = userRepository.findById(requestDto.getUserId()).orElseThrow(
        () -> new CustomException(ErrorCode.USER_NOT_FOUND)
    );

    card.update(requestDto);

    Worker worker = card.joinUser(user);

    CardResponseDto responseDto = new CardResponseDto(card);

    responseDto.setWorker(worker.getUser().getUsername());

    return responseDto;
  }

  @Transactional
  public void deleteCard(Long cardId) {

    Card card = findCard(cardId);

    cardRepository.delete(card);
  }

  private Card findCard(Long cardId) {
    Card card = cardRepository.findById(cardId).orElseThrow(
        () -> new CustomException(ErrorCode.CARD_NOT_FOUND)
    );
    return card;
  }

}
