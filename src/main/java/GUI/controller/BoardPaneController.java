package GUI.controller;

import GUI.Block.*;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.List;

public class BoardPaneController extends PaneController {

    private static final int blockSize = 23;
    @FXML
    public GridPane gamePane1;
    @FXML
    public GridPane gamePane2;
    @FXML
    public GridPane blockPane;

    @FXML
    private void initialize() {

        gamePane1.setPrefSize(blockSize * 10, blockSize * 18);
        gamePane2.setPrefSize(blockSize * 10, blockSize * 18);

        BlockManager manager = new BlockManager();
        addNextBlockToPane(manager.getRandomBlock());
        //updateGameView(manager.generateBlock(BlockType.O).getInitialShape());
        BlockImplementation block = new BlockImplementation(BlockType.I);
        updateEnemyGameView(block.getBoardFields());

        /*TetrisBoard ret = TetrisBoardProvider.getTetrisBoard();
        ret.addBoardStateChangedListener(new BoardStateChangedListener() {
            @Override
            public void stateChanged() {
                updateGameView(TetrisBoardProvider.getTetrisBoard().getBoardState());
            }
        });*/
    }


    private void addNextBlockToPane(Block nextBlock) {
        blockPane.getChildren().clear();
        nextBlock.rotateClockwise();
        for (int i = 0; i < nextBlock.getBoardFields().size(); i++) {
            for (int j = 0; j < nextBlock.getBoardFields().get(i).size(); j++) {
                Rectangle rectangle = new Rectangle(blockSize, blockSize);
                setRectangle(nextBlock.getBoardFields().get(i).get(j).getColor(), rectangle);
                if (nextBlock.getBoardFields().get(i).get(j).isOccupied()) {
                    blockPane.add(rectangle, j, i);
                }
            }
        }
        nextBlock.rotateCounterClockwise();
    }

    private void setRectangle(Color color, Rectangle rectangle) {
        rectangle.setFill(color);
        rectangle.setArcHeight(9);
        rectangle.setArcWidth(9);
    }


    private void updateGameView(List<List<GridField>> boardMatrix) {
        gamePane1.getChildren().clear();
        for (int i = 2; i < boardMatrix.size(); i++) {
            for (int j = 0; j < boardMatrix.get(i).size(); j++) {
                Rectangle rectangle = new Rectangle(blockSize, blockSize);
                if (!boardMatrix.get(i).get(j).isOccupied())
                    setRectangle(Color.TRANSPARENT, rectangle);
                else
                    setRectangle(boardMatrix.get(i).get(j).getColor(), rectangle);
                gamePane1.add(rectangle, j, i);
            }
        }
    }

    private void updateEnemyGameView(List<List<GridField>> boardMatrix) {
        gamePane2.getChildren().clear();
        for (int i = 2; i < boardMatrix.size(); i++) {
            for (int j = 0; j < boardMatrix.get(i).size(); j++) {
                Rectangle rectangle = new Rectangle(blockSize, blockSize);
                if (!boardMatrix.get(i).get(j).isOccupied())
                    setRectangle(Color.TRANSPARENT, rectangle);
                else
                    setRectangle(boardMatrix.get(i).get(j).getColor(), rectangle);
                gamePane2.add(rectangle, j, i);
            }
        }
    }
}
