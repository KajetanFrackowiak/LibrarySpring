package pl.nullpointerexeption.libraryspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.nullpointerexeption.libraryspring.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}

