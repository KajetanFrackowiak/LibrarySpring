package org.users.usersdata.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.users.usersdata.core.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
