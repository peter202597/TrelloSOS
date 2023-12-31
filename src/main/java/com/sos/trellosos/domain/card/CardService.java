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


  public CardResponseDto createCard(CardRequestDto requestDto) {

    Columns columns = columnRepository.findById(requestDto.getColumnId()).orElseThrow(
        () -> new CustomException(ErrorCode.COLUMN_NOT_FOUND)
    );
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

  @Transactional
  public List<CardResponseDto> changeSequence(Long cardId, ChangeSequenceRequestDto requestDto) {
    Integer newSequence = requestDto.getNewSequence();

    Card card = findCard(cardId);
    Integer currentSequence = card.getSequence();
    Long columnId = card.getColumns().getId();

    if (currentSequence < newSequence) {
      cardRepository.decrementSequenceBetween(currentSequence + 1, newSequence, columnId);
    } else {
      cardRepository.incrementSequenceBetween(newSequence, currentSequence - 1, columnId);
    }
    card.setSequence(newSequence);
    cardRepository.save(card);

    return cardRepository.findAllByOrderBySequenceAsc().stream().map(CardResponseDto::new)
        .toList();
  }

  @Transactional
  public CardResponseDto changeColumn(Long cardId, ChangeColumnRequestDto requestDto) {
    Card card = findCard(cardId);

    Columns newColumns = columnRepository.findById(requestDto.getNewColumnsId()).orElseThrow(
        () -> new CustomException(ErrorCode.COLUMN_NOT_FOUND)
    );

    Integer currentSequence = card.getSequence();
    Integer newSequence = requestDto.getNewSequence();

    Long oldColumnsId = card.getColumns().getId();
    Long newColumnsId = newColumns.getId();

    int lastSequenceInOldColumns = cardRepository.countByColumnsId(oldColumnsId);

    card.setColumns(newColumns);


    int lastSequenceInNewColumns = cardRepository.countByColumnsId(newColumnsId);

    // 기존 컬럼에 있는 기존 카드의 시퀀스보다 큰 카드들은 시퀀스 -1
    // 새로운 컬럼에 있는 새 시퀀스 보다 크거나 같은 카드들은 시퀀스 +1
      cardRepository.decrementSequenceBetween(currentSequence + 1, lastSequenceInOldColumns, oldColumnsId);

      cardRepository.incrementSequenceBetween(newSequence, lastSequenceInNewColumns, newColumnsId);

    card.setSequence(newSequence);



    return new CardResponseDto(card);
  }

  @Transactional
  public void deleteAll() {
    cardRepository.deleteAll();
  }
}
