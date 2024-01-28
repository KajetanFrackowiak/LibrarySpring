package pl.nullpointerexeption.libraryspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.nullpointerexeption.libraryspring.model.Author;
import pl.nullpointerexeption.libraryspring.service.AuthorService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private static final Logger logger = LoggerFactory.getLogger(AuthorController.class);

    @Autowired
    private AuthorService authorService;

    @GetMapping
    public List<Author> getAllAuthors() {
        logger.info("Getting all authors");
        return authorService.getAllAuthors();
    }

    @GetMapping("/{id}")
    public Author getAuthorById(@PathVariable Long id) {
        logger.info("Getting author with id: {}", id);
        return authorService.getAuthorById(id);
    }

    @PostMapping
    public Author saveAuthor(@RequestBody Author author) {
        logger.info("Saving author: {}", author);
        return authorService.saveAuthor(author);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable Long id, @RequestBody Author updatedAuthor) {
        logger.info("Updating author with id: {}", id);
        Author author = authorService.getAuthorById(id);

        if (author == null) {
            logger.warn("Author with id: {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        updatedAuthor.setAuthorID(id);
        Author updated = authorService.updateAuthor(updatedAuthor);

        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable Long id) {
        logger.info("Deleting author with id: {}", id);
        authorService.deleteAuthor(id);
    }
}