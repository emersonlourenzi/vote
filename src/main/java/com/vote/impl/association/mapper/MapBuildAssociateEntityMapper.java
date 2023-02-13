package com.vote.impl.association.mapper;

import java.util.Optional;

import com.vote.impl.association.model.request.AssociateImplRequest;
import com.vote.impl.association.repository.entity.AssociateEntity;
import reactor.core.publisher.Mono;

public interface MapBuildAssociateEntityMapper {
    
    static Mono<AssociateEntity> mapBuildAssociateEntityUpdate(AssociateEntity associateEntity,
        AssociateImplRequest associateImplRequest) {
        return Mono.just(AssociateEntity.builder()
            .id(associateEntity.getId())
            .name(validateReturnDataSave(associateEntity.getName(), associateImplRequest.getName()))
            .cpf(validateReturnDataSave(associateEntity.getCpf(), associateImplRequest.getCpf()))
            .build());
    }
    
    static String validateReturnDataSave(String entity, String request) {
        return Optional.ofNullable(request)
            .map(name -> request)
            .orElse(entity);
    }
    
}
