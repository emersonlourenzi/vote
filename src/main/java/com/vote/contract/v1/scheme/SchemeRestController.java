package com.vote.contract.v1.scheme;

import javax.validation.Valid;

import com.vote.contract.v1.scheme.mapper.response.SchemeContractResponseToImplMapper;
import com.vote.contract.v1.scheme.model.request.SchemeContractRequest;
import com.vote.contract.v1.scheme.model.response.SchemeContractResponse;
import com.vote.contract.v1.scheme.swagger.*;
import com.vote.impl.scheme.SchemeService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.vote.contract.v1.scheme.mapper.request.SchemeImplRequestToContractMapper.mapFrom;

@RestController
@AllArgsConstructor
@Api(tags = "Serviço de pauta")
@RequestMapping(path = "/v1/scheme")
public class SchemeRestController {
    
    private final SchemeService schemeService;
    
    @PostMapping("/create")
    @CreateSchemeSwagger
    public Mono<SchemeContractResponse> createScheme(
        @RequestBody
        @Valid SchemeContractRequest schemeContractRequest) {
        return schemeService.createScheme(mapFrom(schemeContractRequest))
            .map(SchemeContractResponseToImplMapper::mapFrom);
    }
    
    @GetMapping("/find/all")
    @FindAllSchemesSwagger
    public Flux<SchemeContractResponse> findAllSchemes() {
        return schemeService.findAllSchemes()
            .map(SchemeContractResponseToImplMapper::mapFrom);
    }
    
    @GetMapping("/find/id/{id}")
    @FindSchemeByIdSwagger
    public Mono<SchemeContractResponse> findSchemeById(
        @PathVariable
        String id) {
        return schemeService.findSchemeById(id)
            .map(SchemeContractResponseToImplMapper::mapFrom);
    }
    
    @DeleteMapping("/delete/{id}")
    @DeleteSchemeByIdSwagger
    public Mono<Void> deleteSchemeById(
        @PathVariable
        String id) {
        return schemeService.deleteSchemeById(id);
    }
    
    @PutMapping("/update/{id}")
    @UpdateSchemeSwagger
    public Mono<SchemeContractResponse> updateScheme(
        @PathVariable
        String id,
        @RequestBody
        SchemeContractRequest schemeContractRequest) {
        return schemeService.updateScheme(id, mapFrom(schemeContractRequest))
            .map(SchemeContractResponseToImplMapper::mapFrom);
    }
    
}
