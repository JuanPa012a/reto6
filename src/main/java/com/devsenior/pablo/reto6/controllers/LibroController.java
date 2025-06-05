package com.devsenior.pablo.reto6.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsenior.pablo.reto6.models.entities.Libro;
import com.devsenior.pablo.reto6.services.impl.LibroServiceImpl;

import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@AllArgsConstructor
@RequestMapping("api/libros")
public class LibroController {
    private final LibroServiceImpl libroService;

    @GetMapping
    public List<Libro> searchBooks(
            @RequestParam(name = "titulo", required = false) String titulo,
            @RequestParam(name = "autor", required = false) String autor,
            @RequestParam(name = "isbn", required = false) String isbn) {
        if (titulo != null) {
            return libroService.getByTitulo(titulo);
        } else if (autor != null) {
            return libroService.getByAutor(autor);
        } else if (isbn != null) {
            return libroService.getByIsbn(isbn);
        }
        return libroService.getAll();
    }
    
    @GetMapping("{id}")
    public Libro getBookById(@PathVariable Long id) {
        return libroService.getById(id);
    }

    @PostMapping
    public Libro saveBook(@RequestBody Libro libro) {
       return libroService.save(libro);
    }

    
    
    @PostMapping("{id}/estado")
    public Libro changeStatusBook(
            @PathVariable Long id,
            @RequestParam(name = "estado", required = true) String estado) {
        return libroService.changeStatus(id, estado.toUpperCase());
    }

    @PutMapping("{id}")
    public Libro editBook(@PathVariable Long id, @RequestBody Libro libro) {
        return libroService.update(id, libro);
    }

    @DeleteMapping("{id}")
    public String deleteBook(@PathVariable Long id){
        libroService.delete(id);
        return "Eliminado con éxito";
    }
    
    

    }

    

