package com.vote.contract.v1.association.model.request;

import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Objeto associado")
public class AssociateContractRequest {
    
    @ApiModelProperty(value = "Nome do associado")
    @NotEmpty(message = "Nome do associado não pode ser vazio ou nulo")
    private String name;
    @CPF(message = "Valor inserido deve um CPF válido")
    @NotEmpty(message = "CPF não pode ser vazio ou nulo")
    @ApiModelProperty(value = "CPF do associado")
    private String cpf;
    
}
