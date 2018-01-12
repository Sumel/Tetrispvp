package GUI.controller;

import GUI.GameMode.AIMode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AiPaneController extends PaneController {
    @FXML
    public Button AcceptButton;
    @FXML
    public Button CancelButton;

    @FXML
    public void handleAcceptAction(ActionEvent actionEvent) {
        gameModeData.setGameMode(new AIMode(0)); // zamiast "0" podac wybrany poziom trudnosci
        gameModeData.notifyListeners();
        // czekamy odpowiedzi i zmienamy scene na BoardPane
        // super.guiController.changeScene("BoardPane");
    }

    @FXML
    public void handleCancelAction(ActionEvent actionEvent) {
        super.guiController.changeScene("MenuPane");
    }
}
