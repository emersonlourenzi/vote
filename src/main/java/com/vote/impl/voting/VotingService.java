package com.vote.impl.voting;

import com.vote.commons.exceptions.scheme.SchemeFinalizedException;
import com.vote.commons.exceptions.scheme.SchemeNotFoundException;
import com.vote.impl.kafka.producer.MessageProducer;
import com.vote.impl.scheme.repository.SchemeRepository;
import com.vote.impl.scheme.repository.entity.SchemeEntity;
import com.vote.impl.voting.mapper.VotingEntityToImplResponseMapper;
import com.vote.impl.voting.model.request.VotingImplRequest;
import com.vote.impl.voting.model.response.VotingImplResponse;
import com.vote.impl.voting.repository.VotingRepository;
import com.vote.impl.voting.repository.entity.VotingEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

import static com.vote.impl.voting.mapper.MapBuildSchemeMapper.mapBuildScheme;
import static com.vote.impl.voting.mapper.MapBuildVotingMapper.mapBuildVoting;

@Service
@RequiredArgsConstructor
public class VotingService {

    private final VotingRepository votingRepository;
    private final SchemeRepository schemeRepository;
    private final MessageProducer messageProducer;
    private final VoteService voteService;
    @Value("${spring.kafka.producer.topic-name}")
    private String topicName;

    public Mono<VotingImplResponse> initVoting(VotingImplRequest votingImplRequest) {
        return validateScheme(votingImplRequest)
            .flatMap(this::updateDataVoting)
            .flatMap(votingRepository::save)
            .map(VotingEntityToImplResponseMapper::mapFrom);
    }

    private Mono<VotingImplRequest> validateScheme(VotingImplRequest votingImplRequest) {
        return schemeRepository.findById(votingImplRequest.getIdScheme())
            .switchIfEmpty(Mono.defer(() -> Mono.error(SchemeNotFoundException::new))
            ).flatMap(scheme -> updateDataScheme(scheme, votingImplRequest))
            .flatMap(schemeEntity -> Mono.just(votingImplRequest));
    }

    private Mono<SchemeEntity> updateDataScheme(SchemeEntity schemeEntity, VotingImplRequest votingImplRequest) {
        return mapBuildScheme(schemeEntity, votingImplRequest)
            .flatMap(schemeRepository::save);
    }

    private Mono<VotingEntity> updateDataVoting(VotingImplRequest votingImplRequest) {
        return mapBuildVoting(votingImplRequest);
    }

    public Mono<VotingImplResponse> endsVoting(String id) {
        return validateVotingIsOpen(id)
            .flatMap(this::validatePeriodEnds)
            .flatMap(this::setDataFinal)
            .flatMap(votingRepository::save)
            .flatMap(this::sendMessageProducer)
            .map(VotingEntityToImplResponseMapper::mapFrom);
    }

    private Mono<VotingEntity> validateVotingIsOpen(String id) {
        return votingRepository.findById(id)
            .flatMap(votingEntity -> {
                if (votingEntity.getFinalDateVoting().isBefore(LocalDateTime.now())) {
                    return Mono.error(SchemeFinalizedException::new);
                }
                return Mono.just(votingEntity);
            });
    }

    private Mono<VotingEntity> setDataFinal(VotingEntity votingEntity) {
        votingEntity.setFinalDateVoting(LocalDateTime.now());
        return Mono.just(votingEntity);
    }

    private Mono<VotingEntity> validatePeriodEnds(VotingEntity votingEntity) {
        var dateNow = LocalDateTime.now();
        if (votingEntity.getInitialDateVoting().isBefore(dateNow) &&
            votingEntity.getFinalDateVoting().isAfter(dateNow)) {
            return Mono.just(votingEntity);
        }
        return Mono.error(SchemeFinalizedException::new);
    }

    public Flux<VotingEntity> findAll() {
        return votingRepository.findAll()
            .switchIfEmpty(Mono.defer(() -> Mono.error(SchemeFinalizedException::new)));
    }

    public Mono<VotingEntity> sendMessageProducer(VotingEntity votingEntity) {
        return voteService.resultVoting(votingEntity.getId())
            .flatMap(voteResult -> messageProducer.sendMessage(topicName, votingEntity.getId(), voteResult))
            .thenReturn(votingEntity);
    }

}
