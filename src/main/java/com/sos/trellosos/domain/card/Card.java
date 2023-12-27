package com.sos.trellosos.domain.card;

import com.sos.trellosos.domain.cardUser.CardUser;
import com.sos.trellosos.domain.column.Column;
import com.sos.trellosos.domain.comment.Comment;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@DynamicInsert
@DynamicUpdate
public class Card {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String cardName;

  private String cardDescription;

  private String cardColor;

  private LocalDateTime dueDate;

  @Builder
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
  private Column column;

  @OneToMany(mappedBy = "card", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Comment> comments = new LinkedHashSet<>();

  @OneToMany(mappedBy = "card", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<CardUser> cardUsers = new LinkedHashSet<>();

  /**
   * 연관관계 편의 메소드 - 반대쪽에는 연관관계 편의 메소드가 없도록 주의합니다.
   */
  public void setColumn(Column column) {
    this.column = column;
  }
  /**
   * 서비스 메소드 - 외부에서 엔티티를 수정할 메소드를 정의합니다. (단일 책임을 가지도록 주의합니다.)
   */
}
