package seng201.team0.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import seng201.team0.models.Inventory;
import seng201.team0.models.Tower;

public class InventoryTests {
    Tower waterTower;
    Tower fireTower;
    Tower airTower;
    Tower earthTower;
    Tower eggTower;

    @BeforeEach
    public void setUp() {
        waterTower = new Tower();
        fireTower = new Tower();
        airTower = new Tower();
        earthTower = new Tower();
        eggTower = new Tower();
        waterTower.putInCurrent();
        fireTower.putInCurrent();
        airTower.putInCurrent();
        earthTower.putInCurrent();
        eggTower.putInCurrent();
        Inventory.setCoins(100);
    }

}
