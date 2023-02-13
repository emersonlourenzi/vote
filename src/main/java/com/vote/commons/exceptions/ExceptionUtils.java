package com.vote.commons.exceptions;

import com.vote.impl.association.repository.entity.AssociateEntity;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class ExceptionUtils {
    
    public static Mono<AssociateEntity> getErrorById(String id) {
        return Mono.error(
            new ResponseStatusException(
                HttpStatus.CONFLICT,
                "ID informado: " + id + ", não encontrado em nosso cadastro"));
    }
    
    public static Mono<AssociateEntity> getErrorByCPF(String cpf) {
        return Mono.error(
            new ResponseStatusException(
                HttpStatus.CONFLICT,
                "CPF informado: " + cpf + ", não encontrado em nosso cadastro"));
    }
    
    public static ResponseStatusException buildError(HttpStatus status, String message) {
        return new ResponseStatusException(status, message);
    }
    
}
