package com.vote.impl.scheme;

import com.vote.impl.scheme.repository.SchemeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import static com.vote.impl.scheme.stub.SchemeEntityStub.*;
import static com.vote.impl.scheme.stub.SchemeImplRequestStub.stubSchemeImplRequestUpdate;
import static com.vote.impl.scheme.stub.SchemeImplResponseStub.stubSchemeImplResponseUpdate;
import static org.mockito.Mockito.when;
import static reactor.test.StepVerifier.create;

@ExtendWith(MockitoExtension.class)
class SchemeImplServiceTest {

    @InjectMocks
    SchemeService schemeService;
    @Mock
    SchemeRepository schemeRepository;

    @Test
    void deleteSchemeByIdSuccess() {
        var id = "q1w2e3r4t5";
        var schemeEntity = stubSchemeEntityDelete();

        when(schemeRepository.findById(id))
            .thenReturn(Mono.just(schemeEntity));
        when(schemeRepository.delete(schemeEntity))
            .thenReturn(Mono.empty());

        create(schemeService.deleteSchemeById(id))
            .verifyComplete();
    }

    @Test
    void deleteSchemeByIdError() {
        var id = "q1w2e3r4t5";
        var schemeEntity = stubSchemeEntityDeleteError();

        when(schemeRepository.findById(id))
            .thenReturn(Mono.just(schemeEntity));

        create(schemeService.deleteSchemeById(id))
            .expectError()
            .verify();
    }

    @Test
    void updateSchemeSuccess() {
        var id = "q1w2ee3r4t5";
        var schemeRequest = stubSchemeImplRequestUpdate();
        var responseExpected = stubSchemeImplResponseUpdate();
        var schemeEntity = stubSchemeEntityUpdate();

        when(schemeRepository.findById(id))
            .thenReturn(Mono.just(schemeEntity));
        when(schemeRepository.save(schemeEntity))
            .thenReturn(Mono.just(schemeEntity));

        create(schemeService.updateScheme(id, schemeRequest))
            .assertNext(response -> Assertions.assertEquals(responseExpected, response))
            .verifyComplete();
    }

}
