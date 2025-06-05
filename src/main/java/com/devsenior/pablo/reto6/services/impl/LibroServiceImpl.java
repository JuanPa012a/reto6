package com.devsenior.pablo.reto6.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.devsenior.pablo.reto6.models.entities.Libro;
import com.devsenior.pablo.reto6.repositories.LibroRepository;
import com.devsenior.pablo.reto6.services.LibroService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LibroServiceImpl implements LibroService{
    private final LibroRepository libroRepository;  
    @Override
    public List<Libro> getAll() {
        return libroRepository.getAll();
    }

    @Override
    public Libro getById(Long id) {
        return libroRepository.getById(id);
    }

    @Override
    public List<Libro> getByTitulo(String titulo) {
        return libroRepository.getByTitulo(titulo);
    }

    @Override
    public List<Libro> getByAutor(String autor) {
        return libroRepository.getByAutor(autor);
    }

    @Override
    public List<Libro> getByIsbn(String isbn) {
        return libroRepository.getByIsbn(isbn);
     }

    @Override
    public Libro save(Libro libro) {
        return libroRepository.save(libro);
    }

    @Override
    public Libro update(Long id, Libro libro) {
        return libroRepository.update(id, libro);
    }

    @Override
    public void delete(Long id) {
        libroRepository.delete(id);
    }

    @Override
    public Libro changeStatus(Long id, String Status) {
        return libroRepository.changeStatus(id, Status);
    }
    

    
} 
