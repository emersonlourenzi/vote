package com.vote.impl.voting.repository;

import com.vote.impl.voting.repository.entity.VoteEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface VoteRepository extends ReactiveMongoRepository<VoteEntity, String> {
    
    Mono<VoteEntity> findByCpfAssociate(String cpf);
    Flux<VoteEntity> findByIdVoting(String id);
    
}
