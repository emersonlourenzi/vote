package com.vote.impl.voting.mapper;

import com.vote.commons.enums.VoteEnum;
import com.vote.commons.exceptions.voting.VotingNotFoundException;
import com.vote.impl.voting.model.response.VoteResultImplResponse;
import com.vote.impl.voting.repository.entity.VoteEntity;
import reactor.core.publisher.Mono;

import java.util.List;

public interface MapBuildVotingResultMapper {

    static Mono<VoteResultImplResponse> buildResultVoting(List<VoteEntity> voteEntities) {
        if (voteEntities.size() == 0) {
            return Mono.error(VotingNotFoundException::new);
        }
        return Mono.just(VoteResultImplResponse.builder()
            .idVoting(getIdVoting(voteEntities))
            .quantityNo(getQuantityVotes(voteEntities, VoteEnum.NO))
            .quantityYes(getQuantityVotes(voteEntities, VoteEnum.YES))
            .build());
    }

    private static String getIdVoting(List<VoteEntity> voteEntities) {
        return voteEntities.stream().findFirst().map(VoteEntity::getIdVoting).orElse(null);
    }

    private static long getQuantityVotes(List<VoteEntity> voteEntities, VoteEnum voteEnum) {
        return voteEntities.stream().filter(vote -> vote.getVote().equals(voteEnum)).count();
    }

}
