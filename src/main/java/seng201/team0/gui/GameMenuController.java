package seng201.team0.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

/**
 * Controls the game menu.
 * Allows the player to exit the game even if all the rounds are not yet completed.
 */
public class GameMenuController {
    // ---------- INITIALISATION ---------- //

    @FXML
    private Button menuExitButton;

    // Initialises the SceneController
    private SceneController sceneController = SceneController.getInstance();

    // ---------- BUTTON FUNCTIONS ---------- //
    /**
     * Changes the game back to the game round from the menu screen.
     * @param event the continue button being pressed.
     * @throws IOException if there is any error loading the fxml file.
     */
    @FXML
    public void menuContinuePressed(ActionEvent event) throws IOException {
        sceneController.SwitchToRound(event);
    }
    /**
     * Allows the player to exit the game during a round.
     */
    public void menuExitPressed() {
        javafx.application.Platform.exit();
    }
}
