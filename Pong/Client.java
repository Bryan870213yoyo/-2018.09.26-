import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class Client {
    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);
        //建立 Socket 以及傳輸資訊時所需的所有媒介
        Socket s;
        String sendMessage="";
        //縮小了存取訊息的空間，為了更有效率的執行
        byte getMessage[] = new byte[20];
        InputStream in;
        OutputStream out;
        //用SaveReference把使用者輸入的IP抓過來用
        String serverIP = (String)SaveReference.getReference("server's IP");
        //連線狀況
        String clientStatus = "";
        SaveReference.addReference("clientStatus",clientStatus);

        try {
            clientStatus = "連線中...";
            s = new Socket(serverIP,2525);
            clientStatus = "成功連線!";

            in = s.getInputStream();
            out = s.getOutputStream();

            ReceiveMessage r = new ReceiveMessage(getMessage,in);
            Thread receiveThread = new Thread(r);
            receiveThread.start();

            while(!sendMessage.equals("end")){
                sendMessage = scn.nextLine();
                out.write(sendMessage.getBytes());
            }

            receiveThread.stop();
            in.close();
            out.close();
            s.close();
            clientStatus = "已離開聊天室";

        }catch (SocketException e){
            clientStatus = "斷線了";
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
