package powerUps.mocks;

import java.util.Map;

public class NetworkModule implements MessageSender{
    private NetworkModule opponent;

    @Override
    public void send(String messageName, Object with){
        opponent.send(messageName, with);
    }

    public NetworkModule getOpponent(){
        return opponent;
    }

    public void setOpponent(NetworkModule opponent) {
        this.opponent = opponent;
    }
}