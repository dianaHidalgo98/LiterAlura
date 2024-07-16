package com.aluracursos.LiterAlura.main;

import com.aluracursos.LiterAlura.model.Book;

import java.util.Optional;

public class Main {
    private Optional<Book> searchCreatedBook;
    public void showMenu() {
        System.out.println("""
                ---------------------
                1 - Buscar libro por título
                2 - Listar libros buscados
                3 - Listar autores registrados
                4 - Listar autores vivos en un determinado año
                5 - Listar libros por idioma\n
                0 - Salir
                Elija la opción a través de su número
                ----------------------
                """);
    }
}
