package com.vote.impl.voting.stub;

import com.vote.impl.voting.model.response.VotingImplResponse;

import java.time.LocalDateTime;

public class VotingImplResponseStub {

    public static VotingImplResponse stubVotingImplResponse() {
        return VotingImplResponse.builder()
            .finalDateVoting(LocalDateTime.of(2025, 12, 12, 12, 12).plusMinutes(10))
            .idScheme("q1w2e3r4t5")
            .initialDateVoting(LocalDateTime.of(2025, 12, 12, 12, 12))
            .build();
    }

    public static VotingImplResponse stubVotingImplResponseForEndsVoting() {
        return VotingImplResponse.builder()
            .finalDateVoting(LocalDateTime.of(2025, 12, 12, 12, 12).plusMinutes(10))
            .idScheme("q1w2e3r4t5")
            .initialDateVoting(LocalDateTime.of(2020, 12, 12, 12, 12))
            .build();
    }

}
