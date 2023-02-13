package com.vote.contract.v1.scheme.mapper.request;

import com.vote.contract.v1.scheme.model.request.SchemeContractRequest;
import com.vote.impl.scheme.model.request.SchemeImplRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SchemeImplRequestToContractMapper {
    
    static SchemeImplRequest mapFrom(SchemeContractRequest schemeContractRequest) {
        return Mappers.getMapper(SchemeImplRequestToContractMapper.class)
            .mapper(schemeContractRequest);
    }
    
    SchemeImplRequest mapper(SchemeContractRequest schemeContractRequest);
    
}
