package algonquin.cst2335.ghos0042;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;

import android.os.Bundle;

public class ChatRoomViewModel extends ViewModel {
    private final MutableLiveData<ArrayList<ChatMessage>> messages = new MutableLiveData<>(new ArrayList<>());

    public ChatRoomViewModel() {
        messages.setValue(new ArrayList<>());
    }
    public MutableLiveData<ArrayList<ChatMessage>> getMessages() {
        return messages;
    }

    public void addMessage(ChatMessage message) {
        ArrayList<ChatMessage> currentMessages = messages.getValue();
        if (currentMessages == null) {
            currentMessages = new ArrayList<>();
        }
        currentMessages.add(message);
        messages.setValue(currentMessages);
    }

}