package com.vote.contract.v1.scheme;

import com.vote.impl.scheme.SchemeService;
import com.vote.impl.scheme.repository.SchemeRepository;
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

import static com.vote.contract.v1.scheme.stub.SchemeContractRequestStub.stubSchemeContractRequest;
import static com.vote.contract.v1.scheme.stub.SchemeContractResponseStub.*;
import static com.vote.contract.v1.scheme.stub.SchemeEntityStub.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
    SchemeRestController.class,
    SchemeService.class
})
class SchemeContractTest {

    @Autowired
    SchemeRestController schemeRestController;
    @MockBean
    SchemeRepository schemeRepository;

    @Test
    void testCreateSchemeSuccess() {
        var mockContractResponse = stubSchemeContractResponse();
        var mockContractRequest = stubSchemeContractRequest();
        var mockEntity = stubSchemeEntity();

        Mockito.when(schemeRepository.save(mockEntity))
            .thenReturn(Mono.just(mockEntity));

        StepVerifier.create(schemeRestController.createScheme(mockContractRequest))
            .assertNext(response -> Assertions.assertEquals(mockContractResponse, response))
            .verifyComplete();
    }

    @Test
    void testFindAllSchemesSuccess() {
        var mockContractResponse = stubSchemeContractResponseFindAll();
        var mockEntity = stubSchemeEntityFindAll();

        Mockito.when(schemeRepository.findAll())
            .thenReturn(Flux.just(mockEntity));

        StepVerifier.create(schemeRestController.findAllSchemes())
            .assertNext(response -> Assertions.assertEquals(mockContractResponse, response))
            .verifyComplete();
    }

    @Test
    void testFindSchemeByIdSuccess() {
        var mockContractResponse = stubSchemeContractResponseFindById();
        var mockEntity = stubSchemeEntityFindById();

        Mockito.when(schemeRepository.findById("q1w2e3r4t5"))
            .thenReturn(Mono.just((mockEntity)));

        StepVerifier.create(schemeRestController.findSchemeById("q1w2e3r4t5"))
            .assertNext(response -> Assertions.assertEquals(mockContractResponse, response))
            .verifyComplete();
    }

    @Test
    void testFindSchemeByIdError() {
        Mockito.when(schemeRepository.findById("q1w2e3r4t5"))
            .thenReturn(Mono.empty());

        StepVerifier.create(schemeRestController.findSchemeById("q1w2e3r4t5"))
            .expectError()
            .verify();
    }

}
