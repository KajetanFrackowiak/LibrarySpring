package org.users.usersdata.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.users.usersdata.core.model.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
