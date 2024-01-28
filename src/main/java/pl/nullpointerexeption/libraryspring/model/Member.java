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
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberID;

    @Column
    private String memberName;
    @Column
    private Integer birthYear;
    @Column
    private String address;
    @Column
    private String email;

    public Member() {

    }
}
