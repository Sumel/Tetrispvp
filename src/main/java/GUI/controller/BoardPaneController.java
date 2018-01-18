package GUI.controller;

import GUI.Block.Block;
import GUI.Block.BlockManager;
import GUI.Block.BlockType;
import GUI.Block.GridField;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.List;

public class BoardPaneController extends PaneController {

    private static final int blockSize = 20;
    @FXML
    public GridPane gamePane1;
    @FXML
    public GridPane gamePane2;
    @FXML
    public GridPane blockPane;

    @FXML
    private void initialize() {

        gamePane1.setPrefSize(blockSize*10,blockSize*18);
        gamePane2.setPrefSize(blockSize*10,blockSize*18);

        BlockManager manager = new BlockManager();
        addBlockToPane(manager.getRandomBlock());
        //updateGameView(manager.generateBlock(BlockType.O).getInitialShape());
        updateEnemyGameView(manager.generateBlock(BlockType.O).getInitialShape());

        /*TetrisBoard ret = TetrisBoardProvider.getTetrisBoard();
        ret.addBoardStateChangedListener(new BoardStateChangedListener() {
            @Override
            public void stateChanged() {
                updateGameView(TetrisBoardProvider.getTetrisBoard().getBoardState());
            }
        });*/
    }


    private void addBlockToPane(Block nextBrick) {
        blockPane.getChildren().clear();
        for (int i = 0; i < nextBrick.getInitialShape().size(); i++) {
            for (int j = 0; j < nextBrick.getInitialShape().get(i).size(); j++) {
                Rectangle rectangle = new Rectangle(blockSize, blockSize);
                setRectangle(nextBrick.getInitialShape().get(i).get(j).getColor(), rectangle);
                if (nextBrick.getInitialShape().get(i).get(j).isOccupied()) {
                    blockPane.add(rectangle, j, i);
                }
            }
        }
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
