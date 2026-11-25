package com.cybindev.messadger.application.port.in;

import java.util.List;

import com.cybindev.messadger.domain.model.Message;

public interface GetConversationUseCase {
    List<Message> getMessages(String conversationId);
}
