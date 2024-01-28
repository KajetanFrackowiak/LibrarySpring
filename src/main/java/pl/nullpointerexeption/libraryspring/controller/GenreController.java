package pl.nullpointerexeption.libraryspring.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.nullpointerexeption.libraryspring.logger.GenreLogger;
import pl.nullpointerexeption.libraryspring.model.Genre;
import pl.nullpointerexeption.libraryspring.service.GenreService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
@RestController
@RequestMapping("/genres")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @Autowired
    private GenreLogger genreLogger;

    public void logMessages() {
        List<Genre> allGenres = genreService.getAllGenres();
        log.info("Getting all genres. Total genres: {}", allGenres.size());
        if (!allGenres.isEmpty()) {
            Genre firstGenre = allGenres.get(0);
            log.debug("First genre details: {}", firstGenre);
        }
        try {
            Genre savedGenre = genreService.saveGenre(new Genre());
            log.info("Saved genre: {}", savedGenre);
        } catch (Exception e) {
            log.error("An error occurred: {}", e.getMessage(), e);
        }
    }

    @GetMapping
    public List<Genre> getAllGenres() {
        return genreService.getAllGenres();
    }

    @GetMapping("/{id}")
    public Genre getGenreById(@PathVariable Long id) {
        return genreService.getGenreById(id);
    }

    @PostMapping
    public Genre saveGenre(@RequestBody Genre genre) {
        return genreService.saveGenre(genre);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Genre> updateGenre(@PathVariable Long id, @RequestBody Genre updatedGenre) {
        Genre genre = genreService.getGenreById(id);

        if (genre == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        updatedGenre.setGenreID(id);
        Genre updated = genreService.updateGenre(updatedGenre);

        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteGenre(@PathVariable Long id) {
        genreService.deleteGenre(id);
    }

    @GetMapping("/logs")
    public Stream<String> getLogs() throws IOException {
        return genreLogger.readLogFile("logs/app.log");
    }
}
