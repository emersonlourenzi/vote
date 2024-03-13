package com.vote.commons.exceptions.associate;

import com.vote.commons.exceptions.ExceptionUtils;
import org.springframework.http.HttpStatus;

public class ErrorValidateCpfException extends ExceptionUtils {

    public ErrorValidateCpfException(HttpStatus status) {
        super("Erro na validação do cpf na integração com serviço de informação do usuario",
            status);
    }

}
