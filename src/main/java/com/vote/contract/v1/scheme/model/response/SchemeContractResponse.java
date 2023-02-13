package com.vote.contract.v1.scheme.model.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SchemeContractResponse {
    
    private String id;
    private String motiveScheme;
    private LocalDateTime initialDateScheme;
    private LocalDateTime finalDateScheme;
    
}
