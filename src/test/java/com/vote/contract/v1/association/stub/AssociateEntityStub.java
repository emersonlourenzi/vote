package com.vote.contract.v1.association.stub;

import com.vote.impl.association.repository.entity.AssociateEntity;

public class AssociateEntityStub {
    
    public static AssociateEntity stubAssociateEntity() {
        return AssociateEntity.builder()
            .id("q1w2e3r4t5")
            .name("name")
            .cpf("92699647012")
            .build();
    }
    
    public static AssociateEntity stubAssociateEntityRequest() {
        return AssociateEntity.builder()
            .name("name")
            .cpf("92699647012")
            .build();
    }
    
    public static AssociateEntity stubAssociateEntityDeleteById() {
        return AssociateEntity.builder()
            .id("q1w2e3r4t5")
            .name("name")
            .cpf("92699647012")
            .build();
    }
    
    public static AssociateEntity stubAssociateEntityResponseFindAll() {
        return AssociateEntity.builder()
            .id("q1w2e3r4t5")
            .name("name")
            .cpf("92699647012")
            .build();
    }
    
}
