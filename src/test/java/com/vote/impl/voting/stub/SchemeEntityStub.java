package com.vote.impl.voting.stub;

import com.vote.impl.scheme.repository.entity.SchemeEntity;

import java.time.LocalDateTime;

public class SchemeEntityStub {

    public static SchemeEntity stubSchemeEntity() {
        return SchemeEntity.builder()
            .finalDateScheme(LocalDateTime.of(2025, 12, 12, 12, 12).plusMinutes(10))
            .initialDateScheme(LocalDateTime.of(2025, 12, 12, 12, 12))
            .motiveScheme("motivo")
            .build();
    }

}
