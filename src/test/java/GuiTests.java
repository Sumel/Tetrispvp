import GUI.controller.GuiController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.hasChildren;
import static org.testfx.matcher.base.NodeMatchers.hasText;
import org.testfx.api.FxRobot;


class GuiTests extends ApplicationTest {

    @Override
    public void start(Stage stage) {
        Button button = new Button("click me!");
        button.setOnAction(actionEvent -> button.setText("clicked!"));
        stage.setScene(new Scene(new StackPane(button), 100, 100));
        stage.show();
    }


    @Test
    void simple_test() {
        // given:
        //rightClickOn("#desktop").moveTo("New").clickOn("Text Document");
        //write("myTextfile.txt").push(ENTER);

        // when:
        //drag(".file").dropTo("#trash-can");

        //then:
        verifyThat(".button", hasText("click me!"));

    }
}