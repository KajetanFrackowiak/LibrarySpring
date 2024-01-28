package pl.nullpointerexeption.libraryspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.nullpointerexeption.libraryspring.model.Genre;
import pl.nullpointerexeption.libraryspring.service.GenreService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenreController {

    private static final Logger logger = LoggerFactory.getLogger(GenreController.class);

    @Autowired
    private GenreService genreService;

    @GetMapping
    public List<Genre> getAllGenres() {
        logger.info("Getting all genres");
        return genreService.getAllGenres();
    }

    @GetMapping("/{id}")
    public Genre getGenreById(@PathVariable Long id) {
        logger.info("Getting genre with id: {}", id);
        return genreService.getGenreById(id);
    }

    @PostMapping
    public Genre saveGenre(@RequestBody Genre genre) {
        logger.info("Saving genre: {}", genre);
        return genreService.saveGenre(genre);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Genre> updateGenre(@PathVariable Long id, @RequestBody Genre updatedGenre) {
        logger.info("Updating genre with id: {}", id);
        Genre genre = genreService.getGenreById(id);

        if (genre == null) {
            logger.warn("Genre with id: {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        updatedGenre.setGenreID(id);
        Genre updated = genreService.updateGenre(updatedGenre);

        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteGenre(@PathVariable Long id) {
        logger.info("Deleting genre with id: {}", id);
        genreService.deleteGenre(id);
    }
}