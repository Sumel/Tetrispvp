package aiModule.mocks;

public class GridField {
    class OccupiedException extends Exception{

    }

    private boolean isOccupied;
    private boolean isTempOccupied;

    public GridField(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }

    public GridField(){
        isOccupied = false;
        isTempOccupied = false;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }


    public void setTempOccupied(boolean occupied) throws OccupiedException {
        if(occupied && this.isOccupied){
            throw new OccupiedException();
        }
        this.isTempOccupied = occupied;

    }
}
