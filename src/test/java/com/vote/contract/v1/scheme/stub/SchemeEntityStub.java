package com.vote.contract.v1.scheme.stub;

import com.vote.impl.scheme.repository.entity.SchemeEntity;

public class SchemeEntityStub {
    
    public static SchemeEntity stubSchemeEntity() {
        return SchemeEntity.builder()
            .motiveScheme("motive")
            .build();
    }
    
    public static SchemeEntity stubSchemeEntityFindAll() {
        return SchemeEntity.builder()
            .motiveScheme("motive")
            .build();
    }
    
    public static SchemeEntity stubSchemeEntityFindById() {
        return SchemeEntity.builder()
            .motiveScheme("motive")
            .build();
    }
    
}
