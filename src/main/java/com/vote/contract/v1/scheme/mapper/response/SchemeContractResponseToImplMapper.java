package com.vote.contract.v1.scheme.mapper.response;

import com.vote.contract.v1.scheme.model.response.SchemeContractResponse;
import com.vote.impl.scheme.model.response.SchemeImplResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SchemeContractResponseToImplMapper {
    
    static SchemeContractResponse mapFrom(SchemeImplResponse schemeImplResponse) {
        return Mappers.getMapper(SchemeContractResponseToImplMapper.class)
            .mapper(schemeImplResponse);
    }
    
    SchemeContractResponse mapper(SchemeImplResponse schemeImplResponse);
    
}
