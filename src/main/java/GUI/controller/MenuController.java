package GUI.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MenuController extends PaneController {
    @FXML
    private Button PlayerButton;
    @FXML
    private Button AiButton;

    @FXML
    private void handlePlayerAction(ActionEvent actionEvent) {
        super.guiController.changeScene("PvpPane");
    }

    @FXML
    private void handleAiAction(ActionEvent actionEvent) {
        super.guiController.changeScene("AiPane");
    }
}
