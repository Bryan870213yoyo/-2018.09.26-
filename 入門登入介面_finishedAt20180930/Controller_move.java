package 入門登入介面_finishedAt20180930;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller_move implements Initializable {
    @FXML ImageView picachu;
    @FXML Button start, stop;

    private Timer timer;
    private boolean X = true;
    private boolean Y = true;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        timer = new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //===========判斷用=============
                if(picachu.getLayoutX() == 300){X = false;}
                else if(picachu.getLayoutX() == 0){X = true;}
                if(picachu.getLayoutY() == 300){Y = false;}
                else if(picachu.getLayoutY() == 0){Y = true;}
                //==============================
                //============X移動=============
                if(X){picachu.setLayoutX(picachu.getLayoutX()+1);}
                else{picachu.setLayoutX(picachu.getLayoutX()-1);}
                //============Y移動=============
                if(Y){picachu.setLayoutY(picachu.getLayoutY()+1);}
                else{picachu.setLayoutY(picachu.getLayoutY() + -1);}
            }
        });

        //start功能設置
        start.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                timer.start();
            }
        });
        //stop功能設置
        stop.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                timer.stop();
            }
        });
    }
}
