package com.devsenior.pablo.reto6.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.devsenior.pablo.reto6.exceptions.LibroNotFoundException;
import com.devsenior.pablo.reto6.models.entities.Libro;

import lombok.AllArgsConstructor;


@Repository
@AllArgsConstructor
public class LibroRepository {
    private final List<Libro> libros;
    

    public List<Libro> getAll(){
        return libros;
    }

     public Libro getById(Long id){
        return libros.stream()
        .filter(l -> l.getId().equals(id))
        .findFirst().orElseThrow(() -> new LibroNotFoundException("El libro con el id: " + id + " No existe."));
    }

    public List<Libro> getByTitulo(String titulo){
          return libros.stream()
          .filter(l -> l.getTitulo().contains(titulo)).toList(); 
    }

    public List<Libro> getByAutor(String autor){
        return libros.stream()
          .filter(l -> l.getAutor().contains(autor)).toList(); 
    }

    public List<Libro> getByIsbn(String isbn){
        return libros.stream()
          .filter(l -> l.getIsbn().contains(isbn)).toList(); 
    }


    public void save(Libro libro){
        libros.add(libro);
       
    }

    public void delete(Libro libro){
        libros.remove(libro);
    }


    
}
