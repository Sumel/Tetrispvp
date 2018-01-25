package GUI.controller;

import GUI.Block.Block;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public interface IGuiController {
    void setKeyPressedHandler(EventHandler<KeyEvent> eventHandler);
    void startGame();
    void updateNextBlockView(Block block);
}
