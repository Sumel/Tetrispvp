package GUI.controller;

import GUI.Block.Block;
import GUI.Main;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class GuiController implements IGuiController {
    protected Stage primaryStage;
    private EventHandler<KeyEvent> keyEvent = null;
    private Scene currentScene = null;
    private PaneController controller;

    public GuiController(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.initRootLayout();
    }

    private void initRootLayout() {
        try {
            this.primaryStage.setTitle("Tetris PVP");
            this.primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    System.out.println("close");
                }
            });

            // load layout from FXML file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/MenuPane.fxml"));
            Pane rootLayout = loader.load();

            // set initial data into controller
            PaneController controller = loader.getController();
            controller.setGuiController(this);
            this.controller=controller;

            // add layout to a scene and show them all
            Scene scene = new Scene(rootLayout);
            this.currentScene=scene;
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            // don't do this in common apps
            e.printStackTrace();
        }
    }

    protected void changeScene(String Pane) {
        try {
            // load layout from FXML file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/" + Pane + ".fxml"));
            javafx.scene.layout.Pane rootLayout = loader.load();

            PaneController controller = loader.getController();
            controller.setGuiController(this);
            this.controller = controller;

            // add layout to a scene and show them all
            Scene scene = new Scene(rootLayout);
            currentScene = scene;
            scene.setOnKeyPressed(keyEvent);

            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            // don't do this in common apps
            e.printStackTrace();
        }
    }

    @Override
    public void setKeyPressedHandler(EventHandler<KeyEvent> eventHandler) {
        this.keyEvent = eventHandler;
        if (currentScene != null) currentScene.setOnKeyPressed(eventHandler);
    }

    @Override
    public void startGame() {
        changeScene("BoardPane");
    }

    @Override
    public void updateNextBlockView(Block block) {
        if (controller != null)
            if (controller.getClass() == BoardPaneController.class)
                ((BoardPaneController) controller).addNextBlockToPane(block);
    }
}
