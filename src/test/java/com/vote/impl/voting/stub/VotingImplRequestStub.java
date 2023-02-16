package com.vote.impl.voting.stub;

import com.vote.impl.voting.model.request.VotingImplRequest;

import java.time.LocalDateTime;

public class VotingImplRequestStub {

    public static VotingImplRequest stubVotingImplRequest() {
        return VotingImplRequest.builder()
            .votingTime(10L)
            .idScheme("q1w2e3r4t5")
            .initialDateVoting(LocalDateTime.of(2025, 12, 12, 12, 12))
            .build();
    }

}
