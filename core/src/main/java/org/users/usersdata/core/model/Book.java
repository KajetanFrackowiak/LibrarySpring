package org.users.usersdata.core.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookID;

    @Column
    private String title;
    @Column
    private Integer publicationYear;
    @Column
    private String isbn;
    @Column
    private Integer availableCopies;

    @ManyToOne
    @JoinColumn(name = "authorID")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "genreID")
    private Genre genre;

    @ManyToOne
    @JoinColumn(name = "publisherID")
    private Publisher publisher;



    public Book() {

    }
}

