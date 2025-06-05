package com.devsenior.pablo.reto6.models.entities;

import com.devsenior.pablo.reto6.models.dto.Estado;

import lombok.Data;

@Data
public class Libro {

    private Long id;
    private String titulo;
    private String autor;
    private String isbn;
    private Integer añoPublicado;
    private String genero;
    private Estado estado;
}
