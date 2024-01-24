package pl.nullpointerexeption.libraryspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.nullpointerexeption.libraryspring.model.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
}

