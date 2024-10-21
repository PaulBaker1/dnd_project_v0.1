package pl.paulb.dndmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.paulb.dndmanager.model.Campaign;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {
}
