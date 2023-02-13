package com.vote.impl.voting.mapper;

import java.time.LocalDateTime;

import com.vote.impl.scheme.repository.entity.SchemeEntity;
import com.vote.impl.voting.model.request.VotingImplRequest;
import reactor.core.publisher.Mono;

public interface MapBuildSchemeMapper {
    
    static Mono<SchemeEntity> mapBuildScheme(SchemeEntity schemeEntity, VotingImplRequest votingImplRequest) {
        return Mono.just(SchemeEntity.builder()
            .id(schemeEntity.getId())
            .motiveScheme(schemeEntity.getMotiveScheme())
            .initialDateScheme(votingImplRequest.getInitialDateVoting())
            .finalDateScheme(
                buildDateFinal(votingImplRequest.getInitialDateVoting(), votingImplRequest.getVotingTime()))
            .build());
    }
    
    static LocalDateTime buildDateFinal(LocalDateTime initialDate, Long votingTime) {
        return initialDate.plusMinutes(votingTime);
    }
    
}
