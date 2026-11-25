package com.cybindev.messadger.domain.model;

import java.time.Instant;
import java.util.UUID;

import com.cybindev.messadger.domain.exception.InvalidMessageException;

public record Message(
    UUID id, 
    String conversationId,
    String sender,
    String msg,
    Instant sentAt) {

        private static final int MAX_LENGTH = 1000;


        public Message {

            if (msg == null || msg.isBlank()) {

                throw new InvalidMessageException(
                    "El mensaje no puede estar vacío");

            }

            if (msg.length() > MAX_LENGTH) {

                throw new InvalidMessageException("El mensaje supera " + MAX_LENGTH + " caracteres");

            }

        }

        public static Message create(String conversationId, String sender, String content) {
            return new Message(
                UUID.randomUUID(), 
                conversationId, 
                sender, 
                content, 
                Instant.now());
        }
    
}
