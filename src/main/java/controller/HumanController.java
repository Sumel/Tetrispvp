package controller;

import mocks.Visualisation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class HumanController implements KeyListener, ActionListener  {

    MoveController moveController;

    HumanController(MoveController moveController, Visualisation visualisation) {
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

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_DOWN:
                pressedDown();
                break;
            case KeyEvent.VK_LEFT:
                pressedLeft();
                break;
            case KeyEvent.VK_RIGHT:
                pressedRight();
                break;
            case KeyEvent.VK_UP:
                pressedUp();
                break;
            case KeyEvent.VK_SPACE:
                pressedSpace();
                break;
            default:
                break;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
