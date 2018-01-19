package GUI.controller;

import GUI.GameMode.PvPMode;
import GUI.Mocks.Network;
import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class WaitingController extends PaneController {
    @FXML
    private VBox loader = new VBox();
    @FXML
    private Text IpAddress;

    @FXML
    private void initialize() {
        try {
            setLoader();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (gameModeData.getMode() == null) {
            gameModeData.setGameMode(new PvPMode());
        }
        //((PvPMode) gameModeData.getMode()).setIpA("192.168.0.1"); //tutaj podajemy IP hosta
        ((PvPMode) gameModeData.getMode()).setStatusForPlayerAToReady();
        setIp(IpAddress);
        gameModeData.gameModeChanged(); // tutaj informujemy, ze gracz wybral PvP Mode
        // czekamy odpowiedzi... mamy podtwirdzenie... zamieniamy scene na BoardPane
        // super.guiController.changeScene("BoardPane");
    }

    private void setLoader() throws Exception {
        final Text text = new Text("");
        text.setId("Dot");
        final Animation animation = new Transition() {
            {
                setCycleDuration(Duration.millis(2000));
                setCycleCount(Timeline.INDEFINITE);
            }
            protected void interpolate(double frac) {
                final float n = 1 * (float) frac;
                if (n < (float) 1 / 4) text.setText("");
                else if (n < (float) 2 / 4) text.setText(".");
                else if (n < (float) 3 / 4) text.setText(". .");
                else if (n <= (float) 4 / 4) text.setText(". . .");
            }
        };
        animation.play();
        loader.setAlignment(Pos.CENTER);
        loader.getChildren().addAll(text);

    }

    private void setIp(Text txt) {
        new Thread(() -> this.takeIP(txt)).start();
    }

    private void takeIP(Text txt) {
        String ip = new Network().getIp();
        txt.setText(ip);
    }
}
