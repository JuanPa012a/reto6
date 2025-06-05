package com.devsenior.pablo.reto6.models.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "Modelo DTO que representa a un libro en la aplicación")
public class LibroDTO {
    @Schema(description = "Titulo o encabezado del libro", example = "El principito")
    @NotBlank(message = "El campo 'titulo' es obligatorio.")
    private String titulo;

    @Schema(description = "Persona quien escribio la obra literaria", example = "Juan Gabriel Garcia Marquez")
    @NotBlank(message = "El campo 'autor' es obligatorio.")
    private String autor;

    @Schema(description = "Codigo identificador del libro", example = "978-3-16-148410-0")
    @NotBlank(message = "El campo 'isbn' es obligatorio.")
    private String isbn;

    @Schema(description = "El año que fue publicado el libro", example = "2025")
    @NotNull(message = "El campo 'añuPublicado' es obligatorio.")
    @Min(value = 1000, message = "El año debe tener 4 dígitos")
    @Max(value = 9999, message = "El año debe tener 4 dígitos")
    private Integer añoPublicado;

    @Schema(description = "Genero tambien se referencia como 'categoria'", example = "Romantico, terror, comedia")
    @NotBlank(message = "El campo 'genero' es obligatorio.")
    private String genero;
    
    @Schema(description = "El estado que se encuentra el libro", example = "DISPONIBLE, OCUPADO, RESERVADO, AGOTADO")
    private Estado estado;
}
