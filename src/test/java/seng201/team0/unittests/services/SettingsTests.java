package seng201.team0.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.models.Settings;
import seng201.team0.models.Tower;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static seng201.team0.models.RESOURCES.*;

class SettingsTests {
    private Settings settings;

    @BeforeEach
    void setUp() {
        settings = Settings.getInstance();
    }

    @Test
    void testAddTowers() {
        ArrayList<Tower> towers = Settings.getTowers();
        assertEquals(5, towers.size(), "There should be 5 towers in the list");
        assertEquals(WATER, towers.get(0).resourceType, "First tower should be of type WATER");
        assertEquals(FIRE, towers.get(1).resourceType, "Second tower should be of type FIRE");
        assertEquals(EARTH, towers.get(2).resourceType, "Third tower should be of type EARTH");
        assertEquals(AIR, towers.get(3).resourceType, "Fourth tower should be of type AIR");
        assertEquals(EGGS, towers.get(4).resourceType, "Fifth tower should be of type EGGS");
    }

    @Test
    void testSetName() {
        Settings.setName("ValidName");
        assertEquals("ValidName", settings.getName(), "Username should be 'ValidName'");

        Settings.setName("Invalid@Name");
        assertNotEquals("Invalid@Name", settings.getName(), "Username should not contain special characters");

        Settings.setName("ab");
        assertNotEquals("ab", settings.getName(), "Username should not be less than 3 characters");

        Settings.setName("aVeryLongUsernameThatExceedsLimit");
        assertNotEquals("aVeryLongUsernameThatExceedsLimit", settings.getName(), "Username should not exceed 15 characters");
    }
    @Test
    void testSetTowers() {
        Settings.setTowers(WATER);
        Tower waterTower = Settings.getTowers().get(0);
        assertEquals(1, waterTower.getLevel(), "Water tower level should be 1");
        assertEquals(1, waterTower.getReloadSpeed(), "Water tower reload speed should be 1");
        assertEquals(5, waterTower.getResourceAmount(), "Water tower resource amount should be 5");
        assertTrue(waterTower.isInCurrent(), "Water tower should be in current");

        Settings.setTowers(FIRE);
        Tower fireTower = Settings.getTowers().get(1);
        assertEquals(1, fireTower.getLevel(), "Fire tower level should be 1");
        assertEquals(1, fireTower.getReloadSpeed(), "Fire tower reload speed should be 1");
        assertEquals(5, fireTower.getResourceAmount(), "Fire tower resource amount should be 5");
        assertTrue(fireTower.isInCurrent(), "Fire tower should be in current");
    }
}
