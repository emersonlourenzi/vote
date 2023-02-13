package com.vote.impl.voting.repository;

import com.vote.impl.voting.repository.entity.VotingEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotingRepository extends ReactiveMongoRepository<VotingEntity, String> {
}
