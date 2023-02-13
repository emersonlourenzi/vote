package com.vote.contract.v1.scheme.stub;

import com.vote.contract.v1.scheme.model.request.SchemeContractRequest;

public class SchemeContractRequestStub {
    
    public static SchemeContractRequest stubSchemeContractRequest() {
        return SchemeContractRequest.builder()
            .motiveScheme("motive")
            .build();
    }
    
}
