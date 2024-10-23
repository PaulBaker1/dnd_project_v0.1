package pl.paulb.dndmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.paulb.dndmanager.model.Player;
import pl.paulb.dndmanager.service.LevelingService;
import pl.paulb.dndmanager.service.SpellService;
import pl.paulb.dndmanager.service.TransactionService;

import java.util.Optional;

@RestController
@RequestMapping("/api/players")
public class PlayerController {
    @Autowired
    private SpellService spellService;

    @Autowired
    private LevelingService levelingService;

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/{playerId}/spells/reset")
    public ResponseEntity<Player> resetSpell(@PathVariable("playerId") Long playerId) {
        Optional<Player> player = spellService.resetSpellsForPlayer(Math.toIntExact(playerId));
        return player.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{playerId}/spells/{spellId}/use")
    public ResponseEntity<Player> useSpell(@PathVariable("playerId") Long playerId, @PathVariable("spellId") int spellId) {
        Optional<Player> player = spellService.useSpell(playerId, spellId);
        return player.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{playerId}/experience")
    public ResponseEntity<Player> experience(@PathVariable Long playerId, @RequestParam Integer xp) {
        Optional<Player> player = levelingService.addExperience(playerId, xp);
        return player.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404).build());
    }

    @PostMapping("/{playerId}/putchase/{itemId}")
    public ResponseEntity<Player> putchase(@PathVariable Long playerId, @PathVariable Long itemId) {
        Optional<Player> player = transactionService.purchaseItem(playerId, itemId);
        return player.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(400).build());
    }

    @PostMapping("/{playerId}/sell/{itemId}")
    public ResponseEntity<Player> sell(@PathVariable Long playerId, @PathVariable Long itemId) {
        Optional<Player> player = transactionService.sellItem(playerId, itemId);
        return player.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(400).build());
    }
}
