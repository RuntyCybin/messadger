package com.cybindev.messadger.infrastructure.adapter.out.jpa;

import java.time.Instant;
import java.util.UUID;

import com.cybindev.messadger.domain.model.Message;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "messages")
public class MessageEntity {
    @Id 
    private UUID id;
    private String conversationId;
    private String sender;
    @Column(length = 1000) 
    private String content;
    private Instant sentAt;

    protected MessageEntity() {}

    static MessageEntity fromDomain(Message m) {
        MessageEntity e = new MessageEntity();
        e.id = m.id();
        e.conversationId = m.conversationId();
        e.sender = m.sender();
        e.content = m.msg();
        e.sentAt = m.sentAt();
        return e;
    }

    Message toDomain() {
        return new Message(id, conversationId, sender, content, sentAt);
    }
}