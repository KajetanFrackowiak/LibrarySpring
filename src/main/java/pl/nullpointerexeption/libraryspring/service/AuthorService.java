package pl.nullpointerexeption.libraryspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.nullpointerexeption.libraryspring.exception.AuthorNotFoundException;
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
        return authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException("Author with id " + id + " not found"));
    }
    public Author updateAuthor(Author updatedAuthor) {
        Author existingAuthor = authorRepository.findById(updatedAuthor.getAuthorID())
                .orElseThrow(() -> new AuthorNotFoundException("Author with id " + updatedAuthor.getAuthorID() + " not found"));

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