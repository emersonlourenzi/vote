package com.vote.impl.voting.mapper;

import com.vote.impl.voting.model.response.VotingImplResponse;
import com.vote.impl.voting.repository.entity.VotingEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VotingEntityToImplResponseMapper {
    
    static VotingImplResponse mapFrom(VotingEntity votingEntity) {
        return Mappers.getMapper(VotingEntityToImplResponseMapper.class)
            .mapper(votingEntity);
    }
    
    VotingImplResponse mapper(VotingEntity votingEntity);
    
}
