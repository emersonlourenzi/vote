package com.vote.contract.v1.association.mapper.request;

import com.vote.contract.v1.association.model.request.AssociateContractRequest;
import com.vote.impl.association.model.request.AssociateImplRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AssociateContractResponseToImplMapper {
    
    static AssociateImplRequest mapFrom(AssociateContractRequest associateContractRequest) {
        return Mappers.getMapper(AssociateContractResponseToImplMapper.class)
            .mapper(associateContractRequest);
    }
    
    AssociateImplRequest mapper(AssociateContractRequest associateContractRequest);
    
}
