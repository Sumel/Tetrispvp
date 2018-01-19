package GUI.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PvpPaneController extends PaneController {
    @FXML
    private Button HostButton;
    @FXML
    private Button JoinButton;

    @FXML
    private void handleHostAction(ActionEvent actionEvent) {
        super.guiController.changeScene("WaitingPane");
    }

    @FXML
    private void handleJoinAction(ActionEvent actionEvent) {
        super.guiController.changeScene("JoinPane");
    }
}
