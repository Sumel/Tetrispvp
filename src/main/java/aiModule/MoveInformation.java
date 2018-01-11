package aiModule;

import java.io.Serializable;

public class MoveInformation implements Serializable {
    int position;
    int rotation;

    MoveInformation(int position, int rotation) {
        this.position = position;
        this.rotation = rotation;
    }
}
