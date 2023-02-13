package com.vote.impl.association.repository;

import com.vote.impl.association.repository.entity.AssociateEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface AssociateRepository extends ReactiveMongoRepository<AssociateEntity, String> {
    
    Mono<AssociateEntity> findByCpf(String cpf);
    
    Mono<Void> deleteByCpf(String cpf);
    
}
