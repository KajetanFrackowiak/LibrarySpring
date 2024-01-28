package pl.nullpointerexeption.libraryspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.nullpointerexeption.libraryspring.model.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
