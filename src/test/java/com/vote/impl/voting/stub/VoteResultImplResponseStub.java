package com.vote.impl.voting.stub;

import com.vote.commons.enums.VoteEnum;
import com.vote.impl.voting.model.response.VoteResultImplResponse;

public class VoteResultImplResponseStub {

    public static VoteResultImplResponse stubVoteResultImplResponse() {
        return VoteResultImplResponse.builder()
            .idVoting("q1w2e3r4t5")
            .winningVote(VoteEnum.YES)
            .quantityNo(5L)
            .quantityYes(10L)
            .build();
    }

    public static VoteResultImplResponse stubVoteResultImplResponseForResult() {
        return VoteResultImplResponse.builder()
            .idVoting("q1w2e3r4t5")
            .winningVote(VoteEnum.YES)
            .quantityNo(0L)
            .quantityYes(1L)
            .build();
    }

}
