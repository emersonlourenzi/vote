package com.vote.impl.scheme.repository.entity;

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
@Document("scheme")
public class SchemeEntity {
    
    @Id
    private String id;
    private String motiveScheme;
    private LocalDateTime initialDateScheme;
    private LocalDateTime finalDateScheme;
    
}
