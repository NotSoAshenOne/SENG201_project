package seng201.team0.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import seng201.team0.models.Inventory;
import seng201.team0.models.Settings;
import seng201.team0.models.Tower;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Controls the game shop.
 * Allows the player to purchase the remaining towers as well as towers that have broken.
 * Also lets the player purchase any of the upgrades and level ups.
 */
public class GameShopController implements Initializable {
    // ---------- INITIALISATION ---------- //

    // Initialises the inventory instance.
    private Inventory inventory = Inventory.getInstance();
    // Initialises the SceneController.
    private SceneController sceneController =  SceneController.getInstance();
    // Initialises the towers.
    ArrayList<Tower> towerList = Settings.getTowers();
    Tower waterTower = towerList.get(0);
    Tower fireTower = towerList.get(1);
    Tower earthTower = towerList.get(2);
    Tower airTower = towerList.get(3);
    Tower eggTower = towerList.get(4);

    // Injecting all of the JavaFX elements.
    @FXML
    private Button shopExitButton;
    @FXML
    private Button buyWaterButton;
    @FXML
    private Button buyFireButton;
    @FXML
    private Button buyEggButton;
    @FXML
    private Button buyEarthButton;
    @FXML
    private Button buyAirButton;
    @FXML
    private Button waterUpButton;
    @FXML
    private Button fireUpButton;
    @FXML
    private Button eggUpButton;
    @FXML
    private Button buyEarthUp;
    @FXML
    private Button buyAirUp;
    @FXML
    private Button waterLevelButton;
    @FXML
    private Button fireLevelButton;
    @FXML
    private Button eggLevelButton;
    @FXML
    private Button earthLevelButton;
    @FXML
    private Button airLevelButton;
    @FXML
    private ListView<String> shopWater;
    @FXML
    private ListView<String> shopFire;
    @FXML
    private ListView<String> shopEgg;
    @FXML
    private ListView<String> shopEarth;
    @FXML
    private ListView<String> shopAir;
    @FXML
    private ListView<String> waterUp;
    @FXML
    private ListView<String> fireUp;
    @FXML
    private ListView<String> eggUp;
    @FXML
    private ListView<String> earthUp;
    @FXML
    private ListView<String> airUp;
    @FXML
    private ListView<String> waterLevel;
    @FXML
    private ListView<String> fireLevel;
    @FXML
    private ListView<String> eggLevel;
    @FXML
    private ListView<String> earthLevel;
    @FXML
    private ListView<String> airLevel;

    /**
     * Sets the initial state of the shop.
     * Checks what towers are in the shop and then if they are, displays their stats and makes them purchasable.
     * Displays the upgrades that the player can buy.
     * @param url
     * @param resourceBundle the resources for the current scene.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Checks if the towers are in the shop.
        if(waterTower.isInShop()){shopWater.getItems().addAll(waterTower.getAttributes());buyWaterButton.setDisable(false);}
        if(fireTower.isInShop()){shopFire.getItems().addAll(fireTower.getAttributes());buyFireButton.setDisable(false);}
        if(eggTower.isInShop()){shopEgg.getItems().addAll(eggTower.getAttributes());buyEggButton.setDisable(false);}
        if(earthTower.isInShop()){shopEarth.getItems().addAll((earthTower.getAttributes()));buyEarthButton.setDisable(false);}
        if(airTower.isInShop()){shopAir.getItems().addAll(airTower.getAttributes());buyAirButton.setDisable(false);}
        // Displays the upgrade items.
        waterUp.getItems().addAll(new String[] {"Hose", "+5 Water", "Cost: 10 Coins"});
        fireUp.getItems().addAll((new String[] {"Lighter", "+3 Fire", "Cost: 8 Coins"}));
        eggUp.getItems().addAll(new String[] {"Chicken", "+4 Eggs", "Cost: 15 Coins"});
        earthUp.getItems().addAll((new String[] {"Bag of Soil", "+5 Earth", "Cost: 12 Coins"}));
        airUp.getItems().addAll(new String[] {"Aircon", "+5 Air", "Cost: 12 Coins"});
        // Displays the level up items.
        waterLevel.getItems().addAll(new String[] {"Water Tank","+1 Water Level","Cost: 40 Coins"});
        fireLevel.getItems().addAll(new String[]{"Log", "+1 Fire Level","Cost: 45 Coins"});
        eggLevel.getItems().addAll(new String[]{"Free Range Pen", "+1 Egg Level", "Cost: 60 Coins"});
        earthLevel.getItems().addAll(new String[]{"Mountain","+1 Earth Level","Cost: 45 Coins"});
        airLevel.getItems().addAll(new String[]{"Wind Turbine","+1 Wind Level","Cost: 50 Coins"});

    }

    // ---------- BUTTON FUNCTIONS ---------- //
    /**
     * Switches the screen from the shop to the game round.
     * @param event the exit button is pressed.
     * @throws IOException if there is an error loading the fxml file.
     */
    @FXML
    public void shopExitPressed(ActionEvent event) throws IOException {
        sceneController.SwitchToRound(event);
    }
    // -------- BUY TOWERS -------- //
    /**
     * Allows the player to buy the water tower.
     * Places the tower in their reserves and then removes the tower from the display
     * and disables the buy button.
     */
    public void buyWater(){
        waterTower.putInReserve();
        shopWater.getItems().clear();
        buyWaterButton.setDisable(true);
    }
    /**
     * Allows the player to buy the fire tower.
     * Places the tower in their reserves and then removes the tower from the display
     * and disables the buy button.
     */
    public void buyFire(){
        fireTower.putInReserve();
        shopFire.getItems().clear();
        buyFireButton.setDisable(true);
    }
    /**
     * Allows the player to buy the egg tower.
     * Places the tower in their reserves and then removes the tower from the display
     * and disables the buy button.
     */
    public void buyEgg(){
        eggTower.putInReserve();
        shopEgg.getItems().clear();
        buyEggButton.setDisable(true);
    }
    /**
     * Allows the player to buy the earth tower.
     * Places the tower in their reserves and then removes the tower from the display
     * and disables the buy button.
     */
    public void buyEarth(){
        earthTower.putInReserve();
        shopEarth.getItems().clear();
        buyEarthButton.setDisable(true);
    }

    /**
     * Allows the player to buy the air tower.
     * Places the tower in their reserves and then removes the tower from the display
     * and disables the buy button.
     */
    public void buyAir(){
        airTower.putInReserve();
        shopAir.getItems().clear();
        buyAirButton.setDisable(true);
    }

    // -------- BUY UPGRADES -------- //
    /**
     * Allows the player to buy the water upgrade when they press the water upgrade button.
     * Runs the inventory method
     */
    public void buyWaterUp(){
        inventory.buyHose();
    }

    /**
     * Allows the player to buy the fire upgrade when they press the fire upgrade button.
     * Runs the inventory method
     */
    public void buyFireUp(){
        inventory.buyLighter();
    }

    /**
     * Allows the player to buy the egg upgrade when they press the egg upgrade button.
     * Runs the inventory method
     */
    public void buyEggUp(){
        inventory.buyChicken();
    }

    /**
     * Allows the player to buy the earth upgrade when they press the earth upgrade button.
     * Runs the inventory method
     */
    public void buyEarthUp(){
        inventory.buyBagOfSoil();
    }

    /**
     * Allows the player to buy the air upgrade when they press the air upgrade button.
     * Runs the inventory method
     */
    public void buyAirUp(){
        inventory.buyAirCon();
    }

    // -------- BUY LEVELS -------- //
    /**
     * Allows the player to buy the water level up when they press the water level up button.
     * Runs the inventory method
     */
    public void buyWaterLevel(){ inventory.buyWaterTank();}

    /**
     * Allows the player to buy the fire level up when they press the fire level up button.
     * Runs the inventory method
     */
    public void buyFireLevel(){inventory.buyLog();}

    /**
     * Allows the player to buy the egg level up when they press the egg level up button.
     * Runs the inventory method
     */
    public void buyEggLevel(){Inventory.buyFreeRangePen();}

    /**
     * Allows the player to buy the earth level up when they press the earth level up button.
     * Runs the inventory method
     */
    public void buyEarthLevel(){inventory.buyMountain();}

    /**
     * Allows the player to buy the air level up when they press the air level up button.
     * Runs the inventory method
     */
    public void buyAirLevel(){inventory.buyWindTurbine();}
}
