package com.sos.trellosos.domain.card;


import com.sos.trellosos.domain.column.entity.Columns;
import com.sos.trellosos.domain.column.repository.ColumnRepository;
import com.sos.trellosos.domain.user.User;
import com.sos.trellosos.domain.user.UserRepository;
import com.sos.trellosos.domain.worker.Worker;
import com.sos.trellosos.domain.worker.WorkerRepository;
import com.sos.trellosos.global.exception.CustomException;
import com.sos.trellosos.global.exception.ErrorCode;
import jakarta.transaction.Transactional;
import java.util.List;
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

  public CardResponseDto createCard(CreateCardRequestDto requestDto) {

    Columns columns = findColumn(requestDto.getColumnId());

    Integer count = cardRepository.countByColumnsId(requestDto.getColumnId()) + 1;

    Card card = new Card(requestDto);

    card.setColumns(columns);
    card.setSequence(count);

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
  public CardResponseDto updateCard(Long cardId, UpdateCardRequestDto requestDto) {
    Card card = findCard(cardId);

    card.update(requestDto);

    return new CardResponseDto(card);

  }

  @Transactional
  public void deleteCard(Long cardId) {

    Card card = findCard(cardId);

    cardRepository.delete(card);
  }

  @Transactional
  public List<CardResponseDto> changeSequence(Long cardId, ChangeSequenceRequestDto requestDto) {
    Integer newSequence = requestDto.getNewSequence();

    Card card = findCard(cardId);
    Integer currentSequence = card.getSequence();
    Long columnId = card.getColumns().getId();

    if (currentSequence < newSequence) {
      cardRepository.pullSequence(currentSequence + 1, newSequence, columnId);
    } else {
      cardRepository.pushSequence(newSequence, currentSequence - 1, columnId);
    }
    card.setSequence(newSequence);

    return cardRepository.findAllByOrderBySequenceAsc().stream().map(CardResponseDto::new)
        .toList();
  }

  @Transactional
  public CardResponseDto changeColumn(Long cardId, ChangeColumnRequestDto requestDto) {
    Card card = findCard(cardId);

    Columns newColumns = findColumn(requestDto.getNewColumnsId());

    Integer currentSequence = card.getSequence();
    Integer newSequence = requestDto.getNewSequence();

    Long oldColumnsId = card.getColumns().getId();
    Long newColumnsId = newColumns.getId();

    Integer lastSequenceInOldColumns = cardRepository.countByColumnsId(oldColumnsId);

    card.setColumns(newColumns);
    card.setSequence(null);

    Integer lastSequenceInNewColumns = cardRepository.countByColumnsId(newColumnsId);

    cardRepository.pullSequence(currentSequence + 1, lastSequenceInOldColumns, oldColumnsId);

    cardRepository.pushSequence(newSequence, lastSequenceInNewColumns, newColumnsId);

    card.setSequence(newSequence);

    return new CardResponseDto(card);

  }

  @Transactional
  public void deleteAll() {
    cardRepository.deleteAll();
  }

  @Transactional
  public CardResponseDto setDueDate(Long cardId, DueDateRequestDto requestDto) {

    Card card = findCard(cardId);

    card.setDueDate(requestDto.getDueDate());

    return new CardResponseDto(card);

  }


  @Transactional
  public CardResponseDto allocateWorker(Long cardId, WorkerRequestDto requestDto) {
    Card card = findCard(cardId);

    User user = findUser(requestDto.getUserId());

    card.allocateWorker(user);

    return new CardResponseDto(card);
  }

  @Transactional
  public CardResponseDto detachWorker(Long cardId, WorkerRequestDto requestDto) {
    Card card = findCard(cardId);

    Worker worker = workerRepository.findByUserIdAndCardId(requestDto.getUserId(), cardId)
        .orElseThrow(
            () -> new CustomException(ErrorCode.WORKER_NOT_FOUND)
        );

    card.detachWorker(worker);

    return new CardResponseDto(card);

  }


  private Card findCard(Long cardId) {
    return cardRepository.findById(cardId).orElseThrow(
        () -> new CustomException(ErrorCode.CARD_NOT_FOUND)
    );
  }

  private User findUser(Long userId) {
    return userRepository.findById(userId).orElseThrow(
        () -> new CustomException(ErrorCode.USER_NOT_FOUND)
    );
  }

  private Columns findColumn(Long columnId) {
    return columnRepository.findById(columnId).orElseThrow(
        () -> new CustomException(ErrorCode.COLUMN_NOT_FOUND)
    );
  }
}