package com.vote.impl.voting.repository.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(value = "voting")
public class VotingEntity {
    
    @Id
    private String id;
    private String idScheme;
    private LocalDateTime initialDateVoting;
    private LocalDateTime finalDateVoting;
    
}
