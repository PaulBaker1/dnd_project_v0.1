package pl.paulb.dndmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.paulb.dndmanager.model.Player;
import pl.paulb.dndmanager.model.Spell;
import pl.paulb.dndmanager.repository.PlayerRepository;

import java.util.Objects;
import java.util.Optional;

@Service
public class SpellService {

    @Autowired
    private PlayerRepository playerRepository;

    public Optional<Player> resetSpellsForPlayer(Integer playerId) {
        Optional<Player> player = playerRepository.findById(playerId);
        player.ifPresent(p -> {
            for (Spell spell : p.getSpells()) {
                spell.setCurrentUses(0);  // Reset spell usage for the day
            }
            playerRepository.save(p);
        });
        return player;
    }

    public Optional<Player> useSpell(Long playerId, Integer spellId) {
       Optional<Player> player = playerRepository.findById(Math.toIntExact(playerId));
       if (player.isPresent()) {
           Player p = player.get();
           Optional<Spell> spell = p.getSpells().stream().filter(s -> Objects.equals(s.getId(), spellId)).findFirst();
           if (spell.isPresent()) {
               Spell s = spell.get();
               if (s.getCurrentUses() < s.getMaxUsesPerDay()) {
                   s.setCurrentUses(s.getCurrentUses() + 1);
                   playerRepository.save(p);
                   return Optional.of(p);
               } else {
                   return Optional.empty();
               }
           }
       }
       return Optional.empty();
    }
}
