package com.aluracursos.LiterAlura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer deathDate;
    private Integer birthDate;

    @ManyToMany(mappedBy = "authors", fetch = FetchType.LAZY)
    private List<Book> books;

    public Author() {}

    public static Author fromDataAuthor(DataAuthor dataAuthor) {
        var author = new Author();

        author.setName(dataAuthor.name());

        if (dataAuthor.birthDate() == null) {
            author.setBirthDate(null);
        } else {
            author.setBirthDate(Integer.valueOf(dataAuthor.birthDate()));
        }

        if (dataAuthor.deathDate() == null) {
            author.setDeathDate(null);
        } else {
            author.setDeathDate(Integer.valueOf(dataAuthor.deathDate()));
        }

        return author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Integer getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(Integer deathDate) {
        this.deathDate = deathDate;
    }

    public Integer getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Integer birthDate) {
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return
                "deathDate=" + deathDate +
                ", birthDate=" + birthDate +
                ", name='" + name + '\'';
    }
}
