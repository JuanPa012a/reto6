package com.devsenior.pablo.reto6.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsenior.pablo.reto6.models.dto.LibroDTO;
import com.devsenior.pablo.reto6.models.entities.Libro;
import com.devsenior.pablo.reto6.services.LibroService;

import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@Tag(name = "Libros", description = "Endpoints para gestion de biblioteca")
@RestController
@AllArgsConstructor
@RequestMapping("api/libros")
public class LibroController {
    private final LibroService libroService;

    @Operation(
        summary = "Listar los libros",
        description = "Lista todos los libros; incluyendo los agotados y ocupados. "+
        "Tambien se puede filtrar por titulo, autor y ISBN."
    )
    @ApiResponses(value= {
    @ApiResponse(
        responseCode = "200",
        description = "Lista de libros recuperada exitosamente",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Libro.class))
    ),
    @ApiResponse(
        responseCode = "204",
        description = "Lista de libros vacía",
        content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class))
    ),
    @ApiResponse(
        responseCode = "404",
        description = "El libro buscado no fue encontrado",
        content = @Content
    )})
    @GetMapping("buscar")
    public ResponseEntity<List<Libro>> searchBooks(
            @RequestParam(name = "titulo", required = false) String titulo,
            @RequestParam(name = "autor", required = false) String autor,
            @RequestParam(name = "isbn", required = false) String isbn) {
        if (titulo != null) {
            var libro = libroService.getByTitulo(titulo);
            if(libro.isEmpty()){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(libro);
        } else if (autor != null) {
            var libro = libroService.getByAutor(autor);
            if(libro.isEmpty()){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(libro);
        } else if (isbn != null) {
            var libro = libroService.getByIsbn(isbn);
            if(libro.isEmpty()){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(libro);
            
        }
        var libros = libroService.getAll();
        if(libros.isEmpty()){
            return ResponseEntity.noContent().build();
        }
       return ResponseEntity.ok(libros);
      
    }
    
    @Operation(
        summary = "Obtener un libro por ID",
        description = "Busca un libro por su ID."
    )
    @ApiResponses(value= {
    @ApiResponse(
        responseCode = "200",
        description = "Libro recuperado exitosamente",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Libro.class))
    ),
    @ApiResponse(
        responseCode = "404",
        description = "El libro buscado no fue encontrado",
        content = @Content
    )})
    @GetMapping("{id}")
    public Libro getBookById(@PathVariable Long id) {
        return libroService.getById(id);
    }
    
    @Operation(
        summary = "Guardar Libro",
        description = "Guardas el libro en la base de datos."
    )
    @ApiResponses(value= {
    @ApiResponse(
        responseCode = "201",
        description = "Libro guardado exitosamente",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Libro.class))
    ),
    @ApiResponse(
        responseCode = "409",
        description = "El libro que intenta guardar ya tiene el titulo o el ISBN existente",
        content = @Content
    ),
    @ApiResponse(
        responseCode = "400",
        description = "Los campos tanto como el titulo, ISBN, año publicado, genero o el autor son obligatorios",
        content = @Content
    )})
    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping
    public Libro saveBook(@RequestBody @Valid LibroDTO libro) {
       return libroService.save(libro);
    }

    @Operation(
        summary = "Cambiar Estado",
        description = "Aqui realizas el cambio del estado del libro. Si lo ocuparon, estan agotados, lo reservaron o esta disponible."
    )
    @ApiResponses(value= {
    @ApiResponse(
        responseCode = "200",
        description = "Estado actualizado exitosamente",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Libro.class))
    ),
    @ApiResponse(
        responseCode = "404",
        description = "El libro buscado no fue encontrado",
        content = @Content
    ),
    @ApiResponse(
        responseCode = "409",
        description = "El Estado indicado no existe",
        content = @Content
    )})
    @ResponseStatus(code = HttpStatus.OK)
    @PostMapping("{id}/estado")
    public Libro changeStatusBook(
            @PathVariable Long id,
            @RequestParam(name = "estado", required = true) String estado) {
        return libroService.changeStatus(id, estado.toUpperCase());
    }


    @Operation(
        summary = "Actualizar Libro",
        description = "Actualizas el libro que has seleccionado en la base de datos."
    )
    @ApiResponses(value= {
    @ApiResponse(
        responseCode = "200",
        description = "Libro actualizado exitosamente",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Libro.class))
    ),
    @ApiResponse(
        responseCode = "404",
        description = "El libro buscado no fue encontrado",
        content = @Content
    ),
    @ApiResponse(
        responseCode = "400",
        description = "Los campos tanto como el titulo, ISBN, año publicado, genero o el autor son obligatorios",
        content = @Content
    )})
    @ResponseStatus(code = HttpStatus.OK)
    @PutMapping("{id}")
    public Libro editBook(@PathVariable Long id, @RequestBody @Valid LibroDTO libro) {
        return libroService.update(id, libro);
    }

    @Operation(
        summary = "Eliminar Libro",
        description = "Eliminas el libro que has seleccionado en la base de datos."
    )
    @ApiResponses(value= {
    @ApiResponse(
        responseCode = "204",
        description = "Libro eliminado exitosamente",
        content = @Content
    ),
    @ApiResponse(
        responseCode = "404",
        description = "El libro buscado no fue encontrado",
        content = @Content
    )})
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void deleteBook(@PathVariable Long id){
        libroService.delete(id);
    }
    
    

    }

    

