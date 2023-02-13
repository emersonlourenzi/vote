package com.vote.impl.scheme.mapper;

import com.vote.impl.scheme.model.response.SchemeImplResponse;
import com.vote.impl.scheme.repository.entity.SchemeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SchemeEntityToResponseMapper {
    
    static SchemeImplResponse mapFrom(SchemeEntity schemeEntity) {
        return Mappers.getMapper(SchemeEntityToResponseMapper.class)
            .mapper(schemeEntity);
    }
    
    SchemeImplResponse mapper(SchemeEntity schemeEntity);
    
}
