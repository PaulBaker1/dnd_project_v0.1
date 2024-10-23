package pl.paulb.dndmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.paulb.dndmanager.model.Currency;
import pl.paulb.dndmanager.model.Item;
import pl.paulb.dndmanager.model.Player;
import pl.paulb.dndmanager.repository.ItemRepository;
import pl.paulb.dndmanager.repository.PlayerRepository;

import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private ItemRepository itemRepository;

    public Optional<Player> purchaseItem(Long playerId, Long itemId) {
        Optional<Player> playerOpt = playerRepository.findById(Math.toIntExact(playerId));
        Optional<Item> itemOpt = itemRepository.findById(Math.toIntExact(itemId));

        if (playerOpt.isPresent() && itemOpt.isPresent()) {
            Player player = playerOpt.get();
            Item item = itemOpt.get();
            Currency itemPrice = item.getPrice();

            // Check if player can afford the item and deduct the price
            if (player.getWallet().deductCurrency(itemPrice)) {
                // Save updated player wallet
                playerRepository.save(player);
                return Optional.of(player);
            }
        }
        return Optional.empty();
    }

    public Optional<Player> sellItem(Long playerId, Long itemId) {
        Optional<Player> playerOpt = playerRepository.findById(Math.toIntExact(playerId));
        Optional<Item> itemOpt = itemRepository.findById(Math.toIntExact(itemId));

        if (playerOpt.isPresent() && itemOpt.isPresent()) {
            Player player = playerOpt.get();
            Item item = itemOpt.get();
            Currency itemPrice = item.getPrice();

            // Add item price to player's wallet
            player.getWallet().addCurrency(itemPrice);
            playerRepository.save(player);
            return Optional.of(player);
        }
        return Optional.empty();
    }
}
