package com.cybindev.messadger.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.cybindev.messadger.application.port.out.MessageRepositoryPort;
import com.cybindev.messadger.domain.exception.InvalidMessageException;
import com.cybindev.messadger.domain.model.Message;

class MessageServiceTest {

    static class InMemoryMessageRepository implements MessageRepositoryPort {
        private final List<Message> store = new ArrayList<>();

        @Override
        public Message save(Message message) {
            store.add(message);
            return message;
        }

        @Override
        public List<Message> findByConversationId(String conversationId) {
            return store.stream().filter(m -> m.conversationId().equals(conversationId)).toList();
        }
    }

    private final MessageService service = new MessageService(new InMemoryMessageRepository());

    @Test
    void sendStoresMessageInConversation() {
        service.send("c1", "ana", "hola");
        service.send("c2", "luis", "otra conversación");

        List<Message> messages = service.getMessages("c1");

        assertEquals(1, messages.size());
        assertEquals("ana", messages.get(0).sender());
        assertEquals("hola", messages.get(0).msg());
    }

    @Test
    void rejectsBlankMessage() {
        assertThrows(InvalidMessageException.class, () -> service.send("c1", "ana", "  "));
    }
}
