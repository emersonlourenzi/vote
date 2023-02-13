package com.vote.contract.v1.scheme.model.request;

import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Objeto pauta")
public class SchemeContractRequest {
    
    @ApiModelProperty(value = "Motivo da pauta")
    @NotEmpty(message = "O motivo da pauta deve ser informado")
    private String motiveScheme;
    
}
