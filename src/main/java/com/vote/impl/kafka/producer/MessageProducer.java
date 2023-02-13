package com.vote.impl.kafka.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageProducer {
    
    private final KafkaTemplate<String, Object> kafkaTemplate;
    
    public Mono<Void> sendMessage(String topicName, String key, final Object data) {
        
        Message<Object> message = MessageBuilder
            .withPayload(data)
            .setHeader(KafkaHeaders.TOPIC, topicName)
            .setHeader(KafkaHeaders.MESSAGE_KEY, key)
            .build();
        
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(message);
        future.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onFailure(Throwable throwable) {
                log.error("Erro ao enviar a mensagem ao topic %s, mensagem: %s",
                    topicName, message, throwable.getMessage());
            }
            
            @Override
            public void onSuccess(SendResult<String, Object> stringStringSendResult) {
                log.info(String.format("Mensagem enviada com sucesso ao topic %s, mensagem: %s",
                    topicName, message));
            }
        });
        
        return Mono.empty();
    }
    
}
