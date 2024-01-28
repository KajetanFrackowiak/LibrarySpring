package pl.nullpointerexeption.libraryspring.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long publisherID;
    @Column
    private String publisherName;
    @Column
    private Integer establishedYear;
    @Column
    private String hqLocation;


    public Publisher() {

    }
}
