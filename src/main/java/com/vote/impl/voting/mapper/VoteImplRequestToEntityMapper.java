package com.vote.impl.voting.mapper;

import com.vote.impl.voting.model.request.VoteImplRequest;
import com.vote.impl.voting.repository.entity.VoteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VoteImplRequestToEntityMapper {
    
    static VoteEntity mapFrom(VoteImplRequest voteImplRequest) {
        return Mappers.getMapper(VoteImplRequestToEntityMapper.class)
            .mapper(voteImplRequest);
    }
    
    @Mapping(target = "id", ignore = true)
    VoteEntity mapper(VoteImplRequest voteImplRequest);
    
}
