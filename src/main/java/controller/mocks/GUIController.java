package controller.mocks;

import controller.GameController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


public class GUIController extends Application {

    Scene tetrisPane;
    Circle circ = new Circle(40, 40, 30);
    Group tetrisControllerArea = new Group(circ);

    public GUIController() {
        tetrisPane = new Scene(tetrisControllerArea, 960, 720 ) ;
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle( "TetrisPVP" );
        primaryStage.setScene(tetrisPane);
        primaryStage.show();
    }


    public void setKeyPressedHandler(EventHandler<KeyEvent> eventHandler) {
        tetrisPane.setOnKeyPressed(eventHandler);
    }


    public void updateNextBlockView(Block nextBlock) {

    }

    public static void main(String[] args) {
        GameController gameController = new GameController();
        gameController.initGame();
        launch(args);
    }

    public void startGame() {

    }



}
