package 入門登入介面_finishedAt20180930;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller_eatingSnake implements Initializable{
    @FXML Circle ball;
    @FXML void keyPressed(KeyEvent event){
        switch (event.getCode()){
            case UP:
                decide = 0;
                break;
            case RIGHT:
                decide = 1;
                break;
            case DOWN:
                decide = 2;
                break;
            case LEFT:
                decide = 3;
                break;
            case SPACE:
                timer.start();
        }
    }
    private int decide = 1;
    Timer timer;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //==============================================================
        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (decide){
                    case 0:
                        ball.setCenterY(ball.getCenterY() + 5);
                        System.out.printf("UP");
                        break;
                    case 1:
                        ball.setCenterX(ball.getCenterX() + 5);
                        System.out.printf("RIGHT");
                        break;
                    case 2:
                        ball.setCenterY(ball.getCenterY() - 5);
                        System.out.printf("DOWN");
                        break;
                    case 3:
                        ball.setCenterX(ball.getCenterX() - 5);
                        System.out.printf("LEFT");
                        break;
                }
            }
        });
        //===================================================================
        keyPressed(KeyEvent event){

        };
    }
}
