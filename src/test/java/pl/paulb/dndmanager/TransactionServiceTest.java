package pl.paulb.dndmanager;

// TransactionServiceTest.java (Test Class)


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.paulb.dndmanager.model.Currency;
import pl.paulb.dndmanager.model.Item;
import pl.paulb.dndmanager.model.Player;
import pl.paulb.dndmanager.repository.ItemRepository;
import pl.paulb.dndmanager.repository.PlayerRepository;
import pl.paulb.dndmanager.service.TransactionService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransactionServiceTest {

    @Mock
    private PlayerRepository playerRepository;

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private TransactionService transactionService;

    private Player player;
    private Item item;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Create mock player with initial wallet setup
        player = new Player();
        player.setId(1L);
        player.setWallet(new Currency(1000, 50, 10, 5, 0));  // 1000 copper, 50 silver, etc.

        // Create mock item with a price
        item = new Item();
        item.setId(1L);
        item.setPrice(new Currency(500, 0, 0, 0, 0));  // Item costs 500 copper
    }

    @Test
    void testPurchaseItem_Success() {
        when(playerRepository.findById(1)).thenReturn(Optional.of(player));
        when(itemRepository.findById(1)).thenReturn(Optional.of(item));

        Optional<Player> result = transactionService.purchaseItem(1L, 1L);

        assertTrue(result.isPresent());
        assertEquals(500, player.getWallet().getCopperCoins());  // Should deduct 500 copper
        verify(playerRepository, times(1)).save(player);  // Ensure player was saved
    }

    @Test
    void testPurchaseItem_Failure_InsufficientFunds() {
        // Set the item price higher than player's total wealth
        item.setPrice(new Currency(2000, 0, 0, 0, 0));  // Item costs 2000 copper

        when(playerRepository.findById(1)).thenReturn(Optional.of(player));
        when(itemRepository.findById(1)).thenReturn(Optional.of(item));

        Optional<Player> result = transactionService.purchaseItem(1L, 1L);

        assertFalse(result.isPresent());  // Should fail because player can't afford the item
        verify(playerRepository, times(0)).save(player);  // Player shouldn't be saved
    }
}
