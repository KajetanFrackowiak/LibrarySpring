package pl.nullpointerexeption.libraryspring.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.nullpointerexeption.libraryspring.model.Book;
import pl.nullpointerexeption.libraryspring.repository.BookRepository;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }
    public Book updateBook(Book updatedBook) {
        Book existingBook = bookRepository.findById(updatedBook.getBookID())
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));

        // Aktualizuj pola książki
        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setAuthor(updatedBook.getAuthor());
        existingBook.setGenre(updatedBook.getGenre());
        existingBook.setPublisher(updatedBook.getPublisher());
        existingBook.setPublicationYear(updatedBook.getPublicationYear());
        existingBook.setIsbn(updatedBook.getIsbn());
        existingBook.setAvailableCopies(updatedBook.getAvailableCopies());

        return bookRepository.save(existingBook);
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
