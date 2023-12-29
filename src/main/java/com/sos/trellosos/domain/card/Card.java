package com.sos.trellosos.domain.card;



import com.sos.trellosos.global.entity.Timestamped;
import com.sos.trellosos.domain.column.entity.Columns;
import com.sos.trellosos.domain.comment.Comment;
import com.sos.trellosos.domain.user.User;
import com.sos.trellosos.domain.worker.Worker;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

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

  public Card(CardRequestDto requestDto) {
    this.cardName = requestDto.getCardName();
    this.cardDescription = requestDto.getCardDescription();
    this.cardColor = requestDto.getCardColor();
  }

  /**
   * 컬럼 - 연관관계 컬럼을 제외한 컬럼을 정의합니다.
   */

  /**
   * 생성자 - 약속된 형태로만 생성가능하도록 합니다.
   */


  /**
   * 연관관계 - Foreign Key 값을 따로 컬럼으로 정의하지 않고 연관 관계로 정의합니다.
   */
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "column_id")
  private Columns columns;

  @OneToMany(mappedBy = "card", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Comment> comments = new ArrayList<>();

  @OneToMany(mappedBy = "card", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Worker> workers = new ArrayList<>();

  /**
   * 연관관계 편의 메소드 - 반대쪽에는 연관관계 편의 메소드가 없도록 주의합니다.
   */
  public void setColumns(Columns columns) {
    this.columns = columns;
  }

  public void setSequence(Integer number) {
    this.sequence = number;
  }

  /**
   * 서비스 메소드 - 외부에서 엔티티를 수정할 메소드를 정의합니다. (단일 책임을 가지도록 주의합니다.)
   */

  public Worker joinUser(User user) {
    var worker = new Worker(user, this);
    this.workers.add(worker);
    user.getWorkers().add(worker);
    return worker;
  }

  public void update(CardRequestDto requestDto) {
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
}
