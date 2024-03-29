package com.vote.impl.voting;

import com.vote.commons.enums.VoteEnum;
import com.vote.commons.exceptions.associate.ErrorFindAssociateException;
import com.vote.commons.exceptions.voting.VotingNotFoundException;
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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.vote.impl.voting.mapper.VoteImplRequestToEntityMapper.mapFrom;

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
//            TODO Servidor heroku fora, implementação da integração concluida
//            .flatMap(this::validateAssociateEnabledVote)
            .flatMap(this::validateAssociateAlreadyVoted)
            .flatMap(this::validateVotingDeadline)
            .flatMap(voteRepository::save)
            .map(VoteEntityToImplResponseMapper::mapFrom);
    }

    private Mono<VoteEntity> validateExistsAssociate(VoteEntity voteEntity) {
        return associationService.findAssociateByCpf(voteEntity.getCpfAssociate())
            .switchIfEmpty(Mono.defer(() -> Mono.error(ErrorFindAssociateException::new))
            ).flatMap(response -> Mono.just(voteEntity));
    }

    private Mono<VoteEntity> validateExistsVoting(VoteEntity voteEntity) {
        return votingRepository.findById(voteEntity.getIdVoting())
            .switchIfEmpty(Mono.defer(() -> Mono.error(VotingNotFoundException::new))
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
                if (UNABLE_VOTE.equals(able.getStatus())) {
                    return Mono.error(ErrorFindAssociateException::new);
                }
                return Mono.just(voteEntity);
            });
    }

    private Mono<VoteEntity> validateVotingDeadline(VoteEntity voteEntity) {
        return votingRepository.findById(voteEntity.getIdVoting())
            .flatMap(votingEntity -> {
                if (ObjectUtils.isEmpty(votingEntity.getFinalDateVoting()) ||
                    votingEntity.getFinalDateVoting().isBefore(LocalDateTime.now())) {
                    return Mono.error(ErrorFindAssociateException::new);
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
            .switchIfEmpty(Mono.defer(() -> Mono.error(ErrorFindAssociateException::new))
            ).flatMap(votingEntity -> {
                if (votingEntity.getFinalDateVoting().isAfter(LocalDateTime.now())) {
                    return Mono.error(ErrorFindAssociateException::new);
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
