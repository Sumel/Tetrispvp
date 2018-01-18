package mocks;

import controller.HumanController;

import javax.swing.*;

public class Visualisation {

    JFrame tetrisBoard;
    JTextField tetrisControllerArea;

    public Visualisation() {
        tetrisBoard = new JFrame("TETRIS PVP");
        tetrisBoard.setBounds(50, 100, 300, 300);
        tetrisBoard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tetrisControllerArea = new JTextField(20);
        tetrisBoard.add(tetrisControllerArea);
        tetrisBoard.setVisible(true);
    }

    public void addKeyListner(HumanController humanController) {
        tetrisControllerArea.addKeyListener(humanController);
    }


    /* informs visualisation of the next block for the player */
    public void updateNextBlockView(Block nextBlock) {

    }

    public void updateView(){

    }

}
