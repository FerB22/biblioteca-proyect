package com.fernbarram.biblioteca.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Libro {

    private Long id;
    private String titulo;
    private String autor;
    private String genero;
    private int anioPublicacion;

}

