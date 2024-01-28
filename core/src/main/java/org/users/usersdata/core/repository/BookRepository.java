package org.users.usersdata.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.users.usersdata.core.model.Book;


public interface BookRepository extends JpaRepository<Book, Long> {
}

