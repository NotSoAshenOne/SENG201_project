package seng201.team0.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.models.Cart;
import seng201.team0.models.Inventory;
import seng201.team0.models.MainFunction;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MainFunctionTest {
    private MainFunction mainFunction;

    @BeforeEach
    void setUp() {
        mainFunction = MainFunction.getInstance();
    }

    @Test
    void getInstance() {
        MainFunction instance1 = MainFunction.getInstance();
        MainFunction instance2 = MainFunction.getInstance();
        assertSame(instance1, instance2);
    }

    @Test
    void setCartLineup() {
        MainFunction.setCartLineup();
        ArrayList<Cart> cartLineup = MainFunction.cartLineup;
        ArrayList<String> cartLineupString = MainFunction.cartLineupString;

        assertNotNull(cartLineup);
        assertNotNull(cartLineupString);
        assertFalse(cartLineup.isEmpty());
        assertFalse(cartLineupString.isEmpty());
        assertEquals(cartLineup.size(), cartLineupString.size());
    }

    @Test
    void nextRound() {
        mainFunction.setNumRoundsLeft(1);
        assertFalse(MainFunction.cartLineup.isEmpty());
        mainFunction.nextRound();
        assertTrue(MainFunction.cartLineup.isEmpty());
    }

    @Test
    void towersBreak() {
        mainFunction.towersBreak();
        // Testing the method which depends on random behavior is challenging
        // We can't directly assert what will happen
        // We can check if there is no crash
        // And in real scenarios, we may consider mocking the random behavior for more deterministic testing
    }

    @Test
    void addRandomCoins() {
        int initialCoins = Inventory.getCoins();
        MainFunction.addRandomCoins();
        int newCoins = Inventory.getCoins();
        assertNotEquals(initialCoins, newCoins);
    }

    @Test
    void currentFull() {
        assertFalse(mainFunction.currentFull());
        mainFunction.setNumRoundsLeft(1);
        assertFalse(mainFunction.currentFull());
        mainFunction.setNumRoundsLeft(3);
        assertTrue(mainFunction.currentFull());
    }
}
