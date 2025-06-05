package com.devsenior.pablo.reto6.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "El estado no existe")
public class EstadoNotFoundException extends RuntimeException{
    public EstadoNotFoundException(String message){
        super(message);
    }
}
