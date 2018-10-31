package 入門登入介面_finishedAt20180930;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller_timing implements Initializable {
    //載入控制元件
    @FXML Button start, stop, restart;
    @FXML ImageView h1, h2, m1, m2, s1, s2, colon1, colon2;

    //設置圖片儲存處和計時器,還有計時器所需的判斷參數
    private Image imgArr[] = new Image[12];
    private Timer mainTimging, colonTiming;
    private int count = 0;
    private boolean colonChange = true;

    //計時器數字顯示的數字參照
    private int ch1, ch2, cm1, cm2, cs1, cs2, hour, minute, second;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //將圖片加入陣列中，以方便提取
        for(int i = 0; i < imgArr.length; i++){
            imgArr[i] = new Image("\\Number Image\\"+Integer.toString(i)+".jpg");
        }
        //預設數字為0
        h1.setImage(imgArr[0]);
        h2.setImage(imgArr[0]);
        m1.setImage(imgArr[0]);
        m2.setImage(imgArr[0]);
        s1.setImage(imgArr[0]);
        s2.setImage(imgArr[0]);

        //=============定義mainTiming=================
        mainTimging = new Timer(1000, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                count = count+1;
                int tempTime = count;

                hour = tempTime/3600;       //取小時
                tempTime = tempTime%3600;
                minute = tempTime/60;       //取分鐘
                tempTime = tempTime%60;
                second = tempTime;          //取秒數

                if(count < 360000){         //設地小於100小時前
                    //設定各個相對應數字
                    ch1 = hour/10;
                    ch2 = hour%10;
                    cm1 = minute/10;
                    cm2 = minute%10;
                    cs1 = second/10;
                    cs2 = second%10;
                    //將圖片同步
                    h1.setImage(imgArr[ch1]);
                    h2.setImage(imgArr[ch2]);
                    m1.setImage(imgArr[cm1]);
                    m2.setImage(imgArr[cm2]);
                    s1.setImage(imgArr[cs1]);
                    s2.setImage(imgArr[cs2]);
                }
                else{
                    JOptionPane.showMessageDialog(null, "超出計實範圍","Warning", JOptionPane.INFORMATION_MESSAGE);
                    mainTimging.stop();
                }
            }
        });
        //========================================

        //==============定義colonTiming===========
        colonTiming = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                colonChange = !colonChange;

                if(colonChange){
                    colon1.setImage(imgArr[10]);
                    colon2.setImage(imgArr[10]);
                }
                else{
                    colon1.setImage(imgArr[11]);
                    colon2.setImage(imgArr[11]);
                }
            }
        });
        //=========================================

        //start加入功能==============================
        start.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                mainTimging.start();
            }
        });
        //===========================================

        //stop加入功能===============================
        stop.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                mainTimging.stop();
            }
        });
        //===========================================

        //restart加入功能============================
        restart.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                mainTimging.stop();
                count = 0;
                mainTimging.start();
            }
        });
        //===========================================

        //=============colonTiming start==============
        colonTiming.start();
    }
}
