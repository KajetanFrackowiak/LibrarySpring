package pl.nullpointerexeption.libraryspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.nullpointerexeption.libraryspring.model.Publisher;
import pl.nullpointerexeption.libraryspring.service.PublisherService;

import java.util.List;

@RestController
@RequestMapping("/publishers")
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

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
}
