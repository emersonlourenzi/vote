package com.vote.impl.voting.repository.entity;

import com.vote.commons.enums.VoteEnum;
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
@Document(value = "vote")
public class VoteEntity {
    
    @Id
    private String id;
    private String idVoting;
    private String cpfAssociate;
    private VoteEnum vote;
    
}
