package pl.nullpointerexeption.libraryspring.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.nullpointerexeption.libraryspring.model.Book;
import pl.nullpointerexeption.libraryspring.service.BookService;
import pl.nullpointerexeption.libraryspring.logger.BookLogger;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private BookLogger bookLogger;

    public void logMessages() {
        List<Book> allBooks = bookService.getAllBooks();
        log.info("Getting all books. Total books: {}", allBooks.size());
        if (!allBooks.isEmpty()) {
            Book firstBook = allBooks.get(0);
            log.debug("First book details: {}", firstBook);
        }
        try {
            Book savedBook = bookService.saveBook(new Book());
            log.info("Saved book: {}", savedBook);
        } catch (Exception e) {
            log.error("An error occurred: {}", e.getMessage(), e);
        }
    }

    @GetMapping
    public List<Book> getAllBooks() {
        logMessages();
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @PostMapping
    public Book saveBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        Book book = bookService.getBookById(id);

        if (book == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        updatedBook.setBookID(id);
        Book updated = bookService.updateBook(updatedBook);

        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    @GetMapping("/logs")
    public Stream<String> getLogs() throws IOException {
        return bookLogger.readLogFile("logs/app.log");
    }
}