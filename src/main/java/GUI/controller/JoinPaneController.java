package GUI.controller;

import GUI.GameMode.PvPMode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class JoinPaneController extends PaneController {
    @FXML
    public Button AcceptButton;
    @FXML
    public Button CancelButton;

    @FXML
    public void handleAcceptAction(ActionEvent actionEvent) {
        if (gameModeData.getMode() == null) {
           gameModeData.setGameMode(new PvPMode());
        }

        ((PvPMode) gameModeData.getMode()).setIpB("127.0.0.1"); //tutaj podajemy ten IP, który gracz wpisał
        ((PvPMode) gameModeData.getMode()).setStatusForPlayerBToReady();

        gameModeData.gameModeChanged(); // tutaj informujemy, ze gracz wybral PvP Mode
        // czekamy odpowiedzi... mamy podtwirdzenie... zamieniamy scene na BoardPane
        // super.guiController.changeScene("BoardPane");
    }

    @FXML
    public void handleCancelAction(ActionEvent actionEvent) {
        super.guiController.changeScene("MenuPane");
    }
}
