package GUI;


import GUI.controller.GuiController;
import controller.GameController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Main extends Application{

	@Override
	public void start(Stage primaryStage) {
		GuiController module = new GuiController(primaryStage);
		GameController controller = new GameController(module);
		module.setController(controller);

		module.setKeyPressedHandler(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
					case UP: System.out.println("Up"); break;
					case SPACE: System.out.println("Space"); break;
					case C: System.out.println("C"); break;
				}
			}
		});
	}

	public static void main(String[] args) {
		launch(args);
	}


}
