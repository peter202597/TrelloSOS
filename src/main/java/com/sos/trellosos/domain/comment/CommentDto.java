package com.sos.trellosos.domain.comment;

import java.time.LocalDateTime;

public record CommentDto(Long id, String text, Long userId, LocalDateTime createdAt) {
}
