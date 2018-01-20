package GUI;

import GUI.controller.GuiController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	private Stage primaryStage;
	private GUI.controller.GuiController GuiController;

	@Override
	public void start(Stage primaryStage) {

		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Tetris PvP");

		this.GuiController = new GuiController(primaryStage);
		this.GuiController.initRootLayout();

	}

	public static void main(String[] args) {
		launch();
	}


}
