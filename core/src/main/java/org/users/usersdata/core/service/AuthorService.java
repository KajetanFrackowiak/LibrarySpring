package org.users.usersdata.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.users.usersdata.core.model.Author;
import org.users.usersdata.core.repository.AuthorRepository;
import org.users.usersdata.web.exception.AuthorNotFoundException;

import java.util.List;


@Service
public class AuthorService {
    private static final Logger logger = LoggerFactory.getLogger(AuthorService.class);


    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(Long id) {
        logger.info("Getting author with id {}", id);
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