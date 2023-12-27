package com.sos.trellosos.domain.comment;

import com.sos.trellosos.domain.card.Card;
import com.sos.trellosos.domain.user.User;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final CardRepository cardRepository;
    private final UserRepository userRepository;

    public Comment addCommentToCard(Long cardId, Long userId, String text) {
        Card card = cardRepository.findById(cardId)
            .orElseThrow(() -> new ResourceNotFoundException("Card not found with id " + cardId));
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));

        Comment comment = Comment.builder()
            .text(text)
            .card(card)
            .user(user)
            .createdAt(LocalDateTime.now())
            .build();
        return commentRepository.save(comment);
    }
}
