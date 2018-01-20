package tetrispvp.board;

import com.google.inject.Inject;
import tetrispvp.network.MessageContext;
import tetrispvp.network.MessageHandler;
import tetrispvp.network.MessageReceiver;
import tetrispvp.network.MessageSender;

import javafx.scene.paint.Color;
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
                    BoardField field = new BoardField(true, true, Color.GRAY, -1, false);
                    for (int i = 0; i < (Integer) with; ++i) {
                        board.addLine(board.getHeight() - 1, field, true);
                    }
                }
            }

            @Override
            public boolean shouldBeForgotten() {
                return false;
            }

            @Override
            public void wasForgotten() {

            }
        });
    }

    private void sendGreyLines(int linesNum) {
        sender.send("linesCleared", linesNum);
    }
}
