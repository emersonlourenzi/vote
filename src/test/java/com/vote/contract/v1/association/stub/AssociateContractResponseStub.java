package com.vote.contract.v1.association.stub;

import com.vote.contract.v1.association.model.response.AssociateContractResponse;

public class AssociateContractResponseStub {
    
    public static AssociateContractResponse stubAssociateContractResponse() {
        return AssociateContractResponse.builder()
            .id("q1w2e3r4t5")
            .name("name")
            .cpf("92699647012")
            .build();
    }
    
    public static AssociateContractResponse stubAssociateContractResponseFindAll() {
        return AssociateContractResponse.builder()
            .id("q1w2e3r4t5")
            .name("name")
            .cpf("92699647012")
            .build();
    }
    
}
