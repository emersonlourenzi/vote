package com.vote.contract.v1.voting.model.response;

import com.vote.commons.enums.VoteEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VoteResultContractResponse {
    
    private String idVoting;
    private Long quantityNo;
    private Long quantityYes;
    private VoteEnum winningVote;
    
}
