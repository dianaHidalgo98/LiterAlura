package com.aluracursos.LiterAlura;

import com.aluracursos.LiterAlura.main.Main;
import com.aluracursos.LiterAlura.model.Author;
import com.aluracursos.LiterAlura.model.Book;
import com.aluracursos.LiterAlura.model.DataAuthor;
import com.aluracursos.LiterAlura.model.DataBook;
import com.aluracursos.LiterAlura.repository.AuthorRepository;
import com.aluracursos.LiterAlura.repository.BookRepository;
import com.aluracursos.LiterAlura.services.GutendexBooksAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;


    public static void main(String[] args) {
        SpringApplication.run(LiterAluraApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Main main = new Main();
        Scanner scanner = new Scanner(System.in);
        GutendexBooksAPI booksApi = new GutendexBooksAPI();

        while (true) {
            main.showMenu();
            var option = scanner.nextLine();

            switch (option) {
                case "1": {
                    System.out.println("Escriba el título del libro que desee buscar");
                    var title = scanner.nextLine();

                    var dataBooks = booksApi.searchByTitle(title);
                    var books = this.processDataBooks(dataBooks);

                    for (var book : books) {
                        book.printInConsole();
                    }

                    break;
                }
                case "2": {
                    var books = this.bookRepository.findAll();

                    for (var book : books) {
                        book.printInConsole();
                    }

                    break;
                }
                case "3": {
                    var authors = this.authorRepository.findAll();

                    authors.stream().toList().forEach(System.out::println);

                    break;
                }
                case "4": {
                    System.out.println("Ingrese el año de inicio que desea buscar");
                    var birthDate = scanner.nextLine();
                    System.out.println("Ingrese el año final que desea buscar");
                    var deathDate = scanner.nextLine();

                    var authors = authorRepository.findByBirthDateLessThanEqualAndDeathDateGreaterThanEqualOrDeathDateIsNull(Integer.valueOf(deathDate), Integer.valueOf(birthDate));

                    authors.forEach(System.out::println);
                    break;
                }
                case "5": {
                    System.out.println("""
                            Ingrese el idioma para buscar los libros:\s
                            es - español
                            en - inglés
                            fr - francés
                            pt - portugués
                            """);
                    var language = scanner.nextLine();
                    List<Book> booksInLanguage = bookRepository.findBooksByLanguage("%" + language + "%");
                    if (booksInLanguage.isEmpty()) {
                        System.out.println("No se encontraron libros en este idioma.");
                    } else {
                        booksInLanguage.forEach(Book::printInConsole);
                    }
                    break;
                }
                case "0": {
                    System.out.println("Closing application...");

                    return;
                }
                default: {
                    System.out.println("Invalid option. Try again.");
                }
            }
        }
    }

    private List<Book> processDataBooks(List<DataBook> dataBooks) {
        List<Book> books = new ArrayList<>();

        for (var dataBook : dataBooks) {
            Optional<Book> existingBook = bookRepository.findByGutenID(dataBook.gutenID());

            Book book;
            if (existingBook.isPresent()) {
                book = existingBook.get();
                book.setTitle(dataBook.title());
                book.setLanguages(dataBook.languages().toString());
                book.setNumberOfDownloads(dataBook.numberDownloads());
                // Consider updating the authors as needed.
            } else {
                book = Book.fromDataBook(dataBook);
                for (DataAuthor dataAuthor : dataBook.authors()) {
                    book.addAuthor(Author.fromDataAuthor(dataAuthor));
                }
                this.authorRepository.saveAll(book.getAuthors());
            }

            bookRepository.save(book);
            books.add(book);
        }

        return books;
    }
}
