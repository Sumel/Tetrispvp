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

    public Score(Coefficients coefficients) {
        this.coefficients = coefficients;
    }

    public Score(double value) {
        this.value = value;
    }

    public void compareWithNewBoard(BoardStatus newBoardStatus) {
        this.deletedLines = newBoardStatus.getCompleteLines();
        this.holes = newBoardStatus.getHoles();
        this.bumpiness = newBoardStatus.getBumpiness();
        this.height = newBoardStatus.getAggregateHeight();

        this.value = getValue();
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
