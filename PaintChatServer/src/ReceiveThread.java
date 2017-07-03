import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ReceiveThread extends Thread {
    private Socket socket;

    public ReceiveThread(Socket socket){
        this.socket=socket;
    }

    public void run(){
        try {
            ObjectInputStream objectInputStream=new ObjectInputStream(socket.getInputStream());

            while(true){
                Object object =objectInputStream.readObject(); //입력스트림으로 부터 데이터 수신
                SendObject(object);//대화 내용
            }
        }catch (Exception e){
            e.printStackTrace();
            UserList.list.remove(this.socket);
        }
    }

    //대화 내용 클라이언트에 보내기
    public void SendObject(Object object){
        try {
            for (int i = 0; i < UserList.list.size(); i++) {
                ObjectOutputStream objectOutputStream=new ObjectOutputStream(UserList.list.get(i).getOutputStream());
                objectOutputStream.writeObject(object);  // ChatData Object 전송;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
