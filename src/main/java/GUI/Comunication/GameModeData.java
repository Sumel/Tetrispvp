package GUI.Comunication;

import GUI.GameMode.GameMode;

import java.util.ArrayList;

public class GameModeData implements Sender {
    private ArrayList listeners;
    private GameMode gameMode = null;

    public GameModeData() {
        listeners = new ArrayList();
    }

    @Override
    public void registerListener(Listener l) {
        listeners.add(l);
    }

    @Override
    public void removeListener(Listener l) {
        int i = listeners.indexOf(l);
        if (i >= 0) {
            listeners.remove(i);
        }
    }

    @Override
    public void notifyListeners() {
        for (int i = 0; i < listeners.size(); i++) {
            Listener listener = (Listener)listeners.get(i);
            listener.update(gameMode);
        }
    }

    public void gameModeChanged() {
        notifyListeners();
    }

    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }

    public GameMode getMode() {
        return gameMode;
    }
}
