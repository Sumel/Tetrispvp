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

    private static final int BRICK_SIZE = 20;
    @FXML
    public GridPane gamePane1;
    @FXML
    public GridPane gamePane2;
    @FXML
    public GridPane blockPane;

    @FXML
    private void initialize() {
        BlockManager manager = new BlockManager();
        addBlockToPane(manager.getRandomBlock());
        updateGameView(manager.generateBlock(BlockType.O).getInitialShape());
        updateEnemyGameView(manager.generateBlock(BlockType.O).getInitialShape());
    }


    private void addBlockToPane(Block nextBrick) {
        blockPane.getChildren().clear();
        for (int i = 0; i < nextBrick.getInitialShape().size(); i++) {
            for (int j = 0; j < nextBrick.getInitialShape().get(i).size(); j++) {
                Rectangle rectangle = new Rectangle(BRICK_SIZE, BRICK_SIZE);
                setRectangle(nextBrick.getInitialShape().get(i).get(j).getColor(), rectangle);
                if (nextBrick.getInitialShape().get(i).get(j).getBlockType() != null) {
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

        for (int i = 2; i < boardMatrix.size(); i++) {
            for (int j = 0; j < boardMatrix.get(i).size(); j++) {
                Rectangle rectangle = new Rectangle(BRICK_SIZE, BRICK_SIZE);
                if (boardMatrix.get(i).get(j).getBlockType() == null)
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
                Rectangle rectangle = new Rectangle(BRICK_SIZE, BRICK_SIZE);
                if (boardMatrix.get(i).get(j).getBlockType() == null)
                    setRectangle(Color.TRANSPARENT, rectangle);
                else
                    setRectangle(boardMatrix.get(i).get(j).getColor(), rectangle);
                gamePane2.add(rectangle, j, i);
            }
        }
    }
}
