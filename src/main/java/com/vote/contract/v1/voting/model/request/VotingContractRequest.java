package com.vote.contract.v1.voting.model.request;

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
@ApiModel(value = "Objeto votação")
public class VotingContractRequest {
    
    @NotEmpty(message = "Obrigatório informar id da pauta")
    @ApiModelProperty(value = "Id da pauta")
    private String idScheme;
    @Builder.Default
    @ApiModelProperty(value = "Tempo de duração da votação, por padrão 1 minuto")
    private Long votingTime = 1L;
    
}
