package pl.paulb.dndmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.paulb.dndmanager.model.Player;

public interface PlayerRepository extends JpaRepository<Player, Integer> {

}
