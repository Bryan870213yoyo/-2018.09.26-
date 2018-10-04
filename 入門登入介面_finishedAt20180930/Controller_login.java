package 入門登入介面_finishedAt20180930;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller_login implements Initializable {
    //加入Button
    @FXML Button ExitBtn, KeyboardBtn, LoginBtn;
    //加入Label
    @FXML Label Account, Password;
    //加入TextField
    @FXML private TextField AccountBox;
    //加入PasswordField
    @FXML private PasswordField PasswordBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        handleButtonAction();
    }

    public void handleButtonAction(){

        //離開按鈕的功能設置
        ExitBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });

        //KeyboardBtn的功能設置 & 預先將SaveReference的物件建立
        KeyboardBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                StageManger.deleteStage("randomKeyboardStage");
                StageManger.addStage("randomKeyboardStage","random keyboard interface","Fxml_randomKeyboard.fxml",250,250);
                StageManger.getStage("randomKeyboardStage").show();
                SaveReference.addToSaveReference("password", PasswordBox);
            }
        });

        //LoginBtn的功能設置
        LoginBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(AccountBox.getText().equals("gorgor@gmail.com")&& PasswordBox.getText().equals("666666")){
                    StageManger.getStage("mainFrameStage").show();
                    AccountBox.setText("");
                    PasswordBox.setText("");
                    StageManger.getStage("loginStage").close();
                }
                else{
                    JOptionPane.showMessageDialog(null,"Account or password is wrong.", "Warning",JOptionPane.ERROR_MESSAGE);
                    AccountBox.setText("");
                    PasswordBox.setText("");
                }
            }
        });
        //用enter登入看看?
        PasswordBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(AccountBox.getText().equals("gorgor@gmail.com")&& PasswordBox.getText().equals("666666")){
                    StageManger.getStage("mainFrameStage").show();
                    AccountBox.setText("");
                    PasswordBox.setText("");
                    StageManger.getStage("loginStage").close();
                }
                else{
                    JOptionPane.showMessageDialog(null,"Account or password is wrong.", "Warning",JOptionPane.ERROR_MESSAGE);
                    AccountBox.setText("");
                    PasswordBox.setText("");
                }
            }
        });
    }
}
