package com.vote.impl.association.mapper;

import com.vote.impl.association.model.response.AssociateImplResponse;
import com.vote.impl.association.repository.entity.AssociateEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AssociateEntityToResponseMapper {
    
    static AssociateImplResponse mapFrom(AssociateEntity associateEntity) {
        return Mappers.getMapper(AssociateEntityToResponseMapper.class)
            .mapper(associateEntity);
    }
    
    AssociateImplResponse mapper(AssociateEntity associateEntity);
    
}
