package com.sos.trellosos.domain.card;


import com.sos.trellosos.domain.column.entity.Columns;
import com.sos.trellosos.domain.comment.Comment;
import com.sos.trellosos.domain.user.User;
import com.sos.trellosos.domain.worker.Worker;
import com.sos.trellosos.global.entity.Timestamped;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@DynamicInsert
@DynamicUpdate
public class Card extends Timestamped {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String cardName;

  private String cardDescription;

  private String cardColor;

  private Integer sequence;

  private LocalDateTime dueDate;

  public Card(CreateCardRequestDto requestDto) {
    this.cardName = requestDto.getCardName();
    this.cardDescription = requestDto.getCardDescription();
    this.cardColor = requestDto.getCardColor();
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "column_id")
  private Columns columns;

  @OneToMany(mappedBy = "card", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Comment> comments = new ArrayList<>();

  @OneToMany(mappedBy = "card", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Worker> workers = new ArrayList<>();

  public void setColumns(Columns columns) {
    this.columns = columns;
  }

  public void setSequence(Integer number) {
    this.sequence = number;
  }

  public void setDueDate(LocalDateTime date) {
    this.dueDate = date;
  }

  public void allocateWorker(User user) {
    Worker worker = new Worker(user, this);
    this.workers.add(worker);
    user.getWorkers().add(worker);
  }


  public void update(UpdateCardRequestDto requestDto) {
    if (requestDto.getCardName() != null) {
      this.cardName = requestDto.getCardName();
    }
    if (requestDto.getCardColor() != null) {
      this.cardColor = requestDto.getCardColor();
    }
    if (requestDto.getCardDescription() != null) {
      this.cardDescription = requestDto.getCardDescription();
    }

  }

  public void detachWorker(Worker worker) {
    worker.getUser().getWorkers().remove(worker);
    this.workers.remove(worker);
  }
}
