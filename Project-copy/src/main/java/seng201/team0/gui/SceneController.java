package seng201.team0.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controls the switching between the scenes for the game.
 */
public class SceneController {
    // ---------- INITIALISATION ---------- //
    private static SceneController sceneController_instance = null;

    /**
     * Creates a single instance of the SceneController Class.
     * @return the instance of SceneController.
     */
    public static synchronized SceneController getInstance(){
        if (sceneController_instance == null){
            sceneController_instance = new SceneController();
        }
        return sceneController_instance;
    }
    private static Stage stage;
    private static Scene scene;

    // ---------- SWITCH FUNCTIONS ---------- //
    /**
     * Switches to the shop.
     * @param event the action causing the switch.
     * @throws IOException if there is an error finding the fxml file.
     */
    public void SwitchToShop(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/gameShop.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Switches to the inventory.
     * @param event the action causing the switch.
     * @throws IOException if there is an error finding the fxml file.
     */
    public void SwitchToInventory(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/gameInventory.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Switches to the menu.
     * @param event the action causing the switch.
     * @throws IOException if there is an error finding the fxml file.
     */
    public void SwitchToMenu(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/gameMenu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Switches to the round screen.
     * @param event the action causing the switch.
     * @throws IOException if there is an error finding the fxml file.
     */
    public void SwitchToRound(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/gameRound.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Switches to the end screen.
     * @param event the action causing the switch.
     * @throws IOException if there is an error finding the fxml file.
     */
    public void SwitchToEnd(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/gameEnd.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
