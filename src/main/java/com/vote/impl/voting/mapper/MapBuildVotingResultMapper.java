package com.vote.impl.voting.mapper;

import java.util.List;

import com.vote.commons.enums.VoteEnum;
import com.vote.commons.exceptions.ExceptionUtils;
import com.vote.impl.voting.model.response.VoteResultImplResponse;
import com.vote.impl.voting.repository.entity.VoteEntity;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Mono;

public interface MapBuildVotingResultMapper {
    
    static Mono<VoteResultImplResponse> buildResultVoting(List<VoteEntity> voteEntities) {
        if (voteEntities.size() == 0) {
            return Mono.error(ExceptionUtils.buildError(
                HttpStatus.NOT_FOUND,
                "Não houve votos nesta votação"
            ));
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
