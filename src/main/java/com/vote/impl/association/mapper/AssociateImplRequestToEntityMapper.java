package com.vote.impl.association.mapper;

import com.vote.impl.association.model.request.AssociateImplRequest;
import com.vote.impl.association.repository.entity.AssociateEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AssociateImplRequestToEntityMapper {
    
    static AssociateEntity mapFrom(AssociateImplRequest associateImplRequest) {
        return Mappers.getMapper(AssociateImplRequestToEntityMapper.class)
            .mapper(associateImplRequest);
    }
    
    @Mapping(target = "id", ignore = true)
    AssociateEntity mapper(AssociateImplRequest associateImplRequest);
    
}
