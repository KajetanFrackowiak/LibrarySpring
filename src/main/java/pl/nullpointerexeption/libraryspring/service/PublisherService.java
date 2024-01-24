package pl.nullpointerexeption.libraryspring.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.nullpointerexeption.libraryspring.model.Publisher;
import pl.nullpointerexeption.libraryspring.repository.PublisherRepository;

import java.util.List;

@Service
public class PublisherService {

    @Autowired
    private PublisherRepository publisherRepository;

    public List<Publisher> getAllPublishers() {
        return publisherRepository.findAll();
    }

    public Publisher getPublisherById(Long id) {
        return publisherRepository.findById(id).orElse(null);
    }

    public Publisher updatePublisher(Publisher updatedPublisher) {
        Publisher existingPublisher = publisherRepository.findById(updatedPublisher.getPublisherID())
                .orElseThrow(() -> new EntityNotFoundException("Publisher not found"));

        // Aktualizuj pola wydawcy
        existingPublisher.setPublisherName(updatedPublisher.getPublisherName());
        existingPublisher.setEstablishedYear(updatedPublisher.getEstablishedYear());
        existingPublisher.setHqLocation(updatedPublisher.getHqLocation());

        return publisherRepository.save(existingPublisher);
    }
    public Publisher savePublisher(Publisher publisher) {
        return publisherRepository.save(publisher);
    }

    public void deletePublisher(Long id) {
        publisherRepository.deleteById(id);
    }
}

