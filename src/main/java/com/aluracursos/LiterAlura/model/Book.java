package com.aluracursos.LiterAlura.model;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private Integer gutenID;

    @Column(columnDefinition = "TEXT")
    private String title;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "book_author", joinColumns = @JoinColumn(name = "book"), inverseJoinColumns = @JoinColumn(name = "author"))
    private List<Author> authors = new ArrayList<>();

    private String languages;
    private Double numberOfDownloads;

    public Book() {}

    public static Book fromDataBook(DataBook dataBook) {
        var book = new Book();

        book.setTitle(dataBook.title());
        book.setAuthors(new ArrayList<>());
        book.setLanguages(dataBook.languages().toString());
        book.setNumberOfDownloads(dataBook.numberDownloads());
        book.setGutenID(dataBook.gutenID());

        return book;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public void addAuthor(Author author) {
        this.authors.add(author);
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public Double getNumberOfDownloads() {
        return numberOfDownloads;
    }

    public void setNumberOfDownloads(Double numberOfDownloads) {
        this.numberOfDownloads = numberOfDownloads;
    }

    public void setGutenID(Integer gutenID) {
        this.gutenID = gutenID;
    }

    public void printInConsole() {
        System.out.println("------LIBRO------");
        System.out.println("Title: " + this.title);
        System.out.println("Authors: " + this.authors.stream().map(Author::getName).collect(Collectors.joining("; ")));
        System.out.println("Languages: " + this.languages);
        System.out.println("Number of Downloads: " + this.numberOfDownloads);
        System.out.println("----------------");
    }

    @Override
    public String toString() {
        return "title='" + title + '\'' + ", author='" + authors + '\'' + ", languages=" + languages + ", numberOfDownloads=" + numberOfDownloads + '\'';
    }
}
