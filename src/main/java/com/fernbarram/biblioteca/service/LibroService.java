package com.fernbarram.biblioteca.service;

import com.fernbarram.biblioteca.model.Libro;
import com.fernbarram.biblioteca.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    public List<Libro> listarTodos() {
        return libroRepository.findAll();
    }

    public Optional<Libro> buscarPorId(Long id) {
        return libroRepository.findById(id);
    }

    public Libro crear(Libro libro) {
        libro.setId(null); // forzamos que se genere un nuevo ID
        return libroRepository.save(libro);
    }

    public Optional<Libro> actualizar(Long id, Libro libroActualizado) {
        return libroRepository.findById(id)
                .map(libroExistente -> {
                    libroExistente.setTitulo(libroActualizado.getTitulo());
                    libroExistente.setAutor(libroActualizado.getAutor());
                    libroExistente.setGenero(libroActualizado.getGenero());
                    libroExistente.setAnioPublicacion(libroActualizado.getAnioPublicacion());
                    return libroRepository.save(libroExistente);
                });
    }

    public boolean eliminar(Long id) {
        Optional<Libro> libro = libroRepository.findById(id);
        libro.ifPresent(l -> libroRepository.deleteById(id));
        return libro.isPresent();
    }
}

