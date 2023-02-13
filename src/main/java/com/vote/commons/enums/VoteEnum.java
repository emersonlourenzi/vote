package com.vote.commons.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum VoteEnum {
    
    @JsonProperty("Não")
    NO("Voto não"),
    @JsonProperty("Sim")
    YES("Voto sim");
    
    private final String vote;
    
}
