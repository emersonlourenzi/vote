package com.vote.impl.scheme.stub;

import com.vote.impl.scheme.model.response.SchemeImplResponse;

import java.time.LocalDateTime;

public class SchemeImplResponseStub {

    public static SchemeImplResponse stubSchemeImplResponseUpdate() {
        return SchemeImplResponse.builder()
            .motiveScheme("motivo")
            .id("q1w2e3r4t5")
            .initialDateScheme(LocalDateTime.of(2050, 12, 12, 12, 12))
            .finalDateScheme(LocalDateTime.of(2050, 12, 13, 12, 12))
            .build();
    }

}
