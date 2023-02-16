package com.vote.impl.scheme.stub;

import com.vote.impl.scheme.repository.entity.SchemeEntity;

import java.time.LocalDateTime;

public class SchemeEntityStub {

    public static SchemeEntity stubSchemeEntityDelete() {
        return SchemeEntity.builder()
            .id("q1w2e3r4t5")
            .motiveScheme("motivo")
            .initialDateScheme(LocalDateTime.of(2050, 12, 12, 12, 12))
            .finalDateScheme(LocalDateTime.of(2050, 12, 12, 13, 12))
            .build();
    }

    public static SchemeEntity stubSchemeEntityDeleteError() {
        return SchemeEntity.builder()
            .id("q1w2e3r4t5")
            .motiveScheme("motivo")
            .initialDateScheme(LocalDateTime.of(2020, 12, 12, 12, 12))
            .finalDateScheme(LocalDateTime.of(2020, 12, 12, 13, 00))
            .build();
    }

    public static SchemeEntity stubSchemeEntityUpdate() {
        return SchemeEntity.builder()
            .id("q1w2e3r4t5")
            .motiveScheme("motivo")
            .initialDateScheme(LocalDateTime.of(2050, 12, 12, 12, 12))
            .finalDateScheme(LocalDateTime.of(2050, 12, 13, 12, 12))
            .build();
    }

}
