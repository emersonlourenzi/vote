package com.vote.impl.association;

import com.vote.impl.association.mapper.AssociateEntityToResponseMapper;
import com.vote.impl.association.mapper.AssociateImplRequestToEntityMapper;
import com.vote.impl.association.model.request.AssociateImplRequest;
import com.vote.impl.association.model.response.AssociateImplResponse;
import com.vote.impl.association.repository.AssociateRepository;
import com.vote.impl.association.repository.entity.AssociateEntity;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.vote.commons.exceptions.ExceptionUtils.getErrorByCPF;
import static com.vote.commons.exceptions.ExceptionUtils.getErrorById;
import static com.vote.impl.association.mapper.MapBuildAssociateEntityMapper.mapBuildAssociateEntityUpdate;

@Service
@AllArgsConstructor
public class AssociationService {
    
    private final AssociateRepository associateRepository;
    
    public Mono<AssociateImplResponse> createAssociate(AssociateImplRequest associateImplRequest) {
        return validateCpfExists(AssociateImplRequestToEntityMapper.mapFrom(associateImplRequest))
            .flatMap(associateRepository::save)
            .map(AssociateEntityToResponseMapper::mapFrom);
    }
    
    public Flux<AssociateImplResponse> findAllAssociates() {
        return associateRepository.findAll()
            .map(AssociateEntityToResponseMapper::mapFrom);
    }
    
    private Mono<AssociateEntity> validateCpfExists(AssociateEntity entity) {
        return associateRepository.findByCpf(entity.getCpf())
            .doOnNext(associateEntity -> {
                throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Já existe um associado cadastrado com o cpf: " + entity.getCpf());
            }).switchIfEmpty(Mono.just(entity));
    }
    
    public Mono<AssociateImplResponse> findAssociateById(String id) {
        return associateRepository.findById(id)
            .switchIfEmpty(Mono.defer(() -> getErrorById(id))
            ).map(AssociateEntityToResponseMapper::mapFrom);
    }
    
    public Mono<AssociateImplResponse> findAssociateByCpf(String cpf) {
        return associateRepository.findByCpf(cpf)
            .switchIfEmpty(Mono.defer(() -> getErrorByCPF(cpf))
            ).map(AssociateEntityToResponseMapper::mapFrom);
    }
    
    public Mono<Void> deleteAssociateById(String id) {
        return associateRepository.findById(id)
            .switchIfEmpty(Mono.defer(() -> getErrorById(id))
            ).flatMap(a -> associateRepository.deleteById(id));
    }
    
    public Mono<Void> deleteAssociateByCpf(String cpf) {
        return associateRepository.findByCpf(cpf)
            .switchIfEmpty(Mono.defer(() -> getErrorByCPF(cpf))
            ).flatMap(a -> associateRepository.deleteByCpf(cpf));
    }
    
    public Mono<AssociateImplResponse> updateAssociate(String id, AssociateImplRequest associateImplRequest) {
        return associateRepository.findById(id)
            .switchIfEmpty(Mono.defer(() -> getErrorById(id)))
            .flatMap(associateEntity -> mapBuildAssociateEntityUpdate(associateEntity, associateImplRequest))
            .flatMap(associateRepository::save)
            .map(AssociateEntityToResponseMapper::mapFrom);
    }
    
}
