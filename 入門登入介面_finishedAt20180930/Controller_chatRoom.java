package 入門登入介面_finishedAt20180930;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller_chatRoom implements Initializable {
    @FXML Button ipconfig, submit, gogogo;
    @FXML TextField ipsubmit, content;
    @FXML TextArea TA;
    @FXML Label ipshow;
    Socket s;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                byte buff[] = new byte[1024];
                try{
                    s = new Socket(String.valueOf(ipsubmit),2525);
                    System.out.println("連接上伺服器");
                    System.out.println("伺服器ip位址:"+s.getInetAddress());
                }catch (IOException e){
                    e.printStackTrace();
                }
                gogogo.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            InputStream in = s.getInputStream();
                            int n = in.read(buff);
                            System.out.println(new String(buff,0,n));
                            in.close();
                            s.close();
                        }
                        catch (IOException e){

                        }
                    }
                });
            }
        });

    }
}
