package com.vote.contract.v1.association.mapper.response;

import com.vote.contract.v1.association.model.response.AssociateContractResponse;
import com.vote.impl.association.model.response.AssociateImplResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AssociateImplResponseToContractMapper {
    
    static AssociateContractResponse mapFrom(AssociateImplResponse associateImplResponse) {
        return Mappers.getMapper(AssociateImplResponseToContractMapper.class)
            .mapper(associateImplResponse);
    }
    
    AssociateContractResponse mapper(AssociateImplResponse associateImplResponse);
    
}
