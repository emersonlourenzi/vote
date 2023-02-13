package com.vote.impl.voting.model.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VotingImplResponse {
    
    private String id;
    private String idScheme;
    private LocalDateTime initialDateVoting;
    private LocalDateTime finalDateVoting;
    
}
