import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

class EchoThread extends Thread{

    private ArrayList<ObjectOutputStream> list; //

    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private String name;

    public EchoThread(ArrayList<ObjectOutputStream> list,ObjectInputStream objectInputStream,ObjectOutputStream objectOutputStream){
        this.list=list;
        this.objectInputStream=objectInputStream;
        this.objectOutputStream=objectOutputStream;
    }

    public void run(){
        try{
            this.name=(String) objectInputStream.readObject();

            ChatData chatData=new ChatData("["+name+"] 님이 입장하셨습니다.");
            SendObject(chatData); //입장

            while(true){
                Object object =objectInputStream.readObject(); //입력스트림으로 부터 데이터 수신

                SendObject(object);//대화 내용
            }
        }catch (Exception e){
            //if(object==null){
                ChatData chatData=new ChatData("["+name+"] 님이 퇴장하셨습니다.");
                SendObject(chatData);//퇴장
                list.remove(objectOutputStream);
                //objectInputStream.close();
                //objectOutputStream.close();

                //break;
           // }
        }
    }

    //대화 내용 클라이언트에 보내기
    public void SendObject(Object object){
        try {
            for (int i = 0; i < list.size(); i++) {
                list.get(i).writeObject(object);  // ChatData Object 전송;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

public class PaintChatServer {

    public static final int PORT = 7777;    //서버 포트
    public static void main(String args[]) {
        ServerSocket serverSocket = null;

        ArrayList<ObjectOutputStream> list=new ArrayList<ObjectOutputStream>();

        try {
            serverSocket = new ServerSocket(PORT);  //서버 소켓 생성
            while(true){
                Socket socket = serverSocket.accept();  //클라이언트가 연결될 때까지 대기

                InputStream inputStream=socket.getInputStream(); //입력스트림 생성
                ObjectInputStream objectInputStream=new ObjectInputStream(inputStream); //Object 입력 스트림 생성

                OutputStream outputStream = socket.getOutputStream();   //출력스트림 생성
                ObjectOutputStream objectOutputStream=new ObjectOutputStream(outputStream); //Object 를 출력할 스트림 생성
                //objectOutputStream.writeObject(new PaintData(10,20,30));

                list.add(objectOutputStream);

                new EchoThread(list,objectInputStream,objectOutputStream).start();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

