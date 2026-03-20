package com.fernbarram.biblioteca.service;

import com.fernbarram.biblioteca.model.Libro;
import com.fernbarram.biblioteca.repository.LibroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroService {

    private final LibroRepository libroRepository;

    // Inyección por constructor
    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

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

    // Nuevo método: buscar por texto en título, autor o género
    public List<Libro> buscarPorTexto(String texto) {
        String textoLower = texto.toLowerCase();

        return libroRepository.findAll().stream()
                .filter(libro ->
                        (libro.getTitulo() != null &&
                                libro.getTitulo().toLowerCase().contains(textoLower)) ||
                                (libro.getAutor() != null &&
                                        libro.getAutor().toLowerCase().contains(textoLower)) ||
                                (libro.getGenero() != null &&
                                        libro.getGenero().toLowerCase().contains(textoLower))
                )
                .toList();
    }
}
