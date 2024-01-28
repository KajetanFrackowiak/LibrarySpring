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
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authorID;

    @Column
    private String authorName;

    @Column
    private Integer birthYear;
    @Column
    private String nationality;
    public Author() {

    }
}