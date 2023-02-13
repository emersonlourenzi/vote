package com.vote.contract.v1.scheme.swagger;

import com.vote.contract.v1.association.model.response.AssociateContractResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiOperation(value = "Atualiza pauta", response = AssociateContractResponse.class,
    responseContainer = "Mono", produces = "application/json")
@ApiResponses({
    @ApiResponse(code = 200, message = "Pauta atualizada com sucesso"),
    @ApiResponse(code = 400, message = "Bad request"),
    @ApiResponse(code = 404, message = "Pauta não encontrada"),
    @ApiResponse(code = 409, message = "Conflito na atualização da pauta"),
    @ApiResponse(code = 500, message = "Internal error", response = Error.class)
})
public @interface UpdateSchemeSwagger {
}
