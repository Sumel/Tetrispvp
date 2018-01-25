package GUI.controller;

import GUI.Comunication.GameModeData;
import tetrispvp.network.MessageReceiver;
import tetrispvp.network.MessageSender;

public abstract class PaneController {
    protected GuiController guiController;
    protected GameModeData gameModeData= new GameModeData();
    protected MessageSender sender;
    protected MessageReceiver receiver;

    void setGuiController(GuiController guiController) {
        this.guiController = guiController;
        //gameModeData = new GameModeData();
    }

    protected GameModeData getGameModeData() {
        return gameModeData;
    }
}
