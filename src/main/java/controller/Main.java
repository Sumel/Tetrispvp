package controller;

/**
 * Created by James on 03.01.2018.
 */
public class Main {

    public static void main(String[] args) {
        GameController gameController = new GameController(GameMode.AI);
        gameController.initGame();
    }
}
