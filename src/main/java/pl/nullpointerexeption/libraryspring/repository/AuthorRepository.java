package pl.nullpointerexeption.libraryspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.nullpointerexeption.libraryspring.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
