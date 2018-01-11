package tetrispvp.board.Mocks;


import com.google.inject.Guice;
import com.google.inject.Injector;
import tetrispvp.board.*;

import java.awt.*;
import java.util.List;
import java.util.Scanner;

public class ConsoleTetrisPlayer {

    public static void main(String[] args) {
        ConsoleTetrisPlayer player = new ConsoleTetrisPlayer();
        player.play();
    }

    private TetrisBoard setUpBoard() {
        TetrisBoard ret = TetrisBoardProvider.getTetrisBoard();
        ret.addBoardStateChangedListener(new BoardStateChangedListener() {
            @Override
            public void stateChanged() {
                drawBoard(ret);
            }
        });
        ret.addBlockCollidedBelowListener(new BlockCollidedBelowListener() {
            @Override
            public void BlockCollidedBelow(List<Point> collidingPoints) {
                ret.spawnNewBlock(new BlockImplementation('I'));
            }
        });
        ret.addGameStateChangedListener(new GameStateChangedListener() {
            @Override
            public void stateChanged(GameState oldState, GameState newState) {
                System.out.println("\nStary stan: " + oldState.toString() + " nowy stan: " + newState.toString());
            }
        });
        return ret;
    }

    private boolean analyzeInput(String input, TetrisBoard board) {
        if (input.equals("l") || input.equals("left")) {
            board.moveLeft();
        } else if (input.equals("r") || input.equals("right")) {
            board.moveRight();
        } else if (input.equals("d") || input.equals("down")) {
            board.moveDown();
        } else if (input.equals("b") || input.equals("bottom")) {
            board.moveToBottom();
        } else if (input.equals("c") || input.equals("clockwise")) {
            board.rotateClockwise();
        } else if (input.equals("cc") || input.toLowerCase().equals("counterclockwise")) {
            board.rotateCounterClockwise();
        } else if (input.equals("s") || input.toLowerCase().equals("spawn")) {
            board.spawnNewBlock(new BlockImplementation('I'));
        } else if (input.equals("f") || input.toLowerCase().equals("flip")) {
            board.flipBoard();
        } else if (input.equals("add")) {
            BoardField field = new BoardField(true, true, Color.gray, -1, false);
            board.addLine(board.getHeight() - 1, field, true);
        } else {
            return false;
        }
        return true;
    }

    private void drawBoard(TetrisBoard board) {
        for (int i = 0; i < 50; ++i) System.out.println();
        List<List<GridField>> fields = board.getBoardState();
        for (int row = 0; row < board.getHeight(); ++row) {
            String rowRepresentation = "";
            for (int col = 0; col < board.getWidth(); ++col) {
                if (fields.get(row).get(col).isOccupied()) {
                    if (fields.get(row).get(col).isLocked()) {
                        if (fields.get(row).get(col).canBeCleared()) {
                            rowRepresentation = rowRepresentation.concat("|x");
                        } else {
                            rowRepresentation = rowRepresentation.concat("|g");
                        }

                    } else {
                        rowRepresentation = rowRepresentation.concat("|o");
                    }

                } else {
                    rowRepresentation = rowRepresentation.concat("|_");
                }
            }
            System.out.println(rowRepresentation);
        }
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        TetrisBoard board = setUpBoard();
        while (!input.equals("q")) {
            input = scanner.nextLine().trim();
            if (analyzeInput(input, board)) {
                //drawBoard(board);
            }
        }
    }


}
