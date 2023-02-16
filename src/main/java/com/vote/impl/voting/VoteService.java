package com.vote.impl.voting;

import java.time.LocalDateTime;
import java.util.Optional;

import com.vote.commons.enums.VoteEnum;
import com.vote.commons.exceptions.ExceptionUtils;
import com.vote.impl.association.AssociationService;
import com.vote.impl.voting.mapper.MapBuildVotingResultMapper;
import com.vote.impl.voting.mapper.VoteEntityToImplResponseMapper;
import com.vote.impl.voting.model.request.VoteImplRequest;
import com.vote.impl.voting.model.response.VoteImplResponse;
import com.vote.impl.voting.model.response.VoteResultImplResponse;
import com.vote.impl.voting.repository.VoteRepository;
import com.vote.impl.voting.repository.VotingRepository;
import com.vote.impl.voting.repository.entity.VoteEntity;
import com.vote.integration.UserInfoIntegration;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import static com.vote.impl.voting.mapper.VoteImplRequestToEntityMapper.mapFrom;

@Slf4j
@Service
@AllArgsConstructor
public class VoteService {
    
    private final VoteRepository voteRepository;
    private final VotingRepository votingRepository;
    private final AssociationService associationService;
    private final UserInfoIntegration userInfoIntegration;
    private static final String UNABLE_VOTE = "UNABLE_TO_VOTE";
    
    public Mono<VoteImplResponse> vote(VoteImplRequest voteImplRequest) {
        return validateExistsAssociate(mapFrom(voteImplRequest))
            .flatMap(this::validateExistsVoting)
            .flatMap(this::validateAssociateEnabledVote)
            .flatMap(this::validateAssociateAlreadyVoted)
            .flatMap(this::validateVotingDeadline)
            .flatMap(voteRepository::save)
            .map(VoteEntityToImplResponseMapper::mapFrom);
    }
    
    private Mono<VoteEntity> validateExistsAssociate(VoteEntity voteEntity) {
        return associationService.findAssociateByCpf(voteEntity.getCpfAssociate())
            .switchIfEmpty(Mono.defer(() -> Mono.error(ExceptionUtils.buildError(
                HttpStatus.CONFLICT,
                "O CPF: " + voteEntity.getCpfAssociate() + ", não é um associado")))
            ).flatMap(response -> Mono.just(voteEntity));
    }
    
    private Mono<VoteEntity> validateExistsVoting(VoteEntity voteEntity) {
        return votingRepository.findById(voteEntity.getIdVoting())
            .switchIfEmpty(Mono.defer(() -> Mono.error(ExceptionUtils.buildError(
                HttpStatus.CONFLICT,
                "Votação com id: " + voteEntity.getIdVoting() + ", não existe")))
            ).flatMap(response -> Mono.just(voteEntity));
    }
    
    private Mono<VoteEntity> validateAssociateAlreadyVoted(VoteEntity voteEntity) {
        return voteRepository.findByCpfAssociate(voteEntity.getCpfAssociate())
            .doOnNext(entity -> {
                throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Associado com cpf: " + voteEntity.getCpfAssociate() +
                        ", já votou na votação: " + voteEntity.getIdVoting());
            }).switchIfEmpty(Mono.just(voteEntity));
    }
    
    private Mono<VoteEntity> validateAssociateEnabledVote(VoteEntity voteEntity) {
        return userInfoIntegration.validateVoteEnabledAssociate(voteEntity.getCpfAssociate())
            .flatMap(able -> {
                log.info(able.getStatus());
                if (UNABLE_VOTE.equals(able.getStatus())) {
                    return Mono.error(ExceptionUtils.buildError(
                        HttpStatus.CONFLICT,
                        "Associado não está habilitado ao voto"
                    ));
                }
                return Mono.just(voteEntity);
            });
    }
    
    private Mono<VoteEntity> validateVotingDeadline(VoteEntity voteEntity) {
        return votingRepository.findById(voteEntity.getIdVoting())
            .flatMap(votingEntity -> {
                if (ObjectUtils.isEmpty(votingEntity.getFinalDateVoting()) ||
                    votingEntity.getFinalDateVoting().isBefore(LocalDateTime.now())) {
                    return Mono.error(ExceptionUtils.buildError(
                        HttpStatus.CONFLICT,
                        "Prazo de votação expirado"
                    ));
                }
                return Mono.just(voteEntity);
            });
    }
    
    public Mono<VoteResultImplResponse> resultVoting(String id) {
        return voteRepository.findByIdVoting(id)
            .collectList()
            .flatMap(MapBuildVotingResultMapper::buildResultVoting)
            .flatMap(this::validateIfTheVotingResultIsAvailable)
            .flatMap(this::validateWinning);
    }
    
    private Mono<VoteResultImplResponse> validateIfTheVotingResultIsAvailable(
        VoteResultImplResponse voteResultImplResponse) {
        return votingRepository.findById(voteResultImplResponse.getIdVoting())
            .switchIfEmpty(Mono.defer(() -> Mono.error(ExceptionUtils.buildError(
                HttpStatus.CONFLICT,
                "Id da votação informado não encontrado")))
            ).flatMap(votingEntity -> {
                if (votingEntity.getFinalDateVoting().isAfter(LocalDateTime.now())) {
                    return Mono.error(ExceptionUtils.buildError(
                        HttpStatus.CONFLICT,
                        "Resultado da votação indisponivel, a votação está em aberto ainda"));
                }
                return Mono.just(voteResultImplResponse);
            });
    }
    
    private Mono<VoteResultImplResponse> validateWinning(VoteResultImplResponse voteResultImplResponse) {
        voteResultImplResponse.setWinningVote(winning(
                Optional.ofNullable(voteResultImplResponse.getQuantityYes())
                    .map(qtd -> voteResultImplResponse.getQuantityYes())
                    .orElse(0L),
                Optional.ofNullable(voteResultImplResponse.getQuantityNo())
                    .map(qtd -> voteResultImplResponse.getQuantityNo())
                    .orElse(0L)
            )
        );
        return Mono.just(voteResultImplResponse);
    }
    
    private VoteEnum winning(Long yes, Long no) {
        if (yes > no) {
            return VoteEnum.YES;
        } else if (no > yes) {
            return VoteEnum.NO;
        } else {
            return null;
        }
    }
    
}
