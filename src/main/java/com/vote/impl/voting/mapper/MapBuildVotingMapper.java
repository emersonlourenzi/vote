package com.vote.impl.voting.mapper;

import com.vote.impl.voting.model.request.VotingImplRequest;
import com.vote.impl.voting.repository.entity.VotingEntity;
import reactor.core.publisher.Mono;

import static com.vote.impl.voting.mapper.MapBuildSchemeMapper.buildDateFinal;

public interface MapBuildVotingMapper {
    
    static Mono<VotingEntity> mapBuildVoting(VotingImplRequest votingImplRequest) {
        return Mono.just(VotingEntity.builder()
            .idScheme(votingImplRequest.getIdScheme())
            .initialDateVoting(votingImplRequest.getInitialDateVoting())
            .finalDateVoting(buildDateFinal(votingImplRequest.getInitialDateVoting(),
                votingImplRequest.getVotingTime()))
            .build());
    }
    
}
