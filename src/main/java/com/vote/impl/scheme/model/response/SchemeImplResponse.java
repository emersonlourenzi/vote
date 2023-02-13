package com.vote.impl.scheme.model.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SchemeImplResponse {
    
    private String id;
    private String motiveScheme;
    private LocalDateTime initialDateScheme;
    private LocalDateTime finalDateScheme;
    
}
