package com.vote.commons.exceptions.voting;

import com.vote.commons.exceptions.ExceptionUtils;
import org.springframework.http.HttpStatus;

public class VotingNotFoundException extends ExceptionUtils {

    public VotingNotFoundException() {
        super("Erro ao buscar votações",
            HttpStatus.NOT_FOUND);
    }

}
