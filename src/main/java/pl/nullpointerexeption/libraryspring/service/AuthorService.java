package pl.nullpointerexeption.libraryspring.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.nullpointerexeption.libraryspring.model.Author;
import pl.nullpointerexeption.libraryspring.repository.AuthorRepository;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }
    public Author updateAuthor(Author updatedAuthor) {
        Author existingAuthor = authorRepository.findById(updatedAuthor.getAuthorID())
                .orElseThrow(() -> new EntityNotFoundException("Author not found"));

        // Aktualizuj pola autora
        existingAuthor.setAuthorName(updatedAuthor.getAuthorName());
        existingAuthor.setBirthYear(updatedAuthor.getBirthYear());
        existingAuthor.setNationality(updatedAuthor.getNationality());

        return authorRepository.save(existingAuthor);
    }
    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
}