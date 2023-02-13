package com.vote.contract.v1.association.swagger;

import com.vote.contract.v1.association.model.response.AssociateContractResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiOperation(value = "Busca associado por ID", response = AssociateContractResponse.class,
    responseContainer = "Mono", produces = "application/json")
@ApiResponses({
    @ApiResponse(code = 200, message = "Associado encontrado com sucesso"),
    @ApiResponse(code = 400, message = "Bad request"),
    @ApiResponse(code = 404, message = "Associado não encontrado"),
    @ApiResponse(code = 409, message = "Conflito na busca do associado"),
    @ApiResponse(code = 500, message = "Internal error", response = Error.class)
})
public @interface FindAssociateByIdSwagger {
}
