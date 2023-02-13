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
public class VoteContractResponse {
    
    private String id;
    private String idVoting;
    private String cpfAssociate;
    private VoteEnum vote;
    
}
