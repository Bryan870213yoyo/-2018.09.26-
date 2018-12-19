import javafx.scene.control.Label;
import javafx.scene.control.Labeled;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class Client implements Runnable{
    @Override
    public void run() {
        Scanner scn = new Scanner(System.in);
        //取得Label_serverStatus已進行狀態顯示同步
        Label Label_clientStatus = (Label)SaveReference.getReference("Label_clientStatus");
        //建立 Socket 以及傳輸資訊時所需的所有媒介
        Socket s;
        String sendMessage="";
        //縮小了存取訊息的空間，為了更有效率的執行
        byte getMessage[] = new byte[20];
        InputStream in;
        OutputStream out;
        //用SaveReference把使用者輸入的IP抓過來用
        String serverIP = (String)SaveReference.getReference("server's IP");


        try {
            Label_clientStatus.setText("連線中...");
            System.out.println("等待連線中... CLIENT");
            s = new Socket(serverIP,2525);
            Label_clientStatus.setText("成功連線!");
            System.out.println("連線成功! CLIENT");

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
            Label_clientStatus.setText("已離開聊天室");

        }catch (SocketException e){
            Label_clientStatus.setText("斷線了");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
