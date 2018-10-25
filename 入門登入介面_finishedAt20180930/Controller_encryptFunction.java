package 入門登入介面_finishedAt20180930;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller_encryptFunction implements Initializable {
    @FXML MenuButton menubtn;
    @FXML MenuItem caesar, xor;
    @FXML TextField key;
    @FXML TextArea textA1, textA2;
    @FXML Button run, close, loadFile1, loadFile2;
    @FXML ProgressBar progressBar;

    private JFileChooser filechooser = new JFileChooser();    //開啟檔案選擇器
    private String loadFileName = "";
    private int controllerOfTheTypeOfTheEncrypt = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //基礎設定
        textA1.setDisable(true);
        textA2.setDisable(true);
        //原檔載入
        loadFile1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //檔案選擇器裡的檔案被選擇
                if(filechooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                    loadFileName = filechooser.getSelectedFile().getPath();
                    textA1.setText(loadFileName);
                }
                else{
                    JOptionPane.showMessageDialog(null,"Error , you have not choose any file!","Warning",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        //選擇鈕功能設置
        caesar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                menubtn.setText("Caesar");
                controllerOfTheTypeOfTheEncrypt = 1;
            }
        });
        xor.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                menubtn.setText("XOR");
                controllerOfTheTypeOfTheEncrypt = 2;
            }
        });

        //run功能設置
        run.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //加密法選擇
                switch (controllerOfTheTypeOfTheEncrypt){
                    case 0:
                        //顯示選擇提醒
                        JOptionPane.showMessageDialog(null,"You have chose the way you want to decrypt, and nothing will happen.", "Reminder",JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case 1:
                        //凱薩加密法的程序指令方法(寫在下方)
                        Caesar_Cipher_encrypt();
                        break;
                    case 2:
                        //XOR加密法的程序指令方法(寫在下方)
//                        XOR_encrypt();
                }
            }
        });
    }

    //凱薩加密法
    public void Caesar_Cipher_encrypt(){
        File file = new File(loadFileName);

        try {
            FileInputStream FIS = new FileInputStream(file);
            BufferedInputStream BIS = new BufferedInputStream(FIS);

            FileOutputStream FOS = new FileOutputStream(file);
            BufferedOutputStream BOS = new BufferedOutputStream(FOS);

            int fileLength = BIS.available();
            int data[] = new int[fileLength];
            int i = 0;
            while( (data[i] = BIS.read()) != -1 ){
                data[i] = data[i] + Integer.parseInt(key.getText());
                i++;
                progressBar.setProgress((double)(i/fileLength));
            }

        }
        catch (IOException e){
            JOptionPane.showMessageDialog(null,"Error fku" + e.getMessage(),"Warning",JOptionPane.INFORMATION_MESSAGE);
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error" + e.getMessage(),"Warning",JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
