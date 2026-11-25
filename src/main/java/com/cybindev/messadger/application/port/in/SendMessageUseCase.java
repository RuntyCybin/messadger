package com.cybindev.messadger.application.port.in;

import com.cybindev.messadger.domain.model.Message;

public interface SendMessageUseCase {
    Message send(String conversationId, String sender, String content);
}
