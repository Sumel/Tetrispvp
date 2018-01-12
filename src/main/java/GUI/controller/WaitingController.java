package GUI.controller;

import GUI.GameMode.PvPMode;
import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class WaitingController extends PaneController {
    @FXML
    public VBox loader = new VBox();

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

        ((PvPMode) gameModeData.getMode()).setIpA("192.168.0.1"); //tutaj podajemy IP hosta
        ((PvPMode) gameModeData.getMode()).setStatusForPlayerAToReady();

        gameModeData.gameModeChanged(); // tutaj informujemy, ze gracz wybral PvP Mode
        // czekamy odpowiedzi... mamy podtwirdzenie... zamieniamy scene na BoardPane
        // super.guiController.changeScene("BoardPane");
    }

    public void setLoader() throws Exception {
        final Label status = new Label("");

        final String content = "...";
        final Text text = new Text("");
        //Font.loadFont((new FileInputStream(new File("src/GUI/view/digital.ttf"))), 38);
        //text.setFont(Font.font(20));
        //text.setFont(Font.loadFont(new FileInputStream(new File("src/GUI/view/digital.ttf")), 50));
        //text.setFont(Font.loadFont(getClass().getResourceAsStream("../view/digital.ttf"), 50));
        text.setId("Dot");
        //text.setStyle("-fx-text-fill: yellow;-fx-fill: #ff8b34;");
        final Animation animation = new Transition() {
            {
                setCycleDuration(Duration.millis(2000));
                setCycleCount(Timeline.INDEFINITE);
            }

            protected void interpolate(double frac) {
//                final int length = content.length();
//                final int n = Math.round(length * (float) frac);
//                text.setText(content.substring(0, n));
                final float n = 1 * (float) frac;
                if (n < (float) 1 / 4) text.setText("");
                else if (n < (float) 2 / 4) text.setText(".");
                else if (n < (float) 3 / 4) text.setText(". .");
                else if (n <= (float) 4 / 4) text.setText(". . .");

            }

        };

        animation.play();

        //VBox layout = new VBox();
        loader.setAlignment(Pos.CENTER);
        loader.getChildren().addAll(text);

    }
}
