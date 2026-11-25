package com.cybindev.messadger.infrastructure.adapter.in.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cybindev.messadger.application.port.in.GetConversationUseCase;
import com.cybindev.messadger.application.port.in.SendMessageUseCase;
import com.cybindev.messadger.domain.exception.InvalidMessageException;
import com.cybindev.messadger.domain.model.Message;

@RestController
@RequestMapping("/conversations/{conversationId}/messages")
public class MessageController {

    private final SendMessageUseCase sendMessage;
    private final GetConversationUseCase getConversation;

    public MessageController(SendMessageUseCase sendMessage, GetConversationUseCase getConversation) {
        this.sendMessage = sendMessage;
        this.getConversation = getConversation;
    }

    @PostMapping
    public ResponseEntity<Message> send(@PathVariable String conversationId,
                                        @RequestBody SendMessageRequest request) {
        Message message = sendMessage.send(conversationId, request.sender(), request.content());
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @GetMapping
    public List<Message> list(@PathVariable String conversationId) {
        return getConversation.getMessages(conversationId);
    }

    @ExceptionHandler(InvalidMessageException.class)
    public ResponseEntity<String> handleInvalid(InvalidMessageException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}