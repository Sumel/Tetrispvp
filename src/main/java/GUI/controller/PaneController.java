package GUI.controller;

import GUI.Comunication.GameModeData;

public abstract class PaneController {
    protected GuiController guiController;
    protected GameModeData gameModeData= new GameModeData();

    void setGuiController(GuiController guiController) {
        this.guiController = guiController;
        //gameModeData = new GameModeData();
    }

    protected GameModeData getGameModeData() {
        return gameModeData;
    }
}
