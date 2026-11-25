package com.cybindev.messadger.infrastructure.adapter.out.jpa;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Component;

import com.cybindev.messadger.application.port.out.MessageRepositoryPort;
import com.cybindev.messadger.domain.model.Message;

@Component 
public class MessagePersistenceAdapter implements MessageRepositoryPort {

    private final MessageRepo jpaRepo;

    public MessagePersistenceAdapter(MessageRepo jpa) {
        this.jpaRepo = jpa;
    }

    @Override
    public Message save(Message message) {
        Objects.requireNonNull(message, "Object must not be null");
        
        return this.jpaRepo.save(
            MessageEntity.fromDomain(message))
            .toDomain();
    }

    @Override
    public List<Message> findByConversationId(String conversationId) {
        return this.jpaRepo.findByConversationIdOrderBySentAtAsc(conversationId).stream()
        .map(MessageEntity::toDomain)
        .toList();
    }
    
}
