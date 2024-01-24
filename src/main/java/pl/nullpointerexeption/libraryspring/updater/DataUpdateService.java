//package pl.nullpointerexeption.libraryspring.updater;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import pl.nullpointerexeption.libraryspring.model.Book;
//import pl.nullpointerexeption.libraryspring.service.BookService;
//
//@Service
//public class DataUpdateService {
//
//    @Autowired
//    private BookService bookService; // Używamy BookService do aktualizacji danych w bazie
//
//    private static final String EXTERNAL_API_URL = "http://localhost:8080/api/books"; // Zastąp tym adresem rzeczywistym adresem API
//
//    @Scheduled(fixedRate = 86400000) // Uruchamianie co 24 godziny (wartość w milisekundach)
//    public void updateDatabaseFromExternalApi() {
//        RestTemplate restTemplate = new RestTemplate();
//
//        // Pobierz dane z publicznego API
//        ResponseEntity<Book[]> responseEntity = restTemplate.exchange(
//                EXTERNAL_API_URL,
//                HttpMethod.GET,
//                null,
//                Book[].class
//        );
//
//        if (responseEntity.getStatusCode().is2xxSuccessful()) {
//            Book[] booksFromExternalApi = responseEntity.getBody();
//
//            // Zaktualizuj dane w bazie
//            if (booksFromExternalApi != null) {
//                for (Book externalBook : booksFromExternalApi) {
//                    Book existingBook = bookService.getBookById(externalBook.getBookID());
//
//                    if (existingBook != null) {
//                        // Jeśli książka już istnieje w bazie, zaktualizuj dane
//                        existingBook.setTitle(externalBook.getTitle());
//                        existingBook.setAuthor(externalBook.getAuthor());
//                        existingBook.setGenre(externalBook.getGenre());
//                        existingBook.setPublisher(externalBook.getPublisher());
//                        existingBook.setPublicationYear(externalBook.getPublicationYear());
//                        existingBook.setIsbn(externalBook.getIsbn());
//                        existingBook.setAvailableCopies(externalBook.getAvailableCopies());
//
//                        // Użyj metody PUT do zaktualizowania książki w zewnętrznym API
//                        HttpHeaders headers = new HttpHeaders();
//                        headers.setContentType(MediaType.APPLICATION_JSON);
//                        HttpEntity<Book> requestEntity = new HttpEntity<>(existingBook, headers);
//                        restTemplate.exchange(
//                                EXTERNAL_API_URL + "/" + existingBook.getBookID(),
//                                HttpMethod.PUT,
//                                requestEntity,
//                                Book.class
//                        );
//
//                        bookService.saveBook(existingBook);
//                    } else {
//                        // Jeśli książka nie istnieje w bazie, dodaj ją
//                        bookService.saveBook(externalBook);
//                    }
//                }
//            }
//        }
//    }
//}