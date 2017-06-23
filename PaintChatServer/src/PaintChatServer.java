import kotlin.Unit;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class PaintChatServer {

    public static final int PORT = 7777;    //서버 포트
    public static void main(String args[]) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);  //서버 소켓 생성
            while(true){
                Socket socket = serverSocket.accept();  //클라이언트가 연결될 때까지 대기

                InputStream inputStream=socket.getInputStream(); //입력스트림 생성
                ObjectInputStream objectInputStream=new ObjectInputStream(inputStream); //Object 입력 스트림 생성
                Object object =objectInputStream.readObject(); //입력스트림으로 부터 데이터 수신

                ChatData chatData=(ChatData)object; //클라이언트에 받은 데이터를 (chatData)object 형으로 입력

                OutputStream outputStream = socket.getOutputStream();   //출력스트림 생성
                ObjectOutputStream objectOutputStream=new ObjectOutputStream(outputStream); //Object 를 출력할 스트림 생성
                objectOutputStream.writeObject(chatData);  // ChatData Object 전송
                //objectOutputStream.writeObject(new PaintData(10,20,30));

                objectInputStream.close();
                objectOutputStream.close(); //스트림 종료
                socket.close(); //소켓 종료
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}

