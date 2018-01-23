package aiModule;

import aiModule.mocks.GridField;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class BoardAI implements BoardStatus{
    private final static int boardWidth = 10;
    private final static int boardHeight = 22;
    private List<List<GridField>> boardStatus;
    private boolean alreadyMoved = false;
    private List<List<GridField>> copy;
    private int[] position;

    public List<List<GridField>> getCopy() {
        return copy;
    }

    public BoardAI(){this.boardStatus = new ArrayList<>();}

    public BoardAI(List<List<GridField>> boardStatus){
        this.boardStatus = boardStatus;
        this.position = findTertominoPosition();
    }

    public BoardAI(BoardAI boardAI){
        this.boardStatus = boardAI.boardStatus;
        this.position = findTertominoPosition();
    }

    public GridField getFieldAtPosition(int x, int y) {
        return boardStatus.get(x).get(y);
    }

    public List<List<GridField>> getBoardState() {
        return boardStatus;
    }

    public int getWidth() {
        return boardWidth;
    }

    public int getHeight() {
        return boardHeight;
    }

    public void setCopy(){
        copy = new ArrayList<>();

        for(int c=0; c<boardWidth; c++){
            List<GridField> row = new ArrayList<>(boardStatus.get(c));
            copy.add(row);
        }
    }

    public void moveLeft(){
        setCopy();

        if (checkIfCanGoLeft()) {
            for (int i = 0; i < 4; i++) {
                boardStatus.get(position[2 * i + 1]-1).set(position[2 * i], copy.get(position[2 * i + 1]).get(position[2 * i]));
                if (position[2 * i + 1] + 1 > 9)
                    boardStatus.get(position[2 * i + 1]).set(position[2 * i], (new GridField(false)));
                else
                    boardStatus.get(position[2 * i + 1]).set(position[2 * i], copy.get(position[2 * i + 1]+1).get(position[2 * i]));
                position[2 * i+1]--;
            }
        }
    }

    public void moveRight(){
        setCopy();

        if (checkIfCanGoRight()) {
            for (int i = 0; i < 4; i++) {
                boardStatus.get(position[2 * i + 1]+1).set(position[2 * i], copy.get(position[2 * i + 1]).get(position[2 * i]));
                if (position[2 * i + 1] - 1 < 0)
                    boardStatus.get(position[2 * i + 1]).set(position[2 * i], (new GridField(false)));
                else
                    boardStatus.get(position[2 * i + 1]).set(position[2 * i], copy.get(position[2 * i + 1]-1).get(position[2 * i]));
                position[2 * i+1]++;
            }
        }
    }

    public void rotate(){
        setCopy();

        int offset = Math.min(position[1], Math.min(position[3], Math.min(position[5], position[7])));         //na jakiej pozycji zaczyna się klocek, najbardziej na lewo wysunięta część

        switch (position[8]){       //wymiar
            case 2:
                break;

            case 3:
                if (!alreadyMoved) {
                    moveDown();
                    alreadyMoved = true;
                }
                boardStatus.get(offset).set(0,copy.get(offset).get(2));
                boardStatus.get(offset+1).set(0,copy.get(offset).get(1));
                boardStatus.get(offset+2).set(0,copy.get(offset).get(0));
                boardStatus.get(offset).set(1,copy.get(offset+1).get(2));
                boardStatus.get(offset+1).set(1,copy.get(offset+1).get(1));
                boardStatus.get(offset+2).set(1,copy.get(offset+1).get(0));
                boardStatus.get(offset).set(2,copy.get(offset+2).get(2));
                boardStatus.get(offset+1).set(2,copy.get(offset+2).get(1));
                boardStatus.get(offset+2).set(2,copy.get(offset+2).get(0));

                updatePosition(offset, 3);
                break;

            case 4:
                if (!alreadyMoved) {
                    moveDown();
                    moveDown();
                    alreadyMoved = true;
                }
                boardStatus.get(offset).set(0,copy.get(offset).get(3));
                boardStatus.get(offset+1).set(0,copy.get(offset).get(2));
                boardStatus.get(offset+2).set(0,copy.get(offset).get(1));
                boardStatus.get(offset+3).set(0,copy.get(offset).get(0));

                boardStatus.get(offset).set(1,copy.get(offset+1).get(3));
                boardStatus.get(offset+1).set(1,copy.get(offset+1).get(2));
                boardStatus.get(offset+2).set(1,copy.get(offset+1).get(1));
                boardStatus.get(offset+3).set(1,copy.get(offset+1).get(0));

                boardStatus.get(offset).set(2,copy.get(offset+2).get(3));
                boardStatus.get(offset+1).set(2,copy.get(offset+2).get(2));
                boardStatus.get(offset+2).set(2,copy.get(offset+2).get(1));
                boardStatus.get(offset+3).set(2,copy.get(offset+2).get(0));

                boardStatus.get(offset).set(3,copy.get(offset+3).get(3));
                boardStatus.get(offset+1).set(3,copy.get(offset+3).get(2));
                boardStatus.get(offset+2).set(3,copy.get(offset+3).get(1));
                boardStatus.get(offset+3).set(3,copy.get(offset+3).get(0));

                updatePosition(offset, 4);
                break;
        }
    }

    private void updatePosition(int offset, int n){
        int index = 0;
        for (int i=0; i<n; i++){
            for (int j=0; j<n; j++){
                if (boardStatus.get(offset+i).get(j).isOccupied()){
                    position[2*index]=j;
                    position[2*index+1] = offset + i;
                    index++;
                }
            }
        }
    }

    public void moveDown(){
        setCopy();

        if (checkIfCanGoDown()) {
            for (int i = 0; i < 4; i++) {
                boardStatus.get(position[2 * i + 1]).set(position[2 * i] + 1, copy.get(position[2 * i + 1]).get(position[2 * i]));
                if (position[2 * i] - 1 < 0)
                    boardStatus.get(position[2 * i + 1]).set(position[2 * i], (new GridField(false)));
                else
                    boardStatus.get(position[2 * i + 1]).set(position[2 * i], copy.get(position[2 * i + 1]).get(position[2 * i] - 1));
                position[2 * i]++;
            }
        }
    }

    private int[] findBase(){     //zwraca tablicę - pozycje części klocka, które są podstawą
        int largestIndexX = 0;                  //wiersz, w którym się znajduje część klocka położona najbliżej podstawy planszy
        int[] finalPosition = {-1,-1,-1,-1,-1,-1,-1,-1};
        for (int i=0; i<4; i++)
            if (position[2*i] > largestIndexX)
                largestIndexX = position[2*i];
        int l=0;
        for (int i=0; i<4; i++){
            if (position[2*i] == largestIndexX) {
                finalPosition[2*l] = position[2*i];
                finalPosition[2*l+1] = position[2*i+1];
                l++;
            }

        }
        for (int i = 0; i< 4; i++){
            int k = position[2*i+1];
            for (int j = i+1; j<4; j++){
                if (k == position[2*j+1] || position[2*j]==largestIndexX)
                    break;
                finalPosition[2*l] = position[2*i];
                finalPosition[2*l+1] = position[2*i+1];
                l++;
            }
        }
        if(position[8] == 2){
            finalPosition[4] = -1;
            finalPosition[5] = -1;
        }
        return finalPosition;
    }

    private boolean checkIfCanGoDown(){
        int[] base = findBase();
        for (int i=0; i<4; i++){
            if (base[2*i] >= 21)
                return false;
            if (base[2*i] != -1 && boardStatus.get(base[2*i+1]).get(base[2*i]+1).isOccupied())
                return false;
        }
        return true;
    }

    private boolean checkIfCanGoLeft(){
        int left = 10;
        for (int i=0; i<4; i++)
            if (position[2*i+1] < left)
                left = position[2*i+1];
        if (left == 0)
            return false;
        return true;
    }

    private boolean checkIfCanGoRight(){
        int right = 0;
        for (int i=0; i<4; i++)
            if (position[2*i+1] > right)
                right = position[2*i+1];
        if (right == 9)
            return false;
        return true;
    }

    public void register(){

    }

    public int[] findTertominoPosition(){
        int dimension;
        int leftTop = -1;
        int rightTop, rightBottom;
        int leftBottom = -1;
        int counter = 0;
        int[] position_ = new int[9];
        for (int j=0; j<boardWidth; j++){
            if (boardStatus.get(j).get(0).isOccupied()){
                if (counter == 0)
                    leftTop = j;
                position_[2*counter]=0;
                position_[2*counter+1]=j;
                counter++;
            }
        }
        rightTop = leftTop + counter - 1;

        int temp = counter;
        counter = 0;
        for (int j=0; j<boardWidth; j++){
            if (boardStatus.get(j).get(1).isOccupied()){
                if (counter == 0)
                    leftBottom = j;
                position_[2*temp]=1;
                position_[2*temp+1]=j;
                counter++;
                temp++;
            }
        }
        rightBottom = leftBottom + counter - 1;

        if (leftTop == -1)
            dimension = 4;
        else if (leftBottom == leftTop && rightBottom == rightTop)
            dimension = 2;
        else
            dimension = 3;

        position_[8]=dimension;

        return position_;
    }

    private boolean isLine(int row){
        for (int i=0; i<boardWidth; i++){
            if (!boardStatus.get(i).get(row).isOccupied())
                return false;
        }
        return true;
    }

    private int getColumnHeight(int column){        //column - [0, 1, ..., 9]
        int r = 0;
        for (; r<boardHeight && !boardStatus.get(column).get(r).isOccupied(); r++);
        return boardHeight - r;
    }

    public int getAggregateHeight(){
        int total = 0;
        for (int c = 0; c<boardWidth; c++)
            total+=this.getColumnHeight(c);
        return total;
    }

    public int getCompleteLines(){
        int total = 0;
        for (int r=0; r<boardHeight; r++)
            if (this.isLine(r))
                total++;
        return total;
    }

    public int getHoles(){
        int total = 0;
        for (int c=0; c<boardWidth; c++){
            boolean check = false;
            for (int r=0; r<boardHeight; r++){
                if(boardStatus.get(c).get(r).isOccupied())
                    check = true;
                else if (!boardStatus.get(c).get(r).isOccupied() && check)
                    total++;
            }
        }
        return total;
    }

    public int getBumpiness(){
        int total = 0;
        for (int c = 0; c<boardWidth-1; c++)
            total+=Math.abs(this.getColumnHeight(c)-this.getColumnHeight(c+1));
        return total;
    }

    public void showBoard(){
        for(int r=0; r<boardHeight; r++){
            for (int c=0; c<boardWidth; c++) {
                if (boardStatus.get(c).get(r).isOccupied())
                    System.out.print("1 ");
                else
                    System.out.print("o ");
            }
            System.out.println();
        }
    }

    public void add(List<GridField> row){
        boardStatus.add(row);
    }

    public static BoardAI deepClone(BoardAI object) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (BoardAI) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int[] getPosition() {
        return position;
    }
}
