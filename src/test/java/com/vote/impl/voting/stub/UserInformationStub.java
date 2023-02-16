package com.vote.impl.voting.stub;

import com.vote.integration.model.UserInfoIntegrationResponse;

public class UserInformationStub {

    public static UserInfoIntegrationResponse stubUserInfoIntegrationResponse() {
        return UserInfoIntegrationResponse.builder()
            .status("ABLE_TO_VOTE")
            .build();
    }

}
