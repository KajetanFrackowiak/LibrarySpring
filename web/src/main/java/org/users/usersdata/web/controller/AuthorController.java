package org.users.usersdata.web.controller;


import org.users.usersdata.core.service.AuthorService;
import logger.AuthorLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.users.usersdata.core.model.Author;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

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
