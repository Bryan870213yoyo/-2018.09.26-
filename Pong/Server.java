import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class Server {
    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);
        //建立 Socket 以及傳輸資訊時所需的所有媒介
        ServerSocket svs;
        String sendMessage = "";
        //縮小了存取訊息的空間，為了更有效率的執行
        byte getMessage[] = new byte[20];
        InputStream in;
        OutputStream out;
        String serverStatus = "";
        SaveReference.addReference("serverStatus",serverStatus);

        try {
            svs = new ServerSocket(2525);
            serverStatus = "等待連線中...";
            Socket s = svs.accept();
            serverStatus = "成功連線!";

            in = s.getInputStream();
            out = s.getOutputStream();

            ReceiveMessage r = new ReceiveMessage(getMessage,in);
            Thread receiveThread = new Thread(r);
            receiveThread.start();
            //沒有收到結束指令就一直等待接收訊息
            while(!sendMessage.equals("end")){
                sendMessage = scn.nextLine();
                out.write(sendMessage.getBytes());
            }

            receiveThread.stop();
            in.close();
            out.close();
            s.close();
            serverStatus = "已離開聊天室";

        }catch (SocketException e){
            serverStatus = "斷線了";
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
