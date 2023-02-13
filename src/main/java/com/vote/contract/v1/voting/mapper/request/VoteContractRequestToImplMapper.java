package com.vote.contract.v1.voting.mapper.request;

import com.vote.contract.v1.voting.model.request.VoteContractRequest;
import com.vote.impl.voting.model.request.VoteImplRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VoteContractRequestToImplMapper {
    
    static VoteImplRequest mapFrom(VoteContractRequest voteContractRequest) {
        return Mappers.getMapper(VoteContractRequestToImplMapper.class)
            .mapper(voteContractRequest);
    }
    
    VoteImplRequest mapper(VoteContractRequest voteContractRequest);
    
}
