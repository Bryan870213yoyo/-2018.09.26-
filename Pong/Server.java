import javafx.application.Platform;
import javafx.scene.control.Label;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class Server implements Runnable{

    @Override
    public void run() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Scanner scn = new Scanner(System.in);
                //取得Label_serverStatus已進行狀態顯示同步
                Label Label_serverStatus = (Label)SaveReference.getReference("Label_serverStatus");
                //建立 Socket 以及傳輸資訊時所需的所有媒介
                ServerSocket svs;
                String sendMessage = "";
                //縮小了存取訊息的空間，為了更有效率的執行
                byte getMessage[] = new byte[20];
                InputStream in;
                OutputStream out;

                try {
                    svs = new ServerSocket(2525);
                    Label_serverStatus.setText("等待連線中...");
                    System.out.println("等待連線中... SERVER");
                    Socket s = svs.accept();
                    Label_serverStatus.setText("連線成功!");
                    System.out.println("連線成功! SERVER");

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
                    Label_serverStatus.setText("已離開聊天室");

                }catch (SocketException e){
                    Label_serverStatus.setText("斷線了");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
