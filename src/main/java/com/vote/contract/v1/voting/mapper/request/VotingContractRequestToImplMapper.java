package com.vote.contract.v1.voting.mapper.request;

import com.vote.contract.v1.voting.model.request.VotingContractRequest;
import com.vote.impl.voting.model.request.VotingImplRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VotingContractRequestToImplMapper {
    
    static VotingImplRequest mapFrom(VotingContractRequest votingContractRequest) {
        return Mappers.getMapper(VotingContractRequestToImplMapper.class)
            .mapper(votingContractRequest);
    }
    
    @Mapping(target = "initialDateVoting", ignore = true)
    VotingImplRequest mapper(VotingContractRequest votingContractRequest);
    
}
