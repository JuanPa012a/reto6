package com.devsenior.pablo.reto6.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "El ISBN ya está en uso")
public class IsbnExistException extends RuntimeException{
    public IsbnExistException(String message){
        super(message);
    }
}
