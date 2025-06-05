package com.devsenior.pablo.reto6.services.impl;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.devsenior.pablo.reto6.exceptions.EstadoNotFoundException;
import com.devsenior.pablo.reto6.exceptions.IsbnExistException;
import com.devsenior.pablo.reto6.exceptions.LibroNotFoundException;
import com.devsenior.pablo.reto6.exceptions.TituloExistException;
import com.devsenior.pablo.reto6.models.dto.Estado;
import com.devsenior.pablo.reto6.models.dto.LibroDTO;
import com.devsenior.pablo.reto6.models.entities.Libro;
import com.devsenior.pablo.reto6.repositories.LibroRepository;
import com.devsenior.pablo.reto6.services.LibroService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LibroServiceImpl implements LibroService{
    private final LibroRepository libroRepository;  
    private final AtomicLong sequence = new AtomicLong(1);
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
        var libro = libroRepository.getByTitulo(titulo);
        if(libro.isEmpty()){
            throw new LibroNotFoundException("No hay libros con el titulo: "+titulo);
        }
        return libro;
    }

    @Override
    public List<Libro> getByAutor(String autor) {
        var libro = libroRepository.getByAutor(autor);
        if(libro.isEmpty()){
            throw new LibroNotFoundException("No hay libros con el autor: "+autor);
        }
        return libro;
    }

    @Override
    public List<Libro> getByIsbn(String isbn) {
        var libro = libroRepository.getByIsbn(isbn);
        if(libro.isEmpty()){
            throw new LibroNotFoundException("No hay libros con el isbn: "+isbn);
        }
        return libro;
     
     }

    
    @Override
    public Libro save(LibroDTO libro) {
        if(validateIsbn(libro.getIsbn())){
            throw new IsbnExistException("El ISBN: "+libro.getIsbn()+ " ya está en uso");
        }else if(validateTitulo(libro.getTitulo())){
            throw new TituloExistException("El titulo: " + libro.getTitulo()+ " ya está en uso");
        }
        var newlibro = setLibro(libro);
        libroRepository.save(newlibro);
        return newlibro;
    }

    
    @Override
    public Libro update(Long id, LibroDTO libro) {
        var libro_modificar = libroRepository.getById(id);
        libro_modificar.setTitulo(libro.getTitulo());
        libro_modificar.setAutor(libro.getAutor());
        libro_modificar.setAñoPublicado(libro.getAñoPublicado());
        libro_modificar.setGenero(libro.getGenero());
        libro_modificar.setIsbn(libro.getIsbn());
        return libro_modificar;
    }
    
    @Override
    public void delete(Long id) {
        var libro = libroRepository.getById(id);
        libroRepository.delete(libro);
    }

    @Override
    public Libro changeStatus(Long id, String Status) {
        var libro = getById(id);
        Estado estado = setEstado(Status);
        libro.setEstado(estado);
        return libro;
    }
    

    private Boolean validateIsbn(String isbn){
        for (Libro libro : libroRepository.getAll()) {
            if(libro.getIsbn().equals(isbn)){
                return true;
            }
        }
        return false;
    }

    private Boolean validateTitulo(String titulo){
        for (Libro libro : libroRepository.getAll()) {
            if(libro.getTitulo().equals(titulo)){
                return true;
            }
        }
        return false;
    }
    private Libro setLibro(LibroDTO libroDTO){
        Libro libro = new Libro();
        libro.setAutor(libroDTO.getAutor());
        libro.setAñoPublicado(libroDTO.getAñoPublicado());
        libro.setGenero(libroDTO.getGenero());
        libro.setIsbn(libroDTO.getIsbn());
        libro.setTitulo(libroDTO.getTitulo());
        libro.setId(sequence.getAndIncrement());
        libro.setEstado(Estado.DISPONIBLE);
        
        return libro;
    }
    
    private Estado setEstado(String status ){
        for (Estado estado : Estado.values()) {
            if(estado.name().equals(status)){
                return estado;
            }
        }
        throw new EstadoNotFoundException("El estado: "+ status + 
        " no se encuentra disponible");
    }
    
    

} 
