package com.vote.contract.v1.voting.mapper.response;

import com.vote.contract.v1.voting.model.response.VoteResultContractResponse;
import com.vote.impl.voting.model.response.VoteResultImplResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VoteResultImplResponseToContractMapper {
    
    static VoteResultContractResponse mapFrom(VoteResultImplResponse voteResultImplResponse) {
        return Mappers.getMapper(VoteResultImplResponseToContractMapper.class)
            .mapper(voteResultImplResponse);
    }
    
    VoteResultContractResponse mapper(VoteResultImplResponse voteResultImplResponse);
    
}
