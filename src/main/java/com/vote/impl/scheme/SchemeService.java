package com.vote.impl.scheme;

import com.vote.commons.exceptions.scheme.SchemeFinalizedException;
import com.vote.commons.exceptions.scheme.SchemeNotFoundException;
import com.vote.impl.scheme.mapper.SchemeEntityToResponseMapper;
import com.vote.impl.scheme.model.request.SchemeImplRequest;
import com.vote.impl.scheme.model.response.SchemeImplResponse;
import com.vote.impl.scheme.repository.SchemeRepository;
import com.vote.impl.scheme.repository.entity.SchemeEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

import static com.vote.impl.scheme.mapper.MappingBuildSchemeEntityMapper.mapBuildSchemeEntityUpdate;
import static com.vote.impl.scheme.mapper.SchemeImplRequestToEntityMapper.mapFrom;

@Service
@AllArgsConstructor
public class SchemeService {

    private final SchemeRepository schemeRepository;

    public Mono<SchemeImplResponse> createScheme(SchemeImplRequest schemeImplRequest) {
        return Mono.just(mapFrom(schemeImplRequest))
            .flatMap(schemeRepository::save)
            .map(SchemeEntityToResponseMapper::mapFrom);
    }

    public Flux<SchemeImplResponse> findAllSchemes() {
        return schemeRepository.findAll()
            .map(SchemeEntityToResponseMapper::mapFrom);
    }

    public Mono<Void> deleteSchemeById(String id) {
        return validateExistsScheme(id)
            .flatMap(this::validateAlreadyVotedSchemeOrDatePast)
            .flatMap(schemeRepository::delete);
    }

    private Mono<SchemeEntity> validateExistsScheme(String id) {
        return schemeRepository.findById(id)
            .switchIfEmpty(Mono.defer(() -> Mono.error(SchemeNotFoundException::new))
            ).flatMap(Mono::just);
    }

    private Mono<SchemeEntity> validateAlreadyVotedSchemeOrDatePast(SchemeEntity schemeEntity) {
        if (!(ObjectUtils.isEmpty(schemeEntity.getFinalDateScheme())) &&
            schemeEntity.getFinalDateScheme().isBefore(LocalDateTime.now())) {
            throw new SchemeFinalizedException();
        }
        return Mono.just(schemeEntity);
    }

    public Mono<SchemeImplResponse> findSchemeById(String id) {
        return validateExistsScheme(id)
            .map(SchemeEntityToResponseMapper::mapFrom);
    }

    public Mono<SchemeImplResponse> updateScheme(String id, SchemeImplRequest schemeImplRequest) {
        return validateExistsScheme(id)
            .flatMap(this::validateAlreadyVotedSchemeOrDatePast)
            .flatMap(schemeEntity -> mapBuildSchemeEntityUpdate(schemeEntity, schemeImplRequest))
            .flatMap(schemeRepository::save)
            .map(SchemeEntityToResponseMapper::mapFrom);
    }

}
