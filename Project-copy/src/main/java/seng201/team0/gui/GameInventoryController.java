package seng201.team0.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import seng201.team0.models.Settings;
import seng201.team0.models.Tower;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Controls the players inventory.
 * Displays listViews for the active and reserved towers, only showing them if the tower is
 * in that give category. I.e. if a tower is reserved then it will be visible in the reserved
 * list but not the active list.
 * Also allows the player to sell their towers from the reserved list.
 */
public class GameInventoryController implements Initializable {
    // ---------- INITIALISATION ---------- //
    // Initialises all the towers.
    ArrayList<Tower> towerList = Settings.getTowers();
    Tower waterTower = towerList.get(0);
    Tower fireTower = towerList.get(1);
    Tower earthTower = towerList.get(2);
    Tower airTower = towerList.get(3);
    Tower eggTower = towerList.get(4);

    // Injects all the JavaFXML elements.
    @FXML
    private Button inventoryExitButton;
    @FXML
    private Button moveWaterButton;
    @FXML
    private Button moveFireButton;
    @FXML
    private Button moveEggButton;
    @FXML
    private Button moveEarthButton;
    @FXML
    private Button moveAirButton;
    @FXML
    private Button sellWaterButton;
    @FXML
    private Button sellFireButton;
    @FXML
    private Button sellEggButton;
    @FXML
    private Button sellEarthButton;
    @FXML
    private Button sellAirButton;
    @FXML
    private Button activateWaterButton;
    @FXML
    private Button activateFireButton;
    @FXML
    private Button activateEggButton;
    @FXML
    private Button activateEarthButton;
    @FXML
    private Button activateAirButton;
    @FXML
    private ListView<String> inventoryFire;
    @FXML
    private ListView<String> inventoryWater;
    @FXML
    private ListView<String> inventoryEgg;
    @FXML
    private ListView<String> inventoryEarth;
    @FXML
    private ListView<String> inventoryAir;
    @FXML
    private ListView<String> reserveWater;
    @FXML
    private ListView<String> reserveFire;
    @FXML
    private ListView<String> reserveEgg;
    @FXML
    private ListView<String> reserveEarth;
    @FXML
    private ListView<String> reserveAir;
    // List of towers for the currentFull() method
    Tower[] towers = new Tower[] {waterTower, fireTower, earthTower, eggTower, airTower};
    // Initialises the SceneController
    private SceneController sceneController = SceneController.getInstance();

    /**
     * Sets the initial states for the towers in the inventory.
     * Checks if the towers are in the inventory and whether they are active or in the
     * reserves. If they are active, displays the towers in the active row, if they are reserves
     * then displays them in the reserves row, otherwise they are in the shop and not displayed.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Filling waterTower ListViews //
        inventoryWater.getItems().addAll(waterTower.getAttributes());
        reserveWater.getItems().addAll(waterTower.getAttributes());
        if(waterTower.isInCurrent()){waterVisible(true, false);}
        else waterVisible(false, waterTower.isInReserve());

        // Filling fireTower ListViews //
        inventoryFire.getItems().addAll(fireTower.getAttributes());
        reserveFire.getItems().addAll(fireTower.getAttributes());
        if(fireTower.isInCurrent()){fireVisible(true, false);}
        else fireVisible(false, fireTower.isInReserve());

        // Filling eggTower ListViews //
        inventoryEgg.getItems().addAll(eggTower.getAttributes());
        reserveEgg.getItems().addAll(eggTower.getAttributes());
        if(eggTower.isInCurrent()){eggVisible(true, false);}
        else eggVisible(false, eggTower.isInReserve());

        // Filling earthTower ListViews //
        inventoryEarth.getItems().addAll(earthTower.getAttributes());
        reserveEarth.getItems().addAll(earthTower.getAttributes());
        if(earthTower.isInCurrent()){earthVisible(true, false);}
        else earthVisible(false, earthTower.isInReserve());

        // Filling airTower ListViews
        inventoryAir.getItems().addAll(airTower.getAttributes());
        reserveAir.getItems().addAll(airTower.getAttributes());
        if(airTower.isInCurrent()){airVisible(true,false);}
        else airVisible(false, airTower.isInReserve());

    }

    // ---------- BUTTON FUNCTIONS ---------- //

    /**
     * Moves the waterTower from active to reserve towers.
     */
    public void moveWater(){
        if(!oneLeft()){
            waterTower.putInReserve();
            waterVisible(false, true);
        }
    }
    /**
     * Moves the fireTower from active to reserve towers.
     */
    public void moveFire(){
        if (!oneLeft()){
            fireTower.putInReserve();
            fireVisible(false, true);
        }
    }
    /**
     * Moves the eggTower from active to reserve towers.
     */
    public void moveEgg(){
        if(!oneLeft()){
            eggTower.putInReserve();
            eggVisible(false, true);
        }
    }
    /**
     * Moves the earthTower from active to reserve towers.
     */
    public void moveEarth(){
        if(!oneLeft()){
            earthTower.putInReserve();
            earthVisible(false, true);
        }
    }
    /**
     * Moves the airTower from active to reserve towers.
     */
    public void moveAir(){
        if(!oneLeft()){
            airTower.putInReserve();
            airVisible(false, true);
        }
    }
    /**
     * Moves the waterTower from reserve to the shop.
     */
    public void sellWater(){
        waterTower.putInShop(true);
        waterVisible(false, false);
    }
    /**
     * Moves the fireTower from reserve to the shop.
     */
    public void sellFire(){
        fireTower.putInShop(true);
        fireVisible(false, false);
    }
    /**
     * Moves the eggTower from reserve to the shop.
     */
    public void sellEgg(){
        eggTower.putInShop(true);
        eggVisible(false, false);
    }
    /**
     * Moves the earthTower from reserve to the shop.
     */
    public void sellEarth(){
        earthTower.putInShop(true);
        earthVisible(false, false);
    }
    /**
     * Moves the airTower from reserve to the shop.
     */
    public void sellAir(){
        airTower.putInShop(true);
        airVisible(false, false);
    }
    /**
     * Moves the waterTower from reserve to active towers.
     */
    public void activateWater(){
        if(!currentFull()){
            waterTower.putInCurrent();
            waterVisible(true, false);
        }
    }
    /**
     * Moves the fireTower from reserve to active towers.
     */
    public void activateFire(){
        if(!currentFull()){
            fireTower.putInCurrent();
            fireVisible(true, false);
        }
    }
    /**
     * Moves the eggTower from reserve to active towers.
     */
    public void activateEgg(){
        if(!currentFull()){
            eggTower.putInCurrent();
            eggVisible(true, false);
        }
    }
    /**
     * Moves the earthTower from reserve to active towers.
     */
    public void activateEarth(){
        if(!currentFull()){
            earthTower.putInCurrent();
            earthVisible(true,false);
        }
    }
    /**
     * Moves the airTower from reserve to active towers.
     */
    public void activateAir(){
        if(!currentFull()){
            airTower.putInCurrent();
            airVisible(true, false);
        }
    }

    /**
     * Switches the scene from the inventory to the game round.
     * @param event inventoryExitButton pressed
     * @throws IOException if fxml file is not found
     */
    @FXML
    public void inventoryExitPressed(ActionEvent event) throws IOException {
        sceneController.SwitchToRound(event);
    }

    // ---------- HELPER FUNCTIONS ---------- //

    /**
     * Checks if the number of towers active is already 3.
     * If number of towers is 3 then alerts the player and returns true.
     * @return whether three towers are active or not.
     */
    public boolean currentFull(){
        // Checks that there are only 3 towers currently in play
        int current = 0;
        for(Tower tower : towers){
            if (tower.isInCurrent()){
                current += 1;
            }
        }
        if (current >= 3){
            // Alerts the player.
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Your active tower slots are full");
            alert.show();
            // Returns that active towers is full
            return true;
        }
        else{return false;}
    }

    /**
     * Checks to see if there is only one tower currently active.
     * @return if there is one tower left.
     */
    public boolean oneLeft(){
        int current = 0;
        for(Tower tower : towers){
            if (tower.isInCurrent()){
                current += 1;
            }
        }
        if (current == 1){
            // Alerts the player.
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("You must have at least one active tower");
            alert.show();
            // Returns that there is only one active tower left
            return true;
        }
        else{return false;}
    }


    /**
     * Sets the state of the waterTower listViews and buttons.
     * If the tower is in current the move and inventory are visible and if
     * tower is in reserve then activate, sell, and reserve are visible.
     * @param current whether the tower is currently active.
     * @param reserve whether the tower is currently reserved.
     */
    public void waterVisible(boolean current, boolean reserve){
        moveWaterButton.setVisible(current);
        inventoryWater.setVisible(current);
        reserveWater.setVisible(reserve);
        sellWaterButton.setVisible(reserve);
        activateWaterButton.setVisible(reserve);
    }
    /**
     * Sets the state of the fireTower listViews and buttons.
     * If the tower is in current the move and inventory are visible and if
     * tower is in reserve then activate, sell, and reserve are visible.
     * @param current whether the tower is currently active.
     * @param reserve whether the tower is currently reserved.
     */
    public void fireVisible(boolean current, boolean reserve){
        moveFireButton.setVisible(current);
        inventoryFire.setVisible(current);
        reserveFire.setVisible(reserve);
        sellFireButton.setVisible(reserve);
        activateFireButton.setVisible(reserve);
    }
    /**
     * Sets the state of the eggTower listViews and buttons.
     * If the tower is in current the move and inventory are visible and if
     * tower is in reserve then activate, sell, and reserve are visible.
     * @param current whether the tower is currently active.
     * @param reserve whether the tower is currently reserved.
     */
    public void eggVisible(boolean current, boolean reserve){
        moveEggButton.setVisible(current);
        inventoryEgg.setVisible(current);
        reserveEgg.setVisible(reserve);
        sellEggButton.setVisible(reserve);
        activateEggButton.setVisible(reserve);
    }
    /**
     * Sets the state of the earthTower listViews and buttons.
     * If the tower is in current the move and inventory are visible and if
     * tower is in reserve then activate, sell, and reserve are visible.
     * @param current whether the tower is currently active.
     * @param reserve whether the tower is currently reserved.
     */
    public void earthVisible(boolean current, boolean reserve){
        moveEarthButton.setVisible(current);
        inventoryEarth.setVisible(current);
        reserveEarth.setVisible(reserve);
        sellEarthButton.setVisible(reserve);
        activateEarthButton.setVisible(reserve);
    }
    /**
     * Sets the state of the airTower listViews and buttons.
     * If the tower is in current the move and inventory are visible and if
     * tower is in reserve then activate, sell, and reserve are visible.
     * @param current whether the tower is currently active.
     * @param reserve whether the tower is currently reserved.
     */
    public void airVisible(boolean current, boolean reserve){
        moveAirButton.setVisible(current);
        inventoryAir.setVisible(current);
        reserveAir.setVisible(reserve);
        sellAirButton.setVisible(reserve);
        activateAirButton.setVisible(reserve);
    }
}
