package 入門登入介面_finishedAt20180930;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;

public class Controller_randomKeyboard implements Initializable {
    //加入Button
    @FXML Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn10,
                 btn11,btn12;
    //加入Label
    @FXML Label tempPassWord;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        handleButtonAction();
    }

    //按鍵陣列
    Button btnArr[] = new Button[12];

    //亂數陣列
    int radom_V[] = new int[10];
    boolean addornot = true;
    Random ran = new Random();
    //亂數陣列

    //將案件加入按鍵陣列中並賦與功能
    private void handleButtonAction(){
            //將案件加入陣列中
        btnArr[0] = btn1;
        btnArr[1] = btn2;
        btnArr[2] = btn3;
        btnArr[3] = btn4;
        btnArr[4] = btn5;
        btnArr[5] = btn6;
        btnArr[6] = btn7;
        btnArr[7] = btn8;
        btnArr[8] = btn9;
        btnArr[9] = btn10;
        btnArr[10] = btn11;
        btnArr[11] = btn12;
        addText();              //加入按鈕文字
            //開始加入功能
        //0~9的按鈕功能設置
        for(int i = 0; i < 10; i++){
            int finalI = i;
            btnArr[i].setOnAction(new EventHandler<javafx.event.ActionEvent>() {
                @Override
                public void handle(javafx.event.ActionEvent event) {
                    tempPassWord.setText(tempPassWord.getText() + btnArr[finalI].getText());
                }
            });
        }
        //Backspace按鈕功能設置
        btnArr[10].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //確認 Label 上有無數字
                if(tempPassWord.getText() == ""){ }
                else{
                    int temp = Integer.parseInt(tempPassWord.getText());
                    temp = (temp - (temp%10))/10;
                    //檢查是否刪光數字
                    if(temp == 0){  tempPassWord.setText("");  }
                    else{   tempPassWord.setText(Integer.toString(temp));   }
                }
            }
        });
        //Submit按鈕功能設置
        btnArr[11].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //將資料透過temp存到SaveReference中的password中，就可以送到PasswordBox
                PasswordField temp = (PasswordField) SaveReference.getSaveReference("password");
                temp.setText(tempPassWord.getText());
                //加入關閉單個視窗的功能
                StageManger.getStage("randomKeyboardStage").close();
            }
        });
    }

    //加入按鈕字樣的方法
    public void addText(){
        randomProduce();
        for(int i = 0; i < radom_V.length; i++){
            btnArr[i].setText(Integer.toString(radom_V[i]));
        }
    }

    //產生純亂數陣列
    public void randomProduce(){
        for(int i = 0; i < radom_V.length; i++){
            int temp = ran.nextInt(10);
            for(int j = 0; j < i; j++){
                if(temp == radom_V[j]){
                    addornot = false;
                    i--;
                    break;
                }
                else{
                    addornot = true;
                }
            }
            if(addornot){
                radom_V[i] = temp;
            }
        }
    }

}
