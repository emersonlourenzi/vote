package com.vote.impl.voting;

import com.vote.impl.kafka.producer.MessageProducer;
import com.vote.impl.scheme.repository.SchemeRepository;
import com.vote.impl.voting.repository.VotingRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static com.vote.impl.voting.stub.SchemeEntityStub.stubSchemeEntity;
import static com.vote.impl.voting.stub.VoteResultImplResponseStub.stubVoteResultImplResponse;
import static com.vote.impl.voting.stub.VotingEntityStub.stubVotingEntityForEndsVoting;
import static com.vote.impl.voting.stub.VotingEntityStub.stubVotingEntityForInitVoting;
import static com.vote.impl.voting.stub.VotingImplRequestStub.stubVotingImplRequest;
import static com.vote.impl.voting.stub.VotingImplResponseStub.stubVotingImplResponse;
import static com.vote.impl.voting.stub.VotingImplResponseStub.stubVotingImplResponseForEndsVoting;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VotingImplServiceTest {

    @InjectMocks
    VotingService votingService;
    @Mock
    SchemeRepository schemeRepository;
    @Mock
    VotingRepository votingRepository;
    @Mock
    VoteService voteService;
    @Mock
    MessageProducer messageProducer;

    @Test
    void initVotingTestSuccess() {
        var id = "q1w2e3r4t5";
        var votingRequest = stubVotingImplRequest();
        var responseExpected = stubVotingImplResponse();
        var schemeEntity = stubSchemeEntity();
        var votingEntity = stubVotingEntityForInitVoting();

        when(schemeRepository.findById(id))
            .thenReturn(Mono.just(schemeEntity));
        when(votingRepository.save(votingEntity))
            .thenReturn(Mono.just(votingEntity));
        when(schemeRepository.save(schemeEntity))
            .thenReturn(Mono.just(schemeEntity));

        StepVerifier.create(votingService.initVoting(votingRequest))
            .assertNext(response -> Assertions.assertEquals(responseExpected, response))
            .verifyComplete();
    }

    @Test
    void endsVotingSuccess() {
        var id = "q1w2e3r4t5";
        var responseExpected = stubVotingImplResponseForEndsVoting();
        var responseActual = stubVotingImplResponseForEndsVoting();
        var votingEntity = stubVotingEntityForEndsVoting();
        var resultVotingResponse = stubVoteResultImplResponse();

        when(votingRepository.findById(id))
            .thenReturn(Mono.just(votingEntity));
        when(votingRepository.save(votingEntity))
            .thenReturn(Mono.just(votingEntity));
        when(voteService.resultVoting(id))
            .thenReturn(Mono.just(resultVotingResponse));
        when(messageProducer.sendMessage(null, id, resultVotingResponse))
            .thenReturn(Mono.empty());

        StepVerifier.create(votingService.endsVoting(id))
            .assertNext(response -> Assertions.assertEquals(responseExpected, responseActual))
            .verifyComplete();
    }

}
