package GUI.controller;

import GUI.GameMode.PvPMode;
import controller.Mode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class JoinPaneController extends PaneController {
    @FXML
    public TextField ipAddress;
    @FXML
    private Button AcceptButton;
    @FXML
    private Button CancelButton;

    @FXML
    private void handleAcceptAction(ActionEvent actionEvent) {
        if (gameModeData.getMode() == null) {
           gameModeData.setGameMode(new PvPMode());
        }

        ((PvPMode) gameModeData.getMode()).setIpB(ipAddress.getText()); //tutaj podajemy ten IP, który gracz wpisał
        ((PvPMode) gameModeData.getMode()).setStatusForPlayerBToReady();

        gameModeData.gameModeChanged(); // tutaj informujemy, ze gracz wybral PvP Mode
        // czekamy odpowiedzi... mamy podtwirdzenie... zamieniamy scene na BoardPane
        // super.guiController.changeScene("BoardPane");

        guiController.gameController.setGameMode(Mode.PVP_JOIN);
        guiController.gameController.setIP(ipAddress.getText());
        guiController.gameController.startGame();
    }

    @FXML
    private void handleCancelAction(ActionEvent actionEvent) {
        super.guiController.changeScene("MenuPane");
    }
}
