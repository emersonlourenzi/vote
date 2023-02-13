package com.vote.impl.scheme.repository;

import com.vote.impl.scheme.repository.entity.SchemeEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchemeRepository extends ReactiveMongoRepository<SchemeEntity, String> {
}
