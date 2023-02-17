package com.vote.contract.v1.scheme.stub;

import com.vote.impl.scheme.repository.entity.SchemeEntity;

import java.time.LocalDateTime;

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

    public static SchemeEntity stubSchemeEntityForDelete() {
        return SchemeEntity.builder()
            .motiveScheme("motive")
            .id("q1w2e3r4t5")
            .initialDateScheme(LocalDateTime.of(2030, 12, 12, 12, 12))
            .finalDateScheme(LocalDateTime.of(2030, 12, 12, 12, 12))
            .build();
    }

}
