package GUI;

import javafx.application.Application;
import javafx.stage.Stage;
import GUI.controller.GuiController;

import static java.lang.Thread.sleep;

public class Main extends Application {

	private Stage primaryStage;
	private GuiController GuiController;

	@Override
	public void start(Stage primaryStage) {

		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Tetris PvP");

		this.GuiController = new GuiController(primaryStage);
		this.GuiController.initRootLayout();

	}

	public static void main(String[] args) {
		launch(args);
	}


}
