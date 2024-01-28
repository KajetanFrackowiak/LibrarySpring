package pl.nullpointerexeption.libraryspring.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.nullpointerexeption.libraryspring.logger.PublisherLogger;
import pl.nullpointerexeption.libraryspring.model.Publisher;
import pl.nullpointerexeption.libraryspring.service.PublisherService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
@RestController
@RequestMapping("/publishers")
public class PublisherController {

    @Autowired
    private PublisherService publisherService;
    @Autowired
    private PublisherLogger publisherLogger;

    public void logMessages() {
        List<Publisher> allPublishers = publisherService.getAllPublishers();
        log.info("Getting all publishers. Total publishers: {}", allPublishers.size());
        if (!allPublishers.isEmpty()) {
            Publisher firstPublisher = allPublishers.get(0);
            log.debug("First publisher details: {}", firstPublisher);
        }
        try {
            Publisher savedPublisher = publisherService.savePublisher(new Publisher());
            log.info("Saved publisher: {}", savedPublisher);
        } catch (Exception e) {
            log.error("An error occurred: {}", e.getMessage(), e);
        }
    }

    @GetMapping
    public List<Publisher> getAllPublishers() {
        return publisherService.getAllPublishers();
    }

    @GetMapping("/{id}")
    public Publisher getPublisherById(@PathVariable Long id) {
        return publisherService.getPublisherById(id);
    }

    @PostMapping
    public Publisher savePublisher(@RequestBody Publisher publisher) {
        return publisherService.savePublisher(publisher);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Publisher> updatePublisher(@PathVariable Long id, @RequestBody Publisher updatedPublisher) {
        Publisher publisher = publisherService.getPublisherById(id);

        if (publisher == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        updatedPublisher.setPublisherID(id);
        Publisher updated = publisherService.updatePublisher(updatedPublisher);

        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deletePublisher(@PathVariable Long id) {
        publisherService.deletePublisher(id);
    }
    @GetMapping("/logs")
    public Stream<String> getLogs() throws IOException {
        return publisherLogger.readLogFile("logs/app.log");
    }
}