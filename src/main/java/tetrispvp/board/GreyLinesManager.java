package tetrispvp.board;

import com.google.inject.Inject;
import tetrispvp.board.Mocks.MessageContext;
import tetrispvp.board.Mocks.MessageHandler;
import tetrispvp.board.Mocks.MessageReceiver;
import tetrispvp.board.Mocks.MessageSender;

import java.awt.*;
import java.util.List;

public class GreyLinesManager {
    private MutableBoard board;
    private MessageSender sender;
    private MessageReceiver receiver;

    @Inject
    public GreyLinesManager(MutableBoard board, MessageSender sender, MessageReceiver receiver) {
        this.board = board;
        this.sender = sender;
        this.receiver = receiver;
        board.addLinesClearedListener(new LinesClearedListener() {
            @Override
            public void linesCleared(List<Line> lines) {
                sendGreyLines(lines.size());
            }
        });
        receiver.expect("linesCleared", new MessageHandler() {
            @Override
            public void arrived(String messageName, Object with, MessageContext within) {
                if (messageName.equals("linesCleared")) {
                    BoardField field = new BoardField(true, true, Color.gray, -1, false);
                    for (int i = 0; i < (Integer) with; ++i) {
                        board.addLine(board.getHeight()-1, field,true);
                    }
                }
            }
        });
    }

    private void sendGreyLines(int linesNum) {
        sender.send("linesCleared", linesNum);
    }
}
