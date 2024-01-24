package pl.nullpointerexeption.libraryspring.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.nullpointerexeption.libraryspring.model.Genre;
import pl.nullpointerexeption.libraryspring.repository.GenreRepository;

import java.util.List;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    public Genre getGenreById(Long id) {
        return genreRepository.findById(id).orElse(null);
    }

    public Genre updateGenre(Genre updatedGenre) {
        Genre existingGenre = genreRepository.findById(updatedGenre.getGenreID())
                .orElseThrow(() -> new EntityNotFoundException("Genre not found"));

        // Aktualizuj pola gatunku
        existingGenre.setGenreName(updatedGenre.getGenreName());

        return genreRepository.save(existingGenre);
    }


        public Genre saveGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    public void deleteGenre(Long id) {
        genreRepository.deleteById(id);
    }
}

