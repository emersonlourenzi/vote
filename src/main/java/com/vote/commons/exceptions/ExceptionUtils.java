package com.vote.commons.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
public abstract class ExceptionUtils extends RuntimeException {

    private final HttpStatus httpStatus;
    private final String erro;

    public ExceptionUtils(String erro, HttpStatus httpStatus) {
        super(erro);
        this.erro = erro;
        this.httpStatus = httpStatus;
        throw new ResponseStatusException(this.getHttpStatus(), this.getErro());
    }

}
