package com.vote.impl.voting.stub;

import com.vote.impl.voting.repository.entity.VotingEntity;

import java.time.LocalDateTime;

public class VotingEntityStub {

    public static VotingEntity stubVotingEntity() {
        return VotingEntity.builder()
            .id("q1w2e3r4t5")
            .idScheme("q1w2e3r4t5")
            .initialDateVoting(LocalDateTime.of(2020, 12, 12, 12, 12))
            .finalDateVoting(LocalDateTime.of(2025, 12, 13, 12, 12))
            .build();
    }

    public static VotingEntity stubVotingEntityForInitVoting() {
        return VotingEntity.builder()
            .idScheme("q1w2e3r4t5")
            .initialDateVoting(LocalDateTime.of(2025, 12, 12, 12, 12))
            .finalDateVoting(LocalDateTime.of(2025, 12, 12, 12, 12).plusMinutes(10))
            .build();
    }

    public static VotingEntity stubVotingEntityForEndsVoting() {
        return VotingEntity.builder()
            .id("q1w2e3r4t5")
            .idScheme("q1w2e3r4t5")
            .initialDateVoting(LocalDateTime.of(2020, 12, 12, 12, 12))
            .finalDateVoting(LocalDateTime.of(2025, 12, 12, 12, 12).plusMinutes(10))
            .build();
    }

    public static VotingEntity stubVotingEntityForResult() {
        return VotingEntity.builder()
            .id("q1w2e3r4t5")
            .idScheme("q1w2e3r4t5")
            .initialDateVoting(LocalDateTime.of(2020, 12, 12, 12, 12))
            .finalDateVoting(LocalDateTime.of(2020, 12, 12, 13, 12).plusMinutes(10))
            .build();
    }

}
