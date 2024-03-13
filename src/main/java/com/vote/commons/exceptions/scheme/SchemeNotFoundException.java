package com.vote.commons.exceptions.scheme;

import com.vote.commons.exceptions.ExceptionUtils;
import org.springframework.http.HttpStatus;

public class SchemeNotFoundException extends ExceptionUtils {

    public SchemeNotFoundException() {
        super("Pauta não localizada",
            HttpStatus.CONFLICT);
    }

}
