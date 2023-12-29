package com.sos.trellosos.domain.comment;

import com.sos.trellosos.domain.card.Card;
import com.sos.trellosos.domain.card.CardRepository;
import com.sos.trellosos.domain.user.User;
import java.time.LocalDateTime;

import com.sos.trellosos.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final CardRepository cardRepository;
    private final UserRepository userRepository;

    @Transactional
    public CommentDto addCommentToCard(Long cardId, Long userId, String text) {
        Card card = cardRepository.findById(cardId)
            .orElseThrow(() -> new RuntimeException("Card not found with id " + cardId));
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found with id " + userId));

        Comment comment = Comment.builder()
            .text(text)
            .card(card)
            .user(user)
            .createdAt(LocalDateTime.now())
            .build();
        Comment savedComment = commentRepository.save(comment);
        return new CommentDto(savedComment.getId(), savedComment.getText(), savedComment.getUser().getId(), savedComment.getCreatedAt());
    }
}
