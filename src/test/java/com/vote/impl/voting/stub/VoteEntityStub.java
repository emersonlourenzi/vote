package com.vote.impl.voting.stub;

import com.vote.commons.enums.VoteEnum;
import com.vote.impl.voting.repository.entity.VoteEntity;

public class VoteEntityStub {

    public static VoteEntity stubVoteEntity() {
        return VoteEntity.builder()
            .idVoting("q1w2e3r4t5")
            .cpfAssociate("12345678900")
            .vote(VoteEnum.YES)
            .build();
    }

    public static VoteEntity stubVoteEntityForResult() {
        return VoteEntity.builder()
            .idVoting("q1w2e3r4t5")
            .cpfAssociate("12345678900")
            .vote(VoteEnum.YES)
            .build();
    }

}
