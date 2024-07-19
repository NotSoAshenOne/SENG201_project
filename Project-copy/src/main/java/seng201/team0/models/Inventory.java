package seng201.team0.models;

import javafx.scene.control.Alert;

import java.util.ArrayList;

/**
 * Class representing the player's inventory and functionality within shop.
 * It handles the towers and provides methods to purchase power-ups and level-up items for towers.
 */

public class Inventory {
    // ---------- INITIALISATION ---------- //
    private static Inventory inventory_instance = null;
    public static synchronized Inventory getInstance(){
        if (inventory_instance == null){
            inventory_instance = new Inventory();
        }
        return inventory_instance;
    }
    private MainFunction main = MainFunction.getInstance();
    private Settings settings = Settings.getInstance();
    static ArrayList<Tower> towerList = Settings.getTowers();
    static Tower waterTower = towerList.get(0);
    static Tower fireTower = towerList.get(1);
    static Tower earthTower = towerList.get(2);
    static Tower airTower = towerList.get(3);
    static Tower eggTower = towerList.get(4);
    public static int coins;


    /**
     * Sets the number of coins.
     * @param tempCoins the number of coins to be added or deducted.
     */
    public static void setCoins(int tempCoins){
        coins += tempCoins;
    }

    /**
     * Gets the number of coins.
     * @return the number of coins.
     */
    public static int getCoins(){
        return coins;
    }


    // ---------- RESOURCE BOOST POWERUPS ---------- //

    /**
     * Buys a Hose power-up for the Water Tower, increasing its resource amount by 5 and costing 10 coins.
     */
    public void buyHose(){
        // Buys Hose Powerup: +5 water and costs 10 coins
        if (waterTower.inCurrent) {
            waterTower.resourceAmount += 5;
            setCoins(-10);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Your Water Tower gained 5 water!");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Your Water Tower isn't currently active");
            alert.show();
        }
    }

    /**
     * Buys a Lighter power-up for the Fire Tower, increasing its resource amount by 3 and costing 8 coins.
     */
    public void buyLighter(){
        // Buys Lighter Powerup: +3 fire and costs 8 coins
        if (fireTower.inCurrent) {
            fireTower.resourceAmount += 3;
            coins -= 8;
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Your Fire Tower gained 3 fires!");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Your Fire Tower isn't currently active");
            alert.show();
        }
    }

    /**
     * Buys an AirCon power-up for the Air Tower, increasing its resource amount by 5 and costing 12 coins.
     */
    public void buyAirCon(){
        // Buys AirCon Powerup: +5 air and costs 12 coins
        if (airTower.inCurrent) {
            airTower.resourceAmount += 5;
            coins -= 12;
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Your Air Tower gained 5 air!");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Your Air Tower isn't currently active");
            alert.show();
        }
    }

    /**
     * Buys a Bag of Soil power-up for the Earth Tower, increasing its resource amount by 5 and costing 12 coins.
     */
    public void buyBagOfSoil(){
        // Buys Bag of Soil Powerup: +5 earth and costs 12 coins
        if (earthTower.inCurrent) {
            earthTower.resourceAmount += 5;
            coins -= 12;
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Your Earth Tower gained 5 earth!");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Your Earth Tower isn't currently active");
            alert.show();
        }
    }

    /**
     * Buys a Chicken power-up for the Egg Tower, increasing its resource amount by 4 and costing 15 coins.
     */
    public static void buyChicken(){
        // Buys Chicken Powerup: +4 eggs and costs 15 coins
        if (earthTower.inCurrent) {
            eggTower.resourceAmount += 4;
            coins -= 15;
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Your Egg Tower gained 4 eggs!");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Your Egg Tower isn't currently active");
            alert.show();
        }
    }

    // ---------- LEVEL-UP POWERUPS ---------- //

    /**
     * Buys a Water Tank to level up the Water Tower, increasing its level, reload speed, and resource amount, and costing 40 coins.
     */
    public void buyWaterTank(){
        //Buys Water Tank: levels up water tower and costs 40
        if (waterTower.inCurrent) {
            waterTower.levelUp();
            coins -= 40;
            displayLevelUp();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Your Water Tower isn't currently active");
            alert.show();
        }
    }

    /**
     * Buys a Log to level up the Fire Tower, increasing its level, reload speed, and resource amount, and costing 45 coins.
     */
    public void buyLog(){
        //Buys Log: levels up fire tower and costs 45
        if (fireTower.inCurrent) {
            fireTower.levelUp();
            coins -= 45;
            displayLevelUp();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Your Fire Tower isn't currently active");
            alert.show();
        }
    }

    /**
     * Buys a Wind Turbine to level up the Air Tower, increasing its level, reload speed, and resource amount, and costing 50 coins.
     */
    public void buyWindTurbine(){
        //Buys Wind Turbine: levels up air tower and costs 50
        if (airTower.inCurrent) {
            airTower.levelUp();
            coins -= 50;
            displayLevelUp();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Your Air Tower isn't currently active");
            alert.show();
        }
    }

    /**
     * Buys a Mountain to level up the Earth Tower, increasing its level, reload speed, and resource amount, and costing 45 coins.
     */
    public void buyMountain(){
        //Buys Mountain: levels up earth tower and costs 45
        if (earthTower.inCurrent) {
            earthTower.levelUp();
            coins -= 45;
            displayLevelUp();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Your Earth Tower isn't currently active");
            alert.show();
        }
    }

    /**
     * Buys a Free Range Pen to level up the Egg Tower, increasing its level, reload speed, and resource amount, and costing 60 coins.
     */
    public static void buyFreeRangePen(){
        //Buys Free Range Pen: levels up egg tower and costs 60
        if (eggTower.inCurrent) {
            eggTower.levelUp();
            coins =- 60;
            displayLevelUp();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Your Egg Tower isn't currently active");
            alert.show();
        }
    }

    // ---------- HELPER METHODS ---------- //

    /**
     * Pops an alert that describes the level up to the player.
     */
    public static void displayLevelUp(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Your tower leveled up! +3 Reload Speed, +5 Resource Amount, +$5 Tower Value!");
        alert.show();
    }
}
