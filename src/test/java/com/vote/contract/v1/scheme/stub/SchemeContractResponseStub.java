package com.vote.contract.v1.scheme.stub;

import com.vote.contract.v1.scheme.model.response.SchemeContractResponse;

public class SchemeContractResponseStub {
    
    public static SchemeContractResponse stubSchemeContractResponse() {
        return SchemeContractResponse.builder()
            .motiveScheme("motive")
            .build();
    }
    
    public static SchemeContractResponse stubSchemeContractResponseFindAll() {
        return SchemeContractResponse.builder()
            .motiveScheme("motive")
            .build();
    }
    
    public static SchemeContractResponse stubSchemeContractResponseFindById() {
        return SchemeContractResponse.builder()
            .motiveScheme("motive")
            .build();
    }
    
}
