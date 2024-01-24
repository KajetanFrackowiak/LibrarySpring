package pl.nullpointerexeption.libraryspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.nullpointerexeption.libraryspring.model.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
