import java.io.Serializable;

public class ChatData implements Serializable {
    String text;
    public ChatData(String text){
        this.text=text;
    }
}