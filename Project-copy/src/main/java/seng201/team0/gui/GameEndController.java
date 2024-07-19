package seng201.team0.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import seng201.team0.models.Inventory;
import seng201.team0.models.MainFunction;
import seng201.team0.models.Settings;
import seng201.team0.models.Tower;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Controls the end screen for the game.
 * Displays the win or loss messages and then allows the player to either exit or restart the
 * game.
 */
public class GameEndController implements Initializable {
    // ---------- INITIALISATION ---------- //
    // Initialises the towers.
    ArrayList<Tower> towerList = Settings.getTowers();
    Tower waterTower = towerList.get(0);
    Tower fireTower = towerList.get(1);
    Tower earthTower = towerList.get(2);
    Tower airTower = towerList.get(3);
    Tower eggTower = towerList.get(4);

    // Initialises the towers for noneLeft() function.
    Tower[] towers = new Tower[] {waterTower, fireTower, earthTower, eggTower, airTower};

    // Injects all the JavaFX elements.
    @FXML
    private Button restartButton;
    @FXML
    private Button exitButton;
    @FXML
    private Label endLabel;
    @FXML
    private Label messageLabel;
    private static Stage stage;
    private static Scene scene;
    // Initialises the MainFunction instance.
    private MainFunction main = MainFunction.getInstance();

    /**
     * Gets the name of the player from Settings and decides whether the player
     * has won, then displays appropriate messages.
     * Player loses if their coins are less than 0.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String name = Settings.getInstance().getName();
        // Player loss
        if(Inventory.getCoins()<0){
            endLabel.setText(name+" you've gone into debt.");
            messageLabel.setText("Sorry but you have lost.");
        }
        else if (noneLeft()){
            endLabel.setText("All your towers broke " + name);
            messageLabel.setText("Sorry but you cannot continue.");
        }
        // Player win
        else{
            endLabel.setText(name+" you did not go into debt.");
            messageLabel.setText("Well done, you have won.");
        }
    }
    // ---------- BUTTON FUNCTIONS ---------- //

    /**
     * Exits the game.
     * @param event the button being pressed.
     */
    public void exitButtonPressed(ActionEvent event){
        javafx.application.Platform.exit();
    }

    /**
     * Resets the player and tower variables and then
     * opens the start page.
     * @param event the button being pressed.
     * @throws IOException error in getting the fxml files.
     */
    public void restartButtonPressed(ActionEvent event) throws IOException {
        main.newGame();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/gameStart.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // ---------- HELPER FUNCTIONS ---------- //
    /**
     * Checks if the player lost because their towers all broke.
     * @return if they have no towers left.
     */
    public boolean noneLeft() {
        int current = 0;
        for (Tower tower : towers) {
            if (tower.isInCurrent()) {
                current += 1;
            }
        }
        return current == 0;
    }

}
