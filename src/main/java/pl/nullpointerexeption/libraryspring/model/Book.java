package pl.nullpointerexeption.libraryspring.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookID;

    private String title;

    @ManyToOne
    @JoinColumn(name = "authorID")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "genreID")
    private Genre genre;

    @ManyToOne
    @JoinColumn(name = "publisherID")
    private Publisher publisher;

    private Integer publicationYear;
    private String isbn;
    private Integer availableCopies;

    public Book() {

    }
}

