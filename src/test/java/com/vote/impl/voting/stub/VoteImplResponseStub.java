package com.vote.impl.voting.stub;

import com.vote.commons.enums.VoteEnum;
import com.vote.impl.voting.model.response.VoteImplResponse;

public class VoteImplResponseStub {

    public static VoteImplResponse stubVoteContractResponse() {
        return VoteImplResponse.builder()
            .idVoting("q1w2e3r4t5")
            .cpfAssociate("12345678900")
            .vote(VoteEnum.YES)
            .build();
    }

}
