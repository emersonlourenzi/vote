package com.vote.commons.exceptions.scheme;

import com.vote.commons.exceptions.ExceptionUtils;
import org.springframework.http.HttpStatus;

public class SchemeFinalizedException extends ExceptionUtils {

    public SchemeFinalizedException() {
        super("Pauta já finalizada",
            HttpStatus.CONFLICT);
    }

}
