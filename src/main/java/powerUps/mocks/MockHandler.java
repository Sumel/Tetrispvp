package powerUps.mocks;

import java.util.ArrayList;
import java.util.List;

public class MockHandler implements MessageHandler{
    private List<String> allMessages = new ArrayList<>();
    @Override
    public void arrived(String messageName, Object with, MessageContext within) {
        allMessages.add(messageName);
    }

    public List<String> getAllMessages() {
        return allMessages;
    }

    public boolean checkIfGot(String message){
        return allMessages.contains(message);
    }

    @Override
    public boolean shouldBeForgotten() {
        return false;
    }

    @Override
    public void wasForgotten() {
        //do nothing
    }
}