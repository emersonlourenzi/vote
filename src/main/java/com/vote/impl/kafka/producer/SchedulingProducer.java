package com.vote.impl.kafka.producer;

import java.time.LocalDateTime;

import com.vote.impl.voting.VotingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@AllArgsConstructor
public class SchedulingProducer {
    
    private final VotingService votingService;
    
    @Scheduled(fixedRate = 300000)
    public void schedulingProducerVotes() {
        log.info("POSTANDO VOTAÇÃO DOS ULTIMOS 5 MINUTOS ".concat(LocalDateTime.now().toString()));
        votingService.findAll()
            .filter(votingEntity -> votingEntity.getFinalDateVoting()
                .isAfter(LocalDateTime.now().minusMinutes(1)))
            .map(votingService::sendMessageProducer)
            .collectList()
            .flatMap(a -> Mono.empty())
            .subscribe();
    }
    
}
