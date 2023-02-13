package com.vote.impl.association.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssociateImplResponse {
    
    private String id;
    private String name;
    private String cpf;
    
}
