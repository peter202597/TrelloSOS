package com.sos.trellosos.domain.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cards/{cardId}/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Comment> addComment(@PathVariable Long cardId, @RequestBody CommentDto commentDto) {
        Comment comment = commentService.addCommentToCard(cardId, commentDto.getUserId(), commentDto.getText());
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }
}
