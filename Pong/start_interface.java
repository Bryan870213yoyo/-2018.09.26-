import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;
import java.util.Scanner;

public class start_interface implements Initializable {
    //導入Fxml資料
    @FXML Button BIG_Server ,BIG_Client ,getIP ,setServer ,connectToServer;
    @FXML Label Label_serverIP, serverStatus, clientStatus;
    @FXML TextField clientIP;
    //建立必要數據
    InetAddress addr;
    String serverIP;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Scanner scn = new Scanner(System.in);

    }
//==============BIG_Server Button的細項控制=========================
    //移上去時變大
    public void serverButtonEntered(MouseEvent mouseEvent) { this.BIG_Server.setFont(Font.font(36.0f)); }
    //移出去時變回原本大小
    public void serverButtonExited(MouseEvent mouseEvent) { this.BIG_Server.setFont(Font.font(30.0f)); }
    //按下時變更小，可確認有點選，以及點下時的執行行為
    public void serverButtonPressed(MouseEvent mouseEvent) {
        this.BIG_Server.setFont(Font.font(25.0f));
    }
//==============BIG_Client Button的細項控制=========================
    //移上去時變大
    public void clientButtonEntered(MouseEvent mouseEvent) { this.BIG_Client.setFont(Font.font(36.0f)); }
    //移出去時變回原本大小
    public void clientButtonExited(MouseEvent mouseEvent) { this.BIG_Client.setFont(Font.font(30.0f)); }
    //按下時變更小，可確認有點選，以及點下時的執行行為
    public void clientButtonPressed(MouseEvent mouseEvent) {
        this.BIG_Client.setFont(Font.font(25.0f));
    }
//===================getIP Button的細項控制=========================
    //移出去時變回原本大小
    public void getIpButtonExited(MouseEvent mouseEvent) {this.getIP.setFont(Font.font(11.0f)); }
    //按下時變更小，可確認有點選，以及點下時的執行行為
    public void getIpButtonPressed(MouseEvent mouseEvent) throws UnknownHostException {
        this.getIP.setFont(Font.font(7.0f));
        addr = InetAddress.getLocalHost();
        serverIP = addr.getHostAddress();
        Label_serverIP.setText(serverIP);
    }
//===============setServer Button的細項控制==========================
    //移上去時變大
    public void setServerEntered(MouseEvent mouseEvent) {this.setServer.setFont(Font.font(13.0f)); }
    //移出去時變回原本大小
    public void setServerExited(MouseEvent mouseEvent) {this.setServer.setFont(Font.font(12.0f)); }
    //按下時變更小，可確認有點選，以及點下時的執行行為
    public void setServerPressed(MouseEvent mouseEvent) {
        this.setServer.setFont(Font.font(11.0f));
        String serverStatusString = (String)SaveReference.getReference("serverStatus");
        while(!serverStatusString.equals("成功連線!")){
            serverStatus.setText(serverStatusString);
        }
        serverStatus.setText("成功連線!");
    }
//===============connectToSever Button的細項控制==========================
    //移上去時變大
    public void connectToServerEntered(MouseEvent mouseEvent) {this.connectToServer.setFont(Font.font(13.0f)); }
    //移出去時變回原本大小
    public void connectToServerExited(MouseEvent mouseEvent) {this.connectToServer.setFont(Font.font(12.0f)); }
    //按下時變更小，可確認有點選，以及點下時的執行行為
    public void connectToServerPressed(MouseEvent mouseEvent) {
        this.connectToServer.setFont(Font.font(11.0f));
        SaveReference.addReference("server's IP",serverIP);
        String clientStatusString = (String)SaveReference.getReference("clientStatus");
        while(!clientStatusString.equals("成功連線!")){
            clientStatus.setText(clientStatusString);
        }
        clientStatus.setText("成功連線!");
    }
}
