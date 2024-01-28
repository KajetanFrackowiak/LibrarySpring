package pl.nullpointerexeption.libraryspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.nullpointerexeption.libraryspring.model.Book;
import pl.nullpointerexeption.libraryspring.service.BookService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        logger.info("Getting all books");
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        logger.info("Getting book with id: {}", id);
        return bookService.getBookById(id);
    }

    @PostMapping
    public Book saveBook(@RequestBody Book book) {
        logger.info("Saving book: {}", book);
        return bookService.saveBook(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        logger.info("Updating book with id: {}", id);
        Book book = bookService.getBookById(id);

        if (book == null) {
            logger.warn("Book with id: {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        updatedBook.setBookID(id);
        Book updated = bookService.updateBook(updatedBook);

        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        logger.info("Deleting book with id: {}", id);
        bookService.deleteBook(id);
    }
}