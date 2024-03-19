package algonquin.cst2335.ghos0042;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

@Entity
public class ChatMessage {
    @ColumnInfo(name="message")
    private String message;
    private String timeSent;
    private boolean isSentButton;

    public ChatMessage(String m, String t, boolean sent) {
        message = m;
        timeSent = t;
        isSentButton = sent;
    }
    public String getMessage() {
        return message;
    }

    public String getTimeSent() {
        return timeSent;
    }

    public boolean isSentButton() {
        return isSentButton;
    }

}