package GUI.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PvpPaneController extends PaneController {
    @FXML
    public Button HostButton;
    @FXML
    public Button JoinButton;

    @FXML
    public void handleHostAction(ActionEvent actionEvent) {
        super.guiController.changeScene("WaitingPane");
    }

    @FXML
    public void handleJoinAction(ActionEvent actionEvent) {
        super.guiController.changeScene("JoinPane");
    }
}
