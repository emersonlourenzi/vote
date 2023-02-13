package com.vote.contract.v1.voting.stub;

import com.vote.contract.v1.voting.model.request.VotingContractRequest;

public class VotingContractRequestStub {
    
    public static VotingContractRequest stubVotingContractRequest() {
        return VotingContractRequest.builder()
            .idScheme("q1w2e3r4t5")
            .build();
    }
    
}
