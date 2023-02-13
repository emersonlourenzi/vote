package com.vote.contract.v1.association.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssociateContractResponse {
    
    private String id;
    private String name;
    private String cpf;
    
}
