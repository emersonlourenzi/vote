package com.vote.impl.scheme.mapper;

import com.vote.impl.scheme.model.request.SchemeImplRequest;
import com.vote.impl.scheme.repository.entity.SchemeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SchemeImplRequestToEntityMapper {
    
    static SchemeEntity mapFrom(SchemeImplRequest schemeImplRequest) {
        return Mappers.getMapper(SchemeImplRequestToEntityMapper.class)
            .mapper(schemeImplRequest);
    }
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "initialDateScheme", ignore = true)
    @Mapping(target = "finalDateScheme", ignore = true)
    SchemeEntity mapper(SchemeImplRequest schemeImplRequest);
    
}
