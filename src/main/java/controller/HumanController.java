package controller;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class HumanController {

    MoveController moveController;

    HumanController(MoveController moveController) {
        this.moveController = moveController;
    }

    private void pressedRight() {
        moveController.moveRight();
    }

    private void pressedLeft() {
        moveController.moveLeft();
    }

    private void pressedDown() {
        moveController.moveDown();
    }

    private void pressedUp() {
        moveController.rotate();
    }

    private void pressedSpace() {
        moveController.fall();
    }

    protected EventHandler<KeyEvent> getEventHandler() {
        return keyEvent -> {
            switch (keyEvent.getCode()) {
                case KP_UP:
                    pressedUp();
                    break;
                case DOWN:
                    pressedDown();
                    break;
                case LEFT:
                    pressedLeft();
                    break;
                case RIGHT:
                    pressedRight();
                    break;
                case SPACE:
                    pressedSpace();
                    break;
                default:
                    System.out.println(keyEvent.getCode());
            }
        };
    }

}
