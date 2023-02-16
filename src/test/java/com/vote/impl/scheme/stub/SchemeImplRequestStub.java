package com.vote.impl.scheme.stub;

import com.vote.impl.scheme.model.request.SchemeImplRequest;

public class SchemeImplRequestStub {

    public static SchemeImplRequest stubSchemeImplRequestUpdate() {
        return SchemeImplRequest.builder()
            .motiveScheme("motivo")
            .build();
    }

}
