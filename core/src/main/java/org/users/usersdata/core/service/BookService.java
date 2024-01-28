package org.users.usersdata.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.users.usersdata.core.model.Book;
import org.users.usersdata.core.repository.BookRepository;
import org.users.usersdata.web.exception.BookNotFoundException;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book with id " + id + " not found"));
    }

    public Book updateBook(Book updatedBook) {
        Book existingBook = bookRepository.findById(updatedBook.getBookID())
                .orElseThrow(() -> new BookNotFoundException("Book with id " + updatedBook.getBookID() + " not found"));

        // Update book fields
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
