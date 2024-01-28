package pl.nullpointerexeption.libraryspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.nullpointerexeption.libraryspring.model.Publisher;
import pl.nullpointerexeption.libraryspring.service.PublisherService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/publishers")
public class PublisherController {

    private static final Logger logger = LoggerFactory.getLogger(PublisherController.class);

    @Autowired
    private PublisherService publisherService;

    @GetMapping
    public List<Publisher> getAllPublishers() {
        logger.info("Getting all publishers");
        return publisherService.getAllPublishers();
    }

    @GetMapping("/{id}")
    public Publisher getPublisherById(@PathVariable Long id) {
        logger.info("Getting publisher with id: {}", id);
        return publisherService.getPublisherById(id);
    }

    @PostMapping
    public Publisher savePublisher(@RequestBody Publisher publisher) {
        logger.info("Saving publisher: {}", publisher);
        return publisherService.savePublisher(publisher);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Publisher> updatePublisher(@PathVariable Long id, @RequestBody Publisher updatedPublisher) {
        logger.info("Updating publisher with id: {}", id);
        Publisher publisher = publisherService.getPublisherById(id);

        if (publisher == null) {
            logger.warn("Publisher with id: {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        updatedPublisher.setPublisherID(id);
        Publisher updated = publisherService.updatePublisher(updatedPublisher);

        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deletePublisher(@PathVariable Long id) {
        logger.info("Deleting publisher with id: {}", id);
        publisherService.deletePublisher(id);
    }
}