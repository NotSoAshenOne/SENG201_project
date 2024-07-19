package seng201.team0.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import seng201.team0.models.MainFunction;
import seng201.team0.models.RESOURCES;
import seng201.team0.models.Settings;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controls the game start screen.
 * Sets all the initial settings, rounds number, starting towers, name, and difficulty.
 */
public class GameStartController implements Initializable {
    // ---------- INITIALISATION ---------- //
    // Initialising the Settings instance.
    private Settings settings = Settings.getInstance();
    // Initialising the MainFunction instance.
    private MainFunction main = MainFunction.getInstance();
    // Initialises the SceneController
    private SceneController sceneController = SceneController.getInstance();
    // Injects all the JavaFX elements.
    @FXML
    private Button startButton;
    @FXML
    private RadioButton notHardButton;
    @FXML
    private RadioButton hardButton;
    @FXML
    private RadioButton harderButton;
    @FXML
    private TextField nameTextField;
    @FXML
    private ToggleGroup toggleGroup;
    @FXML
    private ChoiceBox<RESOURCES> towerOneChoice;
    @FXML
    private ChoiceBox<RESOURCES> towerTwoChoice;
    @FXML
    private ChoiceBox<RESOURCES> towerThreeChoice;
    @FXML
    private Slider numRoundsSlider;
    private RESOURCES[] resources = RESOURCES.values();

    /**
     * Used to start the game.
     * @param stage the initial stage.
     */
    public void init(Stage stage){}

    /**
     *
     * @param arg0
     * @param arg1 the resources for the current scene.
     */
    public void initialize(URL arg0, ResourceBundle arg1) {
        toggleGroup = new ToggleGroup();
        notHardButton.setToggleGroup(toggleGroup);
        hardButton.setToggleGroup(toggleGroup);
        harderButton.setToggleGroup(toggleGroup);
        notHardButton.setSelected(true);
        towerOneChoice.getItems().addAll(resources);
        towerTwoChoice.getItems().addAll(resources);
        towerThreeChoice.getItems().addAll(resources);
    }
    // ---------- BUTTON FUNCTIONS ---------- //

    /**
     * Takes all the settings from the player and sets them for the game.
     * Also checks that three distinct towers have been selected and that the name is valid.
     * Then switches to the game round.
     * @param event start button pressed.
     * @throws IOException if there is any errors loading the fxml file.
     */
    @FXML
    public void startButtonPressed(ActionEvent event) throws IOException {
        // Gets the player's tower and round choice.
        RESOURCES towerOne = towerOneChoice.getValue();
        RESOURCES towerTwo = towerTwoChoice.getValue();
        RESOURCES towerThree = towerThreeChoice.getValue();
        int roundNum = (int)numRoundsSlider.getValue();

        // set name to nameTextField.getText()
        Settings.setName(nameTextField.getText());

        if (notHardButton.isSelected()){
            // Set difficulty to 1
            main.setDifficulty(1);
        }
        else if (hardButton.isSelected()) {
            // Set difficulty to 2
            main.setDifficulty(2);
        }
        else if (harderButton.isSelected()){
            // Set difficulty to 3
            main.setDifficulty(3);
        }
        // Set the number of rounds.
        main.setNumRoundsLeft(roundNum + 1);
        // Checks if the player has selected three distinct towers.
        if (towerOne == towerTwo || towerOne == towerThree || towerTwo == towerThree) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Please only pick one of each type of tower.");
            alert.show();
        }
        //  Checks if name is valid and then sets towers and cart lineup and switches to the round.
        else if (checkName(nameTextField.getText())){
            Settings.addTowers();
            settings.setTowers(towerOne);
            settings.setTowers(towerTwo);
            settings.setTowers(towerThree);
            MainFunction.setCartLineup();
            SwitchToGameRound(event);
            }
    }

    /**
     * Switches to the game round.
     * @param event button is pressed.
     * @throws IOException if there is any error in loading the fxml file.
     */
    @FXML
    public void SwitchToGameRound(ActionEvent event) throws IOException {
        sceneController.SwitchToRound(event);
    }

    /**
     * Checks to see if the player's name is between 3 and 15 characters and contains no special characters.
     * Returns true if these conditions are met otherwise alerts the player and returns false.
     * @param tempName the player's name choice.
     * @return if the name is valid.
     */
    public static boolean checkName(String tempName){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        String name = tempName;
        int len = name.length();
        String[] special = {"[","@","_","!","#","$","%","^","&","*","(",")","<",">","?","/","|","}","{","~",":","]"};
        boolean valid = true;
        // Check if longer than 3 characters.
        if (len < 3){
            System.out.println("Name must be longer than 3 characters");
            alert.setHeaderText("Name must be longer than 3 characters");
            alert.show();
            valid = false;
        }
        // Check if less than 15 characters.
        else if(len > 15){
            System.out.println("Name must be less than 15 characters");
            alert.setHeaderText("Name must be less than 15 characters");
            alert.show();
            valid = false;
        }
        // Check if the name contains special characters.
        else {
            for (String character : special){
                if (name.contains(character)){
                    System.out.println("Name cannot contain special characters");
                    alert.setHeaderText("Name cannot contain special characters");
                    alert.show();
                    valid = false;
                    break;
                }
            }
        }
        return valid;
    }
}
