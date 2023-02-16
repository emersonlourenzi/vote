package com.vote.contract.v1.association;

import com.vote.impl.association.AssociationService;
import com.vote.impl.association.repository.AssociateRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static com.vote.contract.v1.association.stub.AssociateContractRequestStub.stubAssociateContractRequest;
import static com.vote.contract.v1.association.stub.AssociateContractRequestStub.stubAssociateContractRequestUpdate;
import static com.vote.contract.v1.association.stub.AssociateContractResponseStub.stubAssociateContractResponse;
import static com.vote.contract.v1.association.stub.AssociateContractResponseStub.stubAssociateContractResponseFindAll;
import static com.vote.contract.v1.association.stub.AssociateEntityStub.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
    AssociationRestController.class,
    AssociationService.class
})
class AssociationContractTest {
    
    @Autowired
    AssociationRestController associationRestController;
    @MockBean
    AssociateRepository associateRepository;
    
    @Test
    void whenCallCreateAssociateSuccess() {
        var mockResponseContract = stubAssociateContractResponse();
        var mockRequestContract = stubAssociateContractRequest();
        var mockEntityResponse = stubAssociateEntity();
        var mockEntityRequest = stubAssociateEntityRequest();
        
        Mockito.when(associateRepository.save(mockEntityRequest))
            .thenReturn(Mono.just(mockEntityResponse));
        Mockito.when(associateRepository.findByCpf(mockRequestContract.getCpf()))
            .thenReturn(Mono.empty());
        
        StepVerifier.create(associationRestController.createAssociate(mockRequestContract))
            .assertNext(response -> Assertions.assertEquals(mockResponseContract, response))
            .verifyComplete();
    }
    
    @Test
    void whenCallCreateAssociateError() {
        var mockRequestContract = stubAssociateContractRequest();
        var mockEntityResponse = stubAssociateEntity();
        var mockEntityRequest = stubAssociateEntityRequest();
        
        Mockito.when(associateRepository.save(mockEntityRequest))
            .thenReturn(Mono.just(mockEntityResponse));
        Mockito.when(associateRepository.findByCpf(mockRequestContract.getCpf()))
            .thenReturn(Mono.just(mockEntityResponse));
        
        StepVerifier.create(associationRestController.createAssociate(mockRequestContract))
            .expectError()
            .verify();
    }
    
    @Test
    void testAssociateFindAllSuccess() {
        var mockEntity = stubAssociateEntityResponseFindAll();
        var mockContractResponse = stubAssociateContractResponseFindAll();
        
        Mockito.when(associateRepository.findAll())
            .thenReturn(Flux.just(mockEntity));
        
        StepVerifier.create(associationRestController.findAllAssociates())
            .assertNext(response -> Assertions.assertEquals(mockContractResponse, response))
            .verifyComplete();
    }
    
    @Test
    void whenCallGetAssociateByIdSuccess() {
        var mockResponseContract = stubAssociateContractResponse();
        var stubAssociateEntity = stubAssociateEntity();
        
        Mockito.when(associateRepository.findById("q1w2e3r4t5"))
            .thenReturn(Mono.just(stubAssociateEntity));
        
        StepVerifier.create(associationRestController.findAssociateById("q1w2e3r4t5"))
            .assertNext(response -> Assertions.assertEquals(mockResponseContract, response))
            .verifyComplete();
    }
    
    @Test
    void whenCallGetAssociateByCpfSuccess() {
        var mockResponseContract = stubAssociateContractResponse();
        var stubAssociateEntity = stubAssociateEntity();
        
        Mockito.when(associateRepository.findByCpf("q1w2e3r4t5"))
            .thenReturn(Mono.just(stubAssociateEntity));
        
        StepVerifier.create(associationRestController.findAssociateByCpf("q1w2e3r4t5"))
            .assertNext(response -> Assertions.assertEquals(mockResponseContract, response))
            .verifyComplete();
    }
    
    @Test
    void testDeleteAssociateById() {
        var mockEntity = stubAssociateEntityDeleteById();
        
        Mockito.when(associateRepository.deleteById("q1w2e3r4t5"))
            .thenReturn(Mono.empty());
        Mockito.when(associateRepository.findById("q1w2e3r4t5"))
            .thenReturn(Mono.just(mockEntity));
        
        StepVerifier.create(associationRestController.deleteAssociateById("q1w2e3r4t5"))
            .verifyComplete();
    }
    
    @Test
    void testDeleteAssociateByCPF() {
        var mockEntity = stubAssociateEntityDeleteById();
        
        Mockito.when(associateRepository.deleteByCpf("12345678912"))
            .thenReturn(Mono.empty());
        Mockito.when(associateRepository.findByCpf("12345678912"))
            .thenReturn(Mono.just(mockEntity));
        
        StepVerifier.create(associationRestController.deleteAssociateByCpf("12345678912"))
            .verifyComplete();
    }
    
    @Test
    void testUpdateAssociateError() {
        var mockContractRequest = stubAssociateContractRequestUpdate();
        
        Mockito.when(associateRepository.findById("q1w2e3r4t5"))
            .thenReturn(Mono.empty());
        
        StepVerifier.create(associationRestController.updateAssociate("q1w2e3r4t5", mockContractRequest))
            .expectError()
            .verify();
    }
    
}
