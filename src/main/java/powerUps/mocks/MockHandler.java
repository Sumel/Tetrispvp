package powerUps.mocks;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;
import tetrispvp.board.BoardField;
import tetrispvp.board.MutableBoard;
import tetrispvp.board.GridField;

public class MockHandler implements tetrispvp.network.MessageHandler {
    private List<String> allMessages = new ArrayList<>();
    private MutableBoard board;

    public MockHandler(MutableBoard board) {
        this.board = board;
    }

    @Override
    public void arrived(String messageName, Object with, tetrispvp.network.MessageContext within) {
        allMessages.add(messageName);
        System.out.println(with);
        getAllMessages();
        if(messageName.equals("addLines")){
            GridField gf = new BoardField(true, true, Color.BLACK, -1, false);
            board.addLine((int)with, gf, true);
        } else if (messageName.equals("flipBoard")){
            board.flipBoard();
        }
        else{
            //do nothing
        }
    }

    public List<String> getAllMessages() {
        for(String s: allMessages)
            System.out.print(s + " ");
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