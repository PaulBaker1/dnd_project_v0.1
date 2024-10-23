package pl.paulb.dndmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.paulb.dndmanager.model.Player;
import pl.paulb.dndmanager.repository.PlayerRepository;

import java.util.Optional;

@Service
public class LevelingService {

    @Autowired
    private PlayerRepository playerRepository;

    private static final Integer[] XP_THRESHOLDS = {0, 300, 900, 2700, 6500, 14000, 23000, 34000, 48000, 64000,
            85000, 100000, 120000, 140000, 165000, 195000, 225000, 265000,
            305000, 355000};  // Example thresholds

    // Method to add experience points to a player and check for level-up
    public Optional<Player> addExperience(Long playerId, int xpToAdd) {
        Optional<Player> player = playerRepository.findById(Math.toIntExact(playerId));
        if (player.isPresent()) {
            Player p = player.get();
            int currentXP = p.getExperience() + xpToAdd;
            p.setExperience(currentXP);

            // Check if the player needs to level up
            for (int level = 20; level > p.getLevel(); level--) {
                if (currentXP >= XP_THRESHOLDS[level - 1]) {
                    p.levelUp(level);
                    break;
                }
            }

            playerRepository.save(p);
            return Optional.of(p);
        }
        return Optional.empty();
    }
}
