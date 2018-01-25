package GUI.controller;

import GUI.GameMode.AIMode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;

public class AiPaneController extends PaneController {
    @FXML
    public Slider AILevel;
    @FXML
    private Button AcceptButton;
    @FXML
    private Button CancelButton;

    @FXML
    private void handleAcceptAction(ActionEvent actionEvent) {
        gameModeData.setGameMode(new AIMode(AILevel.getValue()/100)); // zamiast "0" podac wybrany poziom trudnosci
        gameModeData.notifyListeners();
        // czekamy odpowiedzi i zmienamy scene na BoardPane
        // super.guiController.changeScene("BoardPane");
        //System.out.println(AILevel.getValue());
        //super.guiController.changeScene("BoardPane");
    }

    @FXML
    private void handleCancelAction(ActionEvent actionEvent) {
        super.guiController.changeScene("MenuPane");
    }
}
