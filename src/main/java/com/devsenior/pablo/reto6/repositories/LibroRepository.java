package com.devsenior.pablo.reto6.repositories;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import com.devsenior.pablo.reto6.models.dto.Estado;
import com.devsenior.pablo.reto6.models.entities.Libro;

import lombok.AllArgsConstructor;


@Repository
@AllArgsConstructor
public class LibroRepository {
    private final List<Libro> libros;
    private final AtomicLong sequence = new AtomicLong(1);

    public List<Libro> getAll(){
        return libros;
    }

     public Libro getById(Long id){
        return libros.stream()
        .filter(l -> l.getId().equals(id))
        .findFirst().get();
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


    public Libro save(Libro libro){
        libro.setId(sequence.getAndIncrement());
        libro.setEstado(Estado.DISPONIBLE);
        libros.add(libro);
        return libro;
    }

    public Libro update(Long id, Libro libro){
        var libro_modificar = getById(id);
        libro_modificar.setTitulo(libro.getTitulo());
        libro_modificar.setAutor(libro.getAutor());
        libro_modificar.setAñoPublicado(libro.getAñoPublicado());
        libro_modificar.setGenero(libro.getGenero());
        libro_modificar.setIsbn(libro.getIsbn());
        
        return libro_modificar; 
    }


    public void delete(Long id){
        var libro = getById(id);
        libros.remove(libro);
    }

    public Libro changeStatus(Long id, String Status){
        var libro = getById(id);
        Estado estado = Estado.valueOf(Status);
        libro.setEstado(estado);
        return libro;
    }
    

    
    
}
