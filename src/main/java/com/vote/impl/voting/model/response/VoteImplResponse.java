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
public class VoteImplResponse {
    
    private String id;
    private String idVoting;
    private String cpfAssociate;
    private VoteEnum vote;
    
}
