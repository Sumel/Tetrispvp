package GUI.controller;

import GUI.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class GuiController {
    protected Stage primaryStage;

    public GuiController(Stage primaryStage) {
        this.primaryStage=primaryStage;
    }


    public void initRootLayout() {
        try {
            this.primaryStage.setTitle("Tetris PVP");

            // load layout from FXML file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/BoardPane.fxml"));
            Pane rootLayout = loader.load();

            // set initial data into controller
            PaneController controller = loader.getController();
            controller.setGuiController(this);

            // add layout to a scene and show them all
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            // don't do this in common apps
            e.printStackTrace();
        }
    }

    public void changeScene(String Pane){
        try {
            // load layout from FXML file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/" + Pane + ".fxml"));
            Pane rootLayout = loader.load();

            PaneController controller = loader.getController();
            controller.setGuiController(this);

            // add layout to a scene and show them all
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            // don't do this in common apps
            e.printStackTrace();
        }
    }

}
