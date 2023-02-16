package com.vote.impl.voting.stub;

import com.vote.commons.enums.VoteEnum;
import com.vote.contract.v1.voting.model.request.VoteContractRequest;

public class VoteRequestStub {

    public static VoteContractRequest stubVoteRequest() {
        return VoteContractRequest.builder()
            .idVoting("q1w2e3r4t5")
            .cpfAssociate("12345678900")
            .vote(VoteEnum.YES)
            .build();
    }

}
