package com.vote.commons.exceptions.associate;

import com.vote.commons.exceptions.ExceptionUtils;
import org.springframework.http.HttpStatus;

public class ErrorFindAssociateException extends ExceptionUtils {

    public ErrorFindAssociateException() {
        super("Erro ao localizador o associado",
            HttpStatus.CONFLICT);
    }

}
