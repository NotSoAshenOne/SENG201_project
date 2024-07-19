package seng201.team0.models;

import javafx.scene.control.Alert;

import java.util.ArrayList;
import java.util.Random;

import static seng201.team0.models.RESOURCES.*;

/**
 * The MainFunction class represents the core logic of the game, including the movement of carts,
 * towers levels, rounds, and game settings. It follows a singleton pattern to ensure only one instance is used
 * throughout the game.
 */

public class MainFunction {
    // ---------- INTIALISATION ---------- //
    private static MainFunction main_instance = null;
    public static synchronized MainFunction getInstance(){
        if (main_instance == null){
            main_instance = new MainFunction();
        }
        return main_instance;
    }
    private Settings settings = Settings.getInstance();
    public static ArrayList cartLineup = new ArrayList();
    public static ArrayList cartLineupString = new ArrayList();
    static RESOURCES[] resources = RESOURCES.values();
    public static int difficulty;
    private int numRoundsLeft;
    //static int coins = Settings.getCoins();

    ArrayList<Tower> towerList = settings.getTowers();
    Tower waterTower = towerList.get(0);
    Tower fireTower = towerList.get(1);
    Tower earthTower = towerList.get(2);
    Tower airTower = towerList.get(3);
    Tower eggTower = towerList.get(4);

    // ---------- CART MOVEMENT METHODS ---------- //
    /** Sets the lineup of carts for each round based on the difficulty level.*/
    public static void setCartLineup(){
        // Sets the line up of carts for each round
        difficulty = 1;
        int numCarts = 0;
        int maxSize = 0;
        int maxSpeed = 0;

        // Changes the number of carts, size of carts and speed of carts based on the difficulty level selected
        switch (difficulty) {
            case 1:
                numCarts = 3;
                maxSize = 5;
                maxSpeed = 2;
                break;
            case 2:
                numCarts = 5;
                maxSize = 8;
                maxSpeed = 5;
                break;
            case 3:
                numCarts = 10;
                maxSize = 10;
                maxSpeed = 10;
                break;
        }

        Random random = new Random();
        // Randomly selects a resource for each cart and values within ranges set above
        for (int i = 0; i < numCarts; i++) {
            int randomIndex = random.nextInt(resources.length);
            RESOURCES randomResource = resources[randomIndex];
            int size = random.nextInt(maxSize) + 1;
            int speed = random.nextInt(maxSpeed) + 1;
            Cart cart = new Cart(size, randomResource, speed);
            cartLineup.add(cart);
            cartLineupString.add(cart.getCartString());
        }
    }

    /** Runs the next cart in the lineup through the towers, filling it with resources. */
    public void nextCart(){
        // Runs the next cart in line past the towers, filling up as it goes
        // Runs on the push of the "go" button
        Cart cart = (Cart) cartLineup.get(0);
        cartLineup.remove(0);
        cartLineupString.remove(0);

        RESOURCES type = cart.resourceType;
        int size = cart.size;
        int speed = cart.speed;
        int time = 0; //number of seconds passed as cart moves through
        int points = size * 4; //arbitrary number 4 coin multiplier

        // Towers Reload: Increasing the level of each tower according to cart speed
        time = 10/speed;
        waterTower.resourceAmount += waterTower.reloadSpeed * time;
        time = 20/speed;
        fireTower.resourceAmount += fireTower.reloadSpeed * time;
        time = 30/speed;
        earthTower.resourceAmount += earthTower.reloadSpeed * time;
        time = 40/speed;
        airTower.resourceAmount += airTower.reloadSpeed * time;
        time = 50/speed;
        eggTower.resourceAmount += eggTower.reloadSpeed * time;

        // Fills the carts: Lowers the level of the corresponding tower and updating the space left in cart
        if (type == WATER && waterTower.inCurrent){
            if (waterTower.resourceAmount <= size){
                size -= waterTower.resourceAmount;
                waterTower.resourceAmount = 0;
            } else{
                waterTower.resourceAmount -= size;
                size = 0;
            }
        } else if (type == FIRE && fireTower.inCurrent){
            if (fireTower.resourceAmount <= size){
                size -= fireTower.resourceAmount;
                fireTower.resourceAmount = 0;
            } else{
                fireTower.resourceAmount -= size;
                size = 0;
            }
        } else if (type == EARTH && earthTower.inCurrent) {
            if (earthTower.resourceAmount <= size){
                size -= earthTower.resourceAmount;
                earthTower.resourceAmount = 0;
            } else{
                earthTower.resourceAmount -= size;
                size = 0;
            }
        } else if (type == AIR && airTower.inCurrent) {
            if (airTower.resourceAmount <= size){
                size -= airTower.resourceAmount;
                airTower.resourceAmount = 0;
            } else{
                airTower.resourceAmount -= size;
                size = 0;
            }
        } else if (type == EGGS && eggTower.inCurrent) {
            if (eggTower.resourceAmount <= size){
                size -= eggTower.resourceAmount;
                eggTower.resourceAmount = 0;
            } else{
                eggTower.resourceAmount -= size;
                size = 0;
            }
        }

        // Adds coins for successful carts through
        if (size == 0){
            Inventory.setCoins(points);
        }

        cartThrough();
    }

    /** Checks if the round is over and proceeds to the next round if necessary. */
    public void cartThrough(){
        // Checks whether a round is over
        boolean roundDone = (cartLineup.isEmpty());

        if (roundDone){
            nextRound();
        }
    }

    // ---------- NEW ROUND METHODS ---------- //{
    /** Proceeds to the next round, resetting the cart lineup and updating the number of rounds left. */
    public void nextRound(){
        // Checks whether game is over and resets cartlineup
        numRoundsLeft -= 1;
        towersBreak(); // if not then a random amount of coins will be added
        setCartLineup();

    }

    /**Decides whether a random tower will break and handles the consequences. */
    public void towersBreak(){
        // Decides whether a random tower will break

        // Random tower selection:
        Random randomiser = new Random();
        int randomIndex = randomiser.nextInt(towerList.size());
        Tower randomTower = towerList.get(randomIndex);

        // Tower has a 50% chance of breaking:
        boolean breaks = Math.random() < 0.5;
        System.out.println(breaks);

        if (breaks && randomTower.inCurrent){
            randomTower.putInShop(false);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Oh no! One of your towers broke!");
            alert.show();
        } else{
            addRandomCoins();
        }
    }

    /** Adds or subtracts a random number of coins between -10 and 20 to/from the player's inventory */
    public static void addRandomCoins(){
        // Adds or subtracts a random number of coins between -10 and 20

        // Generates a random number of coins between -10 and 20
        Random randomiser = new Random();
        int randomCoins = randomiser.nextInt(31) - 10;

        // Adds/subtracts to coins value
        randomCoins += Inventory.getCoins();
        Inventory.setCoins(randomCoins);

        // Displays a message based on what happened (or nothing if value was 0)
        if (randomCoins > 0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(randomCoins + " coins randomly fell into your pocket! Nice!");
            alert.show();
        } else if (randomCoins < 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Oh no! A wizard stole " + randomCoins + " of your coins!");
            alert.show();
        }
    }

    /** Starts a new game by resetting the towers and coins. */
    public void newGame(){
        // Resets everything
        waterTower.putInShop(false);
        fireTower.putInShop(false);
        airTower.putInShop(false);
        earthTower.putInShop(false);
        eggTower.putInShop(false);
        Inventory.setCoins(-(Inventory.getCoins()));
        cartLineupString.clear();
        cartLineup.clear();

    }

    // ---------- OTHER FUNCTIONAL METHODS ---------- //
    /**
     * Checks whether there are only 3 towers currently in play
     * @return true if there are 3 or more towers in play, false otherwise
     */
    public boolean currentFull(){
        // Checks that there are only 3 towers currently in play
        int current = 0;

        for(Tower tower : towerList){
            if (tower.inCurrent){
                System.out.println(tower.resourceTypeString);
                current += 1;
            }
        }
        return (current >= 3);
        //return gameFull;
    }

    /**
     * Sets the number of rounds left in the game.
     * @param tempRounds the number of rounds left
     */
    public void setNumRoundsLeft(int tempRounds){
        numRoundsLeft = tempRounds;
    }

    /**
     * Sets the difficulty level of the game.
     * @param tempDifficulty the difficulty level to set
     */
    public void setDifficulty(int tempDifficulty){
        difficulty = tempDifficulty;
    }

    /**
     * Gets the number of rounds left in the game
     * @return the number of rounds left
     */
    public int getNumRoundsLeft(){
        return numRoundsLeft - 1;
    }

}

