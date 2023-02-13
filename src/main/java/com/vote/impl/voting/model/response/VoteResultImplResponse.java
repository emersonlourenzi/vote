package com.vote.impl.voting.model.response;

import com.vote.commons.enums.VoteEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VoteResultImplResponse {
    
    private String idVoting;
    private Long quantityNo;
    private Long quantityYes;
    private VoteEnum winningVote;
    
}
