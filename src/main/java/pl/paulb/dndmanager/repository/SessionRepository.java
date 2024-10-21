package pl.paulb.dndmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.paulb.dndmanager.model.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    Page<Session> findAll(Pageable pageable);
}
