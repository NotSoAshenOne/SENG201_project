package seng201.team0.unittests.services;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.models.Tower;
import static org.junit.jupiter.api.Assertions.*;
import static seng201.team0.models.RESOURCES.EARTH;
import static seng201.team0.models.RESOURCES.WATER;

public class TowerTests {
    Tower defaultTower;
    Tower customTower;

    @BeforeEach
    void setUp() {
        defaultTower = new Tower();
        customTower = new Tower(10, 7, EARTH, 3, 20, true, false, false);
    }

    @Test
    public void testDefaultConstructor() {
        assertEquals(0, defaultTower.getResourceAmount());
        assertEquals(5, defaultTower.getReloadSpeed());
        assertEquals(WATER, defaultTower.resourceType);
        assertEquals(1, defaultTower.getLevel());
        assertEquals(10, defaultTower.getCost());
        assertFalse(defaultTower.isInCurrent());
        assertFalse(defaultTower.isInReserve());
        assertFalse(defaultTower.isInShop());
    }

    @Test
    void testConstructorAllocations() {
        assertEquals(10, customTower.getResourceAmount());
        assertEquals(7, customTower.getReloadSpeed());
        assertEquals(EARTH, customTower.resourceType);
        assertEquals(3, customTower.getLevel());
        assertEquals(20, customTower.getCost());
        assertTrue(customTower.isInCurrent());
        assertFalse(customTower.isInReserve());
        assertFalse(customTower.isInShop());
    }
    @Test
    public void testLevelUp() {
        defaultTower.levelUp();
        assertEquals(2, defaultTower.getLevel());
        assertEquals(8, defaultTower.getReloadSpeed());
        assertEquals(5, defaultTower.getResourceAmount());
        assertEquals(15, defaultTower.getCost());
    }
    @Test
    void testPutInCurrent() {
        defaultTower.putInCurrent();
        assertTrue(defaultTower.isInCurrent());
        assertFalse(defaultTower.isInReserve());
        assertFalse(defaultTower.isInShop());
    }
    @Test
    void testPutInReserve() {
        defaultTower.putInReserve();
        assertFalse(defaultTower.isInCurrent());
        assertTrue(defaultTower.isInReserve());
        assertFalse(defaultTower.isInShop());
    }
    @Test
    void testPutInShop() {
        defaultTower.putInShop(true);
        assertFalse(defaultTower.isInCurrent());
        assertFalse(defaultTower.isInReserve());
        assertTrue(defaultTower.isInShop());
        assertEquals(5, defaultTower.getResourceAmount());
        assertEquals(5, defaultTower.getReloadSpeed());
        assertEquals(1, defaultTower.getLevel());
        assertEquals(10, defaultTower.getCost());
    }

}
