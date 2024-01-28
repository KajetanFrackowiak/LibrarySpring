package org.users.usersdata.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.users.usersdata.core.model.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
}

