package GUI.controller;

import GUI.Comunication.GameModeData;

public class PaneController {
    protected GuiController guiController;
    public GameModeData gameModeData= new GameModeData();

    public void setGuiController(GuiController guiController) {
        this.guiController = guiController;
        //gameModeData = new GameModeData();
    }

    public GameModeData getGameModeData() {
        return gameModeData;
    }
}
