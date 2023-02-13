package com.vote.contract.v1.voting.mapper.response;

import com.vote.contract.v1.voting.model.response.VoteContractResponse;
import com.vote.impl.voting.model.response.VoteImplResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VoteImplResponseToContract {
    
    static VoteContractResponse mapFrom(VoteImplResponse voteImplResponse) {
        return Mappers.getMapper(VoteImplResponseToContract.class)
            .mapper(voteImplResponse);
    }
    
    VoteContractResponse mapper(VoteImplResponse voteImplResponse);
    
}
