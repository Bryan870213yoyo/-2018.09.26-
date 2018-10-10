package 入門登入介面_finishedAt20180930;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.*;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller_encryptAndDecrypt implements Initializable {

    @FXML Label label_method, label_password;
    @FXML TextArea inputBox, outputBox;
    @FXML TextField textfield_password;
    @FXML RadioButton rabtn_encrypt, rabtn_decrypt;
    @FXML Button btn_run, btn_exit;
    @FXML MenuButton encrypt_way;
    @FXML MenuItem Caesar_Cipher, none;

    int controllerOfTheTypeOfTheEncrypt = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //設定點none時會做的系數更改
        none.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //將控制器的數字設為0
                controllerOfTheTypeOfTheEncrypt = 0;
                //更改格子中的文字
                encrypt_way.setText("None");
            }
        });
        //設定點選Caesar Cipher時會做的系數更改
        Caesar_Cipher.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //將控制器數字設定為1
                controllerOfTheTypeOfTheEncrypt = 1;
                //更改格子中的文字
                encrypt_way.setText("Caesar Cipher");
            }
        });
        //設定Exit的功能
        btn_exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                StageManger.getStage("mainFrameStage").show();
                StageManger.getStage("encryptAndDecryptStage").close();
            }
        });
        //設定Run的功能
        btn_run.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                determineOfTheWay();
            }
        });
    }

    //run的值型判斷法
    public void determineOfTheWay(){
        switch (controllerOfTheTypeOfTheEncrypt){
            case 0:
                //顯示選擇提醒
                JOptionPane.showMessageDialog(null,"You have chose none, and nothing will happen.", "Reminder",JOptionPane.INFORMATION_MESSAGE);
                break;
            case 1:
                //凱薩加密法的程序指令方法(寫在下方)
                Caesar_Cipher_encrypt();
                break;
        }
    }
    //凱薩加密法的加密方法
    public void Caesar_Cipher_encrypt(){
        String input = inputBox.getText();
        String output = "";
        //進行加密，並加加密後的文字儲存到新的output中(提取textfield_password的值進行加密)
        int Caesar_keypassword = Integer.parseInt(textfield_password.getText());
        for(int i = 0; i < input.length(); i++){
            output = output + (char)((int)input.charAt(i)+Caesar_keypassword);
        }
        //加到outputBox中
        outputBox.setText(output);
    }
}
