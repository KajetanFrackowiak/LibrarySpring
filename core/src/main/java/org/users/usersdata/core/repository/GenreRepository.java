package org.users.usersdata.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.users.usersdata.core.model.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
