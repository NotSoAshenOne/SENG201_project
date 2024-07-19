package seng201.team0.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import seng201.team0.models.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Controls the game round.
 * Allows the player to switch to the menu, inventory, shop and play the next round.
 * Displays the upcoming carts, coins, and the number of rounds left.
 */
public class GameRoundController implements Initializable {
    // ---------- INITIALISATION ---------- //
    // Initialises the MainFunction instance.
    private MainFunction main = MainFunction.getInstance();
    // Initialises the SceneController.
    private SceneController sceneController = SceneController.getInstance();
    // Initialising the towers.
    ArrayList<Tower> towerList = Settings.getTowers();
    Tower waterTower = towerList.get(0);
    Tower fireTower = towerList.get(1);
    Tower earthTower = towerList.get(2);
    Tower airTower = towerList.get(3);
    Tower eggTower = towerList.get(4);

    // Injects all the JavaFX elements.

    @FXML
    private Button menuButton;
    @FXML
    private Button inventoryButton;
    @FXML
    private Button shopButton;
    @FXML
    private Button nextRoundButton;
    @FXML
    private Button finishButton;
    @FXML
    private ListView<String> waterListView;
    @FXML
    private ListView<String> fireListView;
    @FXML
    private ListView<String> earthListView;
    @FXML
    private ListView<String> airListView;
    @FXML
    private ListView<String> eggListView;
    @FXML
    private Label roundLabel;
    @FXML
    private Label coinsLabel;
    @FXML
    private ListView<Cart> upcomingListView;
    @FXML
    private ListView<String> towerExample;

    // Initialises a list of towers for the noneLeft method.
    Tower[] towers = new Tower[] {waterTower, fireTower, earthTower, eggTower, airTower};

    /**
     * Sets the initial values for the coins, rounds left, upcoming carts and the towers that
     * are currently active.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        coinsLabel.setText(String.valueOf(Inventory.getCoins()));
        roundLabel.setText(String.valueOf(main.getNumRoundsLeft()));
        upcomingListView.getItems().addAll(MainFunction.cartLineupString);
        towerExample.getItems().addAll(new String[] {"STATS:","Amount","Speed","Level","TYPE"});
        updateTowers();
    }

    // ---------- BUTTON FUNCTIONS ---------- //

    /**
     * Switches the screen to the menu.
     * @param event button pressed.
     * @throws IOException fxml file not found.
     */
    public void roundMenuPressed(ActionEvent event) throws IOException{
        sceneController.SwitchToMenu(event);
    }

    /**
     * Switches the screen to the inventory.
     * @param event button pressed.
     * @throws IOException fxml file not found.
     */
    public void inventoryPressed(ActionEvent event) throws IOException{
        sceneController.SwitchToInventory(event);
    }

    /**
     * Switches the screen to the shop.
     * @param event button pressed.
     * @throws IOException fxml file not found.
     */
    public void shopPressed(ActionEvent event) throws IOException{
        sceneController.SwitchToShop(event);
    }

    /**
     * Runs the nextCart method and then refreshes all the tower stats and removes them if they
     * have been broken during the round.
     * Updates the upcoming carts list, the coins, and the number of rounds left.
     * Then checks if the number of rounds left is 0 or all towers are broken
     * and if it is removes the next round button and makes the finish button visible.
     */
    public void nextRoundPressed() {
        main.nextCart();
        clearTowers();
        updateTowers();
        // Update carts, coins, and rounds.
        upcomingListView.getItems().clear();
        upcomingListView.getItems().addAll(MainFunction.cartLineupString);
        coinsLabel.setText(String.valueOf(Inventory.coins));
        roundLabel.setText(String.valueOf(main.getNumRoundsLeft()));
        // All rounds are complete or towers all broken, show the finish button.
        if (main.getNumRoundsLeft() <= 0 || noneLeft()){
            nextRoundButton.setVisible(false);
            finishButton.setVisible(true);
        }

    }

    /**
     * Switches the screen to the end screen.
     * Only appears once all of the rounds have finished.
     * @param event
     * @throws IOException
     */
    public void finishButtonPressed(ActionEvent event) throws IOException{
        sceneController.SwitchToEnd(event);
    }

    // ---------- HELPER FUNCTIONS ---------- //

    /**
     * Checks if the towers are active and updates the values of the tower stats if they are.
     */
    public void updateTowers(){
        if(waterTower.isInCurrent()){waterListView.getItems().addAll(waterTower.getAttributes());}
        if(fireTower.isInCurrent()){fireListView.getItems().addAll(fireTower.getAttributes());}
        if(earthTower.isInCurrent()){earthListView.getItems().addAll((earthTower.getAttributes()));}
        if (airTower.isInCurrent()){airListView.getItems().addAll(airTower.getAttributes());}
        if(eggTower.isInCurrent()){eggListView.getItems().addAll(eggTower.getAttributes());}
    }

    /**
     * Refreshes all the towers, allowing the stats to update and for them to be removed
     * if a tower breaks.
     */
    public void clearTowers(){
        waterListView.getItems().clear();
        fireListView.getItems().clear();
        eggListView.getItems().clear();
        airListView.getItems().clear();
        earthListView.getItems().clear();
    }

    /**
     * Checks if there are no more active towers and alerts the player if there are not.
     * @return if there are no towers.
     */
    public boolean noneLeft(){
        int current = 0;
        for(Tower tower : towers){
            if (tower.isInCurrent()){
                current += 1;
            }
        }
        if (current == 0){
            // Alerts the player.
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Your final tower has broken!");
            alert.show();
            // Returns that there are no active towers
            return true;
        }
        else{return false;}
    }

}
