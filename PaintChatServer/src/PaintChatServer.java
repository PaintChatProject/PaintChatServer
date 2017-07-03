import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class PaintChatServer {

    public static final int PORT = 7777;    //서버 포트
    public static void main(String args[]) {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(PORT);  //서버 소켓 생성
            while(true){
                Socket socket = serverSocket.accept();  //클라이언트가 연결될 때까지 대기

                UserList.list.add(socket);

                System.out.println("C");

                new ReceiveThread(socket).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

