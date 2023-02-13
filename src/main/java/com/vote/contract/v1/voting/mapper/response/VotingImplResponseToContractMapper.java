package com.vote.contract.v1.voting.mapper.response;

import com.vote.contract.v1.voting.model.response.VotingContractResponse;
import com.vote.impl.voting.model.response.VotingImplResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VotingImplResponseToContractMapper {
    
    static VotingContractResponse mapFrom(VotingImplResponse votingImplResponse) {
        return Mappers.getMapper(VotingImplResponseToContractMapper.class)
            .mapper(votingImplResponse);
    }
    
    VotingContractResponse mapper(VotingImplResponse votingImplResponse);
    
}
