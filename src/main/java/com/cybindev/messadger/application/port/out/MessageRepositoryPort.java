package com.cybindev.messadger.application.port.out;

import java.util.List;

import com.cybindev.messadger.domain.model.Message;

public interface MessageRepositoryPort {
    Message save(Message message);
    List<Message> findByConversationId(String conversationId);
}
