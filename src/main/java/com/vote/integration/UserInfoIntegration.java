package com.vote.integration;

import com.vote.commons.exceptions.ExceptionUtils;
import com.vote.integration.model.UserInfoIntegrationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Configuration
public class UserInfoIntegration {
    
    private final WebClient webClient;
    
    @Autowired
    public UserInfoIntegration(
        @Qualifier("userWebClient")
        WebClient webClient) {
        this.webClient = webClient;
    }
    
    public Mono<UserInfoIntegrationResponse> validateVoteEnabledAssociate(String cpf) {
        log.info("VERIFICANDO ASSOCIADO COM CPF: " + cpf + " SE ESTÁ HABILITADO AO VOTO");
        return webClient.get()
            .uri(uriBuilder -> uriBuilder.path(cpf)
                .build())
            .retrieve()
            .onStatus(HttpStatus::isError, error ->
                error.bodyToMono(String.class)
                    .flatMap(exception -> Mono.error(
                        ExceptionUtils.buildError(
                            error.statusCode(),
                            "Erro na validação do cpf na integração com serviço de informação do usuario")))
            ).bodyToMono(UserInfoIntegrationResponse.class);
    }
    
}
