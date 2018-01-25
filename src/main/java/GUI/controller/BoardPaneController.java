package GUI.controller;

import GUI.Block.Block;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import tetrispvp.board.*;
import tetrispvp.network.MessageContext;
import tetrispvp.network.MessageHandler;
import tetrispvp.network.MessageReceiver;
import tetrispvp.network.MessageSender;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class BoardPaneController extends PaneController {

    private static final int blockSize = 23;
    @FXML
    private GridPane gamePane1;
    @FXML
    private GridPane gamePane2;
    @FXML
    private GridPane blockPane;

    private MessageSender sender;
    private MessageReceiver receiver;
    private ReentrantLock lock = new ReentrantLock();


    @FXML
    private void initialize() {
        gamePane1.setPrefSize(blockSize * 10, blockSize * 18);
        gamePane2.setPrefSize(blockSize * 10, blockSize * 18);
        sender=guiController.network.messageContext().sender();
        receiver=guiController.network.messageContext().receiver();

        //updateGameView(manager.generateBlock(BlockType.O).getInitialShape());
        receiver.expect("sendBoard", new MessageHandler() {
                    @Override
                    public void arrived(String messageName, Object with, MessageContext within) {
                        lock.lock();
                        try {
                            if (messageName.equals("sendBoard")) {
                                updateEnemyGameView((List<List<GridField>>) with);
                            }
                        }
                        finally {
                            lock.unlock();
                        }
                    }

                    @Override
                    public boolean shouldBeForgotten() {
                        return false;
                    }

                    @Override
                    public void wasForgotten() {

                    }
                });

        TetrisBoard ret = TetrisBoardProvider.getTetrisBoard();
        ret.addBoardStateChangedListener(new BoardStateChangedListener() {
            @Override
            public void stateChanged() {
                updateGameView(TetrisBoardProvider.getTetrisBoard().getBoardState());
            }
        });
    }

    protected void addNextBlockToPane(Block nextBlock) {
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
        sender.send("sendBoard",boardMatrix);
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

    private void updateEnemyGameView(List<List<tetrispvp.board.GridField>> boardMatrix) {
        lock.lock();
        try {
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
        finally {
            lock.unlock();
        }
    }
}
