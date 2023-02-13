package com.vote.impl.scheme.mapper;

import java.util.Optional;

import com.vote.impl.scheme.model.request.SchemeImplRequest;
import com.vote.impl.scheme.repository.entity.SchemeEntity;
import reactor.core.publisher.Mono;

public interface MappingBuildSchemeEntityMapper {
    
    static Mono<SchemeEntity> mapBuildSchemeEntityUpdate(SchemeEntity schemeEntity,
        SchemeImplRequest schemeImplRequest) {
        return Mono.just(SchemeEntity.builder()
            .id(schemeEntity.getId())
            .motiveScheme(validateMotiveScheme(schemeImplRequest.getMotiveScheme(), schemeEntity.getMotiveScheme()))
            .initialDateScheme(Optional.ofNullable(schemeEntity.getInitialDateScheme())
                .map(date -> schemeEntity.getInitialDateScheme())
                .orElse(null))
            .finalDateScheme(Optional.ofNullable(schemeEntity.getFinalDateScheme())
                .map(date -> schemeEntity.getFinalDateScheme())
                .orElse(null))
            .build());
    }
    
    static String validateMotiveScheme(String motiveRequest, String motiveEntity) {
        return Optional.ofNullable(motiveRequest)
            .map(date -> motiveRequest)
            .orElse(motiveEntity);
    }
    
}
