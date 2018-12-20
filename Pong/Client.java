import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class Client extends Thread{

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

    Label clientStatus = (Label)SaveReference.getReference("clientStatus");
    Button BIG_Client = (Button)SaveReference.getReference("BIG_Client");

    @Override
    public void run() {

        System.out.println("clientStatus:"+clientStatus);

        try {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    clientStatus.setText("client:連線中...");
                }
            });


            System.out.println("client:連線中...");
            s = new Socket(serverIP,2525);

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    clientStatus.setText("成功連線!");
                    BIG_Client.setText("Start!");
                    BIG_Client.setDisable(false);
                }
            });

            System.out.println("client:成功連線!");

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

        }catch (SocketException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

}
