package pl.nullpointerexeption.libraryspring.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.nullpointerexeption.libraryspring.logger.AuthorLogger;
import pl.nullpointerexeption.libraryspring.model.Author;
import pl.nullpointerexeption.libraryspring.service.AuthorService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;
    @Autowired
    private AuthorLogger authorLogger;

    public void logMessages() {
        List<Author> allAuthors = authorService.getAllAuthors();
        log.info("Getting all authors. Total authors: {}", allAuthors.size());
        if (!allAuthors.isEmpty()) {
            Author firstAuthor = allAuthors.get(0);
            log.debug("First author details: {}", firstAuthor);
        }
        try {
            Author savedAuthor = authorService.saveAuthor(new Author());
            log.info("Saved author: {}", savedAuthor);
        } catch (Exception e) {
            log.error("An error occurred: {}", e.getMessage(), e);
        }
    }

    @GetMapping
    public List<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping("/{id}")
    public Author getAuthorById(@PathVariable Long id) {
        return authorService.getAuthorById(id);
    }

    @PostMapping
    public Author saveAuthor(@RequestBody Author author) {
        return authorService.saveAuthor(author);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable Long id, @RequestBody Author updatedAuthor) {
        Author author = authorService.getAuthorById(id);

        if (author == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        updatedAuthor.setAuthorID(id);
        Author updated = authorService.updateAuthor(updatedAuthor);

        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
    }

    @GetMapping("/logs")
    public Stream<String> getLogs() throws IOException {
        return AuthorLogger.readLogFile("logs/app.log");
    }
}
