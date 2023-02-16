package com.vote.contract.v1.voting;

import com.vote.impl.association.AssociationService;
import com.vote.impl.kafka.producer.MessageProducer;
import com.vote.impl.scheme.repository.SchemeRepository;
import com.vote.impl.voting.VoteService;
import com.vote.impl.voting.VotingService;
import com.vote.impl.voting.repository.VoteRepository;
import com.vote.impl.voting.repository.VotingRepository;
import com.vote.integration.UserInfoIntegration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static com.vote.contract.v1.voting.stub.VotingContractRequestStub.stubVotingContractRequest;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
    VotingRestController.class,
    VotingService.class
})
class VotingContractTest {
    
    @Autowired
    VotingRestController votingRestController;
    @MockBean
    VoteRepository voteRepository;
    @MockBean
    VotingRepository votingRepository;
    @MockBean
    AssociationService associationService;
    @MockBean
    UserInfoIntegration userInfoIntegration;
    @MockBean
    SchemeRepository schemeRepository;
    @MockBean
    MessageProducer messageProducer;
    @MockBean
    VoteService voteService;
    
    
    @Test
    void testInitVotingError() {
        var mockVotingContractRequest = stubVotingContractRequest();
        
        Mockito.when(schemeRepository.findById("q1w2e3r4t5"))
            .thenReturn(Mono.empty());
        
        StepVerifier.create(votingRestController.initVoting(mockVotingContractRequest))
            .expectError()
            .verify();
    }
    
}
