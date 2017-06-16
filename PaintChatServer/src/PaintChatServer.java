import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class PaintChatServer {
    public static final int PORT = 7777;    //서버 포트
    public static void main(String args[]) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);  //서버 소켓 생성
            Socket socket = serverSocket.accept();  //클라이언트가 연결될 때까지 대기
            OutputStream outputStream = socket.getOutputStream();   //출력스트림 생성
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(outputStream); //Object 를 출력할 스트림 생성
            objectOutputStream.writeObject(new ChatData("TCP Chat TEST"));  // ChatData Object 전송
            //objectOutputStream.writeObject(new PaintData(10,20,30));
            objectOutputStream.close(); //스트림 종료
            socket.close(); //소켓 종료
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
