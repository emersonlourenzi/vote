package com.vote.impl.voting.mapper;

import com.vote.impl.voting.model.response.VoteImplResponse;
import com.vote.impl.voting.repository.entity.VoteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VoteEntityToImplResponseMapper {
    
    static VoteImplResponse mapFrom(VoteEntity voteEntity) {
        return Mappers.getMapper(VoteEntityToImplResponseMapper.class)
            .mapper(voteEntity);
    }
    
    VoteImplResponse mapper(VoteEntity voteEntity);
    
}
