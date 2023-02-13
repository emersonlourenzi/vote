package com.vote.contract.v1.association;

import javax.validation.Valid;

import com.vote.contract.v1.association.mapper.response.AssociateImplResponseToContractMapper;
import com.vote.contract.v1.association.model.request.AssociateContractRequest;
import com.vote.contract.v1.association.model.response.AssociateContractResponse;
import com.vote.contract.v1.association.swagger.*;
import com.vote.impl.association.AssociationService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.vote.contract.v1.association.mapper.request.AssociateContractResponseToImplMapper.mapFrom;

@RestController
@AllArgsConstructor
@Api(tags = "Serviço do associado")
@RequestMapping(path = "/v1/association")
public class AssociationRestController {
    
    private final AssociationService associationService;
    
    @PostMapping("/create")
    @CreateAssociateSwagger
    public Mono<AssociateContractResponse> createAssociate(
        @RequestBody
        @Valid AssociateContractRequest associateContractRequest) {
        return associationService.createAssociate(mapFrom(associateContractRequest))
            .map(AssociateImplResponseToContractMapper::mapFrom);
    }
    
    @GetMapping("/find/all")
    @FindAllAssociatesSwagger
    public Flux<AssociateContractResponse> findAllAssociates() {
        return associationService.findAllAssociates()
            .map(AssociateImplResponseToContractMapper::mapFrom);
    }
    
    @GetMapping("/find/id/{id}")
    @FindAssociateByIdSwagger
    public Mono<AssociateContractResponse> findAssociateById(
        @PathVariable
        String id) {
        return associationService.findAssociateById(id)
            .map(AssociateImplResponseToContractMapper::mapFrom);
    }
    
    @GetMapping("/find/cpf/{cpf}")
    @FindAssociateByCpfSwagger
    public Mono<AssociateContractResponse> findAssociateByCpf(
        @PathVariable
        String cpf) {
        return associationService.findAssociateByCpf(cpf)
            .map(AssociateImplResponseToContractMapper::mapFrom);
    }
    
    @DeleteMapping("/delete/id/{id}")
    @DeleteAssociateById
    public Mono<Void> deleteAssociateById(
        @PathVariable
        String id) {
        return associationService.deleteAssociateById(id);
    }
    
    @DeleteMapping("/delete/cpf/{id}")
    @DeleteAssociateByCpf
    public Mono<Void> deleteAssociateByCpf(
        @PathVariable
        String id) {
        return associationService.deleteAssociateByCpf(id);
    }
    
    @PutMapping("/update/{id}")
    @UpdateAssociateSwagger
    public Mono<AssociateContractResponse> updateAssociate(
        @PathVariable
        String id,
        @RequestBody
        @Valid AssociateContractRequest associateContractRequest) {
        return associationService.updateAssociate(id, mapFrom(associateContractRequest))
            .map(AssociateImplResponseToContractMapper::mapFrom);
    }
    
}
