package com.devsenior.pablo.reto6.models.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "ENUM para manejar el tipo de estado del libro")
public enum Estado {
    @Schema(description = 
    "Este enunciado es para indicar que el libro está listo para usarse")
    DISPONIBLE, 
    @Schema(description 
    = "Este enuncuado es para indicar que el libro lo tiene reservado otro usuario")
    RESERVADO, 
    @Schema(description = 
    "Este enunciado es para indicar que el libro no se puede usar, lo tiene en posesion otro usuario")
    OCUPADO, 
    @Schema(description = 
    "Este enunciado es para indicar que el libro ya no hay más libros en el momento")
    AGOTADO 
}
