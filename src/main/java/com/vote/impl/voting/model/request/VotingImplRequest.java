package com.vote.impl.voting.model.request;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VotingImplRequest {
    
    private String idScheme;
    @Builder.Default
    private LocalDateTime initialDateVoting = LocalDateTime.now();
    private Long votingTime;
    
}
