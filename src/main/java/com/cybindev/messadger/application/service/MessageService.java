package com.cybindev.messadger.application.service;

import java.util.List;

import com.cybindev.messadger.application.port.in.GetConversationUseCase;
import com.cybindev.messadger.application.port.in.SendMessageUseCase;
import com.cybindev.messadger.application.port.out.MessageRepositoryPort;
import com.cybindev.messadger.domain.model.Message;

public class MessageService implements SendMessageUseCase, GetConversationUseCase{


    private final MessageRepositoryPort messageRepo;

    public MessageService(MessageRepositoryPort messagePort) {
        this.messageRepo = messagePort;
    }

    @Override
    public List<Message> getMessages(String conversationId) {
        return this.messageRepo.findByConversationId(conversationId);
    }

    @Override
    public Message send(String conversationId, String sender, String content) {
        return this.messageRepo.save(Message.create(conversationId, sender, content));
    }
    
}
