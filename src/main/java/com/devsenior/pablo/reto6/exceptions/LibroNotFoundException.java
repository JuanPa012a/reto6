package com.devsenior.pablo.reto6.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "El libro no fue encontrado")
public class LibroNotFoundException extends RuntimeException{

    public LibroNotFoundException(String message){
        super(message);
    }

}
    
