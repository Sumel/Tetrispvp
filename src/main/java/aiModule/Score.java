package aiModule;

import aiModule.mocks.GridField;

import java.util.List;

public class Score {
    public static Coefficients defaultCoefficients =
            new Coefficients(-.5, 0.5, -0.5, -0.5);


    private int totalGridFields;
    private Coefficients coefficients;

    public int deletedLines;
    public int holes;
    public int bumpiness;
    public int height;

    private double value;

    public Score(BoardStatus originalBoardStatus, Coefficients coefficients) {
        this.totalGridFields = countGridFields(originalBoardStatus);
        this.coefficients = coefficients;
    }

    public Score(double value) {
        this.value = value;
    }

    static int countGridFields(BoardStatus originalBoardStatus) {
        int total = 0;
        for (List<GridField> line : originalBoardStatus.value) {
            for (GridField gridField : line) {
                if (gridField.isOccupied()) {
                    total += 1;
                }
            }
        }
        return total;
    }

    public void compareWithNewBoard(BoardStatus newBoardStatus) {
        this.deletedLines = getDeletedLines(newBoardStatus);
        this.holes = newBoardStatus.getHoles();
        this.bumpiness = newBoardStatus.getBumpiness();
        this.height = newBoardStatus.getAggregateHeight();

        this.value = getValue();
    }

    public int getDeletedLines(BoardStatus boardStatus) {
        int newTotalGridFields = countGridFields(boardStatus);
        int deletedLines = 0;
        if (newTotalGridFields < this.totalGridFields) {
            deletedLines = this.totalGridFields - newTotalGridFields;
            deletedLines /= 10;
        }
        return deletedLines;
    }

    public double getValue() {
        return this.bumpiness * this.coefficients.bumpiness
                + this.holes * this.coefficients.holes
                + this.height * this.coefficients.height
                + this.deletedLines * this.coefficients.lines;
    }

    public boolean hasBetterValue(Score score) {
        return this.value > score.value;
    }
}
