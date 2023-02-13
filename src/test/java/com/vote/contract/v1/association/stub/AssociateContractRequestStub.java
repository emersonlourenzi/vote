package com.vote.contract.v1.association.stub;

import com.vote.contract.v1.association.model.request.AssociateContractRequest;

public class AssociateContractRequestStub {
    
    public static AssociateContractRequest stubAssociateContractRequest() {
        return AssociateContractRequest.builder()
            .name("name")
            .cpf("92699647012")
            .build();
    }
    
    public static AssociateContractRequest stubAssociateContractRequestUpdate() {
        return AssociateContractRequest.builder()
            .name("name")
            .cpf("92699647012")
            .build();
    }
    
}
