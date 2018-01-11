package aiModule;

import aiModule.mocks.GridField;

import java.util.List;

class Score{

    private int totalGridFields;
    private Coefficients coefficients;

    private int deletedLines;
    private int holes;
    private int bumpiness;
    private int height;

    private double value;

    Score(BoardStatus originalBoardStatus, Coefficients coefficients) {
        this.totalGridFields = countGridFields(originalBoardStatus);
        this.coefficients = coefficients;
    }

    Score(double value){
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

    private int getDeletedLines(BoardStatus boardStatus) {
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

    public boolean hasBetterValue(Score score){
        return this.value > score.value;
    }
}
