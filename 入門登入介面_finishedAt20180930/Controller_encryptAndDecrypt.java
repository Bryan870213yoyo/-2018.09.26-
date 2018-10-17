package 入門登入介面_finishedAt20180930;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.RadioButton;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
    @FXML ToggleGroup radioBtnGroup;
    @FXML Menu menu_file, menu_edit, menu_help;
    @FXML MenuItem menuitem_open, menuitem_save;

    int controllerOfTheTypeOfTheEncrypt = 0;
    private JFileChooser filechooser = new JFileChooser();    //開啟檔案選擇器

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
        //設定openFile的功能
        menuitem_open.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //檔案選擇器裡的檔案被選擇
                if(filechooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                    //儲存文字用的空字串
                    String str = "";
                    //
                    try {
                        //建立一個檔案閱讀器，並將檔案選擇棄選擇的檔案匯入給檔案閱讀器閱讀
                        FileReader frd = new FileReader(filechooser.getSelectedFile());
                        //建立緩衝區
                        BufferedReader buf = new BufferedReader(frd);
                        //按行讀取(判斷下一行是否有東西後再執行，若沒有就結束)
                        while((str = buf.readLine()) != null){
                            inputBox.appendText(str + '\n');
                        }
                        //關閉檔案閱讀器
                        frd.close();
                    }
                    catch (IOException e){
                        JOptionPane.showMessageDialog(null,"Error" + e.getMessage(),"Warning",JOptionPane.INFORMATION_MESSAGE);
                    }
                    catch (Exception e){
                        JOptionPane.showMessageDialog(null,"Error" + e.getMessage(),"Warning",JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
    }

    //run的值型判斷法
    public void determineOfTheWay(){
        //加密法選擇
        if(rabtn_encrypt.isSelected()){
            //確認有無文字
            if(inputBox.getText().length() > 0){
                switch (controllerOfTheTypeOfTheEncrypt){
                    case 0:
                        //顯示選擇提醒
                        JOptionPane.showMessageDialog(null,"You have chose the way you want to decrypt, and nothing will happen.", "Reminder",JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case 1:
                        //凱薩加密法的程序指令方法(寫在下方)
                        Caesar_Cipher_encrypt();
                        break;
                }
            }
            //無輸入文字時
            else{
                JOptionPane.showMessageDialog(null,"You have not entered nothing to encrypt.", "Reminder",JOptionPane.INFORMATION_MESSAGE);
            }
        }
        //解密法選擇
        else if (rabtn_decrypt.isSelected()){
            //確認有輸入文字
            if(outputBox.getText().length() > 0){
                switch (controllerOfTheTypeOfTheEncrypt){
                    case 0:
                        //顯示選擇提醒
                        JOptionPane.showMessageDialog(null,"You have chose none, and nothing will happen.", "Reminder",JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case 1:
                        //凱薩加密法的程序指令方法(寫在下方)
                        Caesar_Cipher_decrypt();
                        break;
                }
            }
            //無輸入文字時
            else{
                JOptionPane.showMessageDialog(null,"You have not entered nothing to decrypt.", "Reminder",JOptionPane.INFORMATION_MESSAGE);
            }
        }
        //無選擇解密或加密時
        else{
            JOptionPane.showMessageDialog(null,"You have not choose you want to encrypt or decrypt.", "Reminder",JOptionPane.INFORMATION_MESSAGE);

        }
    }
    //凱薩加密法的加密方法
    public void Caesar_Cipher_encrypt(){
        try{
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
        catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null,"This is not a available password type.", "Warn!",JOptionPane.INFORMATION_MESSAGE);
        }
    }
    //凱薩解密法的解密方法
    public void Caesar_Cipher_decrypt(){
        try{
            String output = outputBox.getText();
            String input = "";
            //進行加密，並加加密後的文字儲存到新的input中(提取textfield_password的值進行加密)
            int Caesar_keypassword = Integer.parseInt(textfield_password.getText());
            for(int i = 0; i < output.length(); i++){
                input = input + (char)((int)output.charAt(i)-Caesar_keypassword);
            }
            //加到outputBox中
            inputBox.setText(input);
        }
        catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null,"This is not a available password type.", "Warn!",JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
