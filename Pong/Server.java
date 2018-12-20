import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class Server extends Thread{

    Scanner scn = new Scanner(System.in);//建立 Socket 以及傳輸資訊時所需的所有媒介
    ServerSocket svs;
    String sendMessage = "";//縮小了存取訊息的空間，為了更有效率的執行
    byte getMessage[] = new byte[20];
    InputStream in;
    OutputStream out;

    Label serverStatus = (Label)SaveReference.getReference("serverStatus");
    Button BIG_Server = (Button)SaveReference.getReference("BIG_Server");

    @Override
    public void run() {

        System.out.println("serverStatus:"+serverStatus);

        try {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    serverStatus.setText("server:等待連線...");
                }
            });
            System.out.println("server:等待連線...");
            svs = new ServerSocket(2525);
            Socket s = svs.accept();

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    serverStatus.setText("成功連線!");
                    BIG_Server.setText("Start!");
                    BIG_Server.setDisable(false);
                }
            });

            System.out.println("client:成功連線!");

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

        }catch (SocketException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
