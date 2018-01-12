package GUI.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MenuController extends PaneController {
    @FXML
    public Button PlayerButton;
    @FXML
    public Button AiButton;


    @FXML
    public void handlePlayerAction(ActionEvent actionEvent) {
        super.guiController.changeScene("PvpPane");
    }

    @FXML
    public void handleAiAction(ActionEvent actionEvent) {
        super.guiController.changeScene("AiPane");
    }
}
