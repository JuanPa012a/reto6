package com.devsenior.pablo.reto6.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "El titulo está en uso")
public class TituloExistException extends RuntimeException{
    public TituloExistException(String message){
        super(message);
    }
}
