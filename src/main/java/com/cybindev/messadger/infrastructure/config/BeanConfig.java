package com.cybindev.messadger.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cybindev.messadger.application.port.out.MessageRepositoryPort;
import com.cybindev.messadger.application.service.MessageService;

@Configuration
public class BeanConfig {
    @Bean
    public MessageService messageService(MessageRepositoryPort repository) {
        return new MessageService(repository);
    }
}
