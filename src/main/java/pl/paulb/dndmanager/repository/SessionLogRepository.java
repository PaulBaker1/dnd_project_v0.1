package pl.paulb.dndmanager.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.paulb.dndmanager.model.SessionLog;

@Repository
public interface SessionLogRepository extends JpaRepository<SessionLog, Long> {
    Page<SessionLog> findByCampaignId(Long campaignId, Pageable pageable);

}
