package com.vote.impl.voting;

import com.vote.impl.association.AssociationService;
import com.vote.impl.association.repository.AssociateRepository;
import com.vote.impl.kafka.producer.MessageProducer;
import com.vote.impl.scheme.repository.SchemeRepository;
import com.vote.impl.voting.repository.VoteRepository;
import com.vote.impl.voting.repository.VotingRepository;
import com.vote.integration.UserInfoIntegration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.vote.impl.voting.stub.AssociateEntityStub.stubAssociateEntity;
import static com.vote.impl.voting.stub.UserInformationStub.stubUserInfoIntegrationResponse;
import static com.vote.impl.voting.stub.VoteEntityStub.stubVoteEntity;
import static com.vote.impl.voting.stub.VoteEntityStub.stubVoteEntityForResult;
import static com.vote.impl.voting.stub.VoteImplRequestStub.stubVoteContractRequest;
import static com.vote.impl.voting.stub.VoteImplResponseStub.stubVoteContractResponse;
import static com.vote.impl.voting.stub.VoteResultImplResponseStub.stubVoteResultImplResponseForResult;
import static com.vote.impl.voting.stub.VotingEntityStub.stubVotingEntity;
import static com.vote.impl.voting.stub.VotingEntityStub.stubVotingEntityForResult;
import static org.mockito.Mockito.when;
import static reactor.test.StepVerifier.create;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
    VotingService.class,
    VoteService.class,
    UserInfoIntegration.class,
    AssociationService.class
})
class VoteImplServiceTest {

    @Autowired
    VoteService voteService;
    @MockBean
    VoteRepository voteRepository;
    @MockBean
    VotingRepository votingRepository;
    @MockBean
    AssociateRepository associateRepository;
    @MockBean
    UserInfoIntegration userInfoIntegration;
    @MockBean
    SchemeRepository schemeRepository;
    @MockBean
    MessageProducer messageProducer;

    @Test
    void testVoteSuccess() {
        var idVoting = "q1w2e3r4t5";
        var cpf = "12345678900";
        var votingEntity = stubVotingEntity();
        var voteRequest = stubVoteContractRequest();
        var responseExpected = stubVoteContractResponse();
        var associateEntity = stubAssociateEntity();
        var ableVote = stubUserInfoIntegrationResponse();
        var voteEntity = stubVoteEntity();

        when(votingRepository.findById(idVoting))
            .thenReturn(Mono.just(votingEntity));
        when(associateRepository.findByCpf(cpf))
            .thenReturn(Mono.just(associateEntity));
        when(userInfoIntegration.validateVoteEnabledAssociate(cpf))
            .thenReturn(Mono.just(ableVote));
        when(voteRepository.findByCpfAssociate(cpf))
            .thenReturn(Mono.empty());
        when(votingRepository.findById(idVoting))
            .thenReturn(Mono.just(votingEntity));
        when(voteRepository.save(voteEntity))
            .thenReturn(Mono.just(voteEntity));

        create(voteService.vote(voteRequest))
            .assertNext(response -> Assertions.assertEquals(responseExpected, response))
            .verifyComplete();
    }

    @Test
    void resultVotingSuccess() {
        var id = "q1w2e3r4t5";
        var responseExpected = stubVoteResultImplResponseForResult();
        var voteEntity = stubVoteEntityForResult();
        var votingEntity = stubVotingEntityForResult();

        when(voteRepository.findByIdVoting(id))
            .thenReturn(Flux.just(voteEntity));
        when(votingRepository.findById(id))
            .thenReturn(Mono.just(votingEntity));

        create(voteService.resultVoting(id))
            .assertNext(response -> Assertions.assertEquals(responseExpected, response))
            .verifyComplete();
    }

}
