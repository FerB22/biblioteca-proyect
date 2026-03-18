package com.fernbarram.biblioteca.repository;

import com.fernbarram.biblioteca.model.Libro;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class LibroRepository {

    // 20 libros de ejemplo precargados en memoria
    private List<Libro> libros = new ArrayList<>(Arrays.asList(
            new Libro(1L,  "Don Quijote de la Mancha", "Miguel de Cervantes",        "Novela",            1605),
            new Libro(2L,  "Cien años de soledad",     "Gabriel García Márquez",     "Realismo mágico",   1967),
            new Libro(3L,  "1984",                     "George Orwell",              "Distopía",          1949),
            new Libro(4L,  "El principito",            "Antoine de Saint-Exupéry",   "Fábula",            1943),
            new Libro(5L,  "Dune",                     "Frank Herbert",              "Ciencia ficción",   1965),
            new Libro(6L,  "Crónica de una muerte anunciada", "Gabriel García Márquez","Novela corta", 1981),
            new Libro(7L,  "Rayuela",                  "Julio Cortázar",             "Novela",            1963),
            new Libro(8L,  "La sombra del viento",     "Carlos Ruiz Zafón",          "Misterio",          2001),
            new Libro(9L,  "El señor de los anillos",  "J. R. R. Tolkien",           "Fantasía",          1954),
            new Libro(10L, "Harry Potter y la piedra filosofal", "J. K. Rowling",    "Fantasía",          1997),
            new Libro(11L, "Fahrenheit 451",           "Ray Bradbury",               "Distopía",          1953),
            new Libro(12L, "La naranja mecánica",      "Anthony Burgess",            "Distopía",          1962),
            new Libro(13L, "El nombre de la rosa",     "Umberto Eco",                "Misterio histórico",1980),
            new Libro(14L, "It",                       "Stephen King",               "Terror",            1986),
            new Libro(15L, "El resplandor",            "Stephen King",               "Terror",            1977),
            new Libro(16L, "Los juegos del hambre",    "Suzanne Collins",            "Ciencia ficción",   2008),
            new Libro(17L, "Orgullo y prejuicio",      "Jane Austen",                "Romance",           1813),
            new Libro(18L, "Drácula",                  "Bram Stoker",                "Terror",            1897),
            new Libro(19L, "Frankenstein",             "Mary Shelley",               "Ciencia ficción",   1818),
            new Libro(20L, "La metamorfosis",          "Franz Kafka",                "Novela corta",      1915)
    ));

    // siguiente id disponible después de los 20 libros
    private Long contadorId = 21L;

    public List<Libro> findAll() {
        return libros;
    }

    public Optional<Libro> findById(Long id) {
        return libros.stream()
                .filter(l -> l.getId().equals(id))
                .findFirst();
    }

    public Libro save(Libro libro) {
        if (libro.getId() == null) {
            libro.setId(contadorId++);
            libros.add(libro);
        } else {
            for (int i = 0; i < libros.size(); i++) {
                if (libros.get(i).getId().equals(libro.getId())) {
                    libros.set(i, libro);
                    break;
                }
            }
        }
        return libro;
    }

    public void deleteById(Long id) {
        libros.removeIf(l -> l.getId().equals(id));
    }
}
