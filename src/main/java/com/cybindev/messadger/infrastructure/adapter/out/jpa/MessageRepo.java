package com.cybindev.messadger.infrastructure.adapter.out.jpa;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepo extends JpaRepository<MessageEntity, UUID> {
    List<MessageEntity> findByConversationIdOrderBySentAtAsc(String conversationId);
}
