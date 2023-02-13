package com.vote.contract.v1.voting.model.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.vote.commons.enums.VoteEnum;
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
@ApiModel(value = "Objeto voto")
public class VoteContractRequest {
    
    @NotEmpty(message = "Obrigatório informar id da votação")
    @ApiModelProperty(value = "Id da votação")
    private String idVoting;
    @NotEmpty(message = "Obrigatório informar cpf do associado")
    @ApiModelProperty(value = "CPF do associado")
    private String cpfAssociate;
    @NotNull(message = "Obrigatório informar o voto")
    @ApiModelProperty(value = "Voto = Sim ou Não")
    private VoteEnum vote;
    
}
