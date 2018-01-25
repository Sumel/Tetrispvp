package powerUps;

import java.util.ArrayList;
import java.util.List;

import tetrispvp.board.*;

public class PowerUpLinesClearedListener implements LinesClearedListener {

    public void linesCleared(List<Line> lines) {
        List<GridField> fields = new ArrayList<GridField>();
        for(Line line : lines){
            fields.addAll(line.getFieldsInLine());
        }
        powerUps.PowerUpManager.getPowerUpManager().lineCleared(fields);
    }

}
