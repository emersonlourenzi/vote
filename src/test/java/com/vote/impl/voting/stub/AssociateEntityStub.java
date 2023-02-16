package com.vote.impl.voting.stub;

import com.vote.impl.association.repository.entity.AssociateEntity;

public class AssociateEntityStub {

    public static AssociateEntity stubAssociateEntity() {
        return AssociateEntity.builder()
            .cpf("12345678900")
            .id("q1w2e3r4t5")
            .name("Poucas Trancas")
            .build();
    }

}
