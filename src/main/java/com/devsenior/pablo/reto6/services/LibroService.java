package com.devsenior.pablo.reto6.services;

import java.util.List;

import com.devsenior.pablo.reto6.models.dto.LibroDTO;
import com.devsenior.pablo.reto6.models.entities.Libro;

public interface LibroService {
    List<Libro> getAll();

    Libro getById(Long id);

    List<Libro> getByTitulo(String titulo);

    List<Libro> getByAutor(String autor);

    List<Libro> getByIsbn(String isbn);


    Libro save(LibroDTO libro);

    Libro update(Long id, LibroDTO libro);

    void delete(Long id);

    Libro changeStatus(Long id, String Status);

}
