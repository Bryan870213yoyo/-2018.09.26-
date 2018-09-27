package 亂數鍵盤;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import java.util.Random;
import jdk.nashorn.internal.lookup.Lookup;

import java.awt.*;

public class Main extends Application {
    //按鍵陣列
    Button btnArr[] = new Button[12];
    //亂數陣列
    int radom_V[] = new int[10];
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        getthosebtn(root);
        primaryStage.setTitle("亂數鍵盤");
        primaryStage.setScene(new Scene(root, 450, 450));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    //開始加入案件和功能
    public void getthosebtn(Parent r){
        radom();
        for(int i = 0; i < 12; i++){
            btnArr[i] = (Button) r.lookup("btn" + Integer.toString(i+1));
            if(i < 10){
                btnArr[i].setText(Integer.toString(radom_V[i]));
            }
        }
        //亂數生成
        for (int i = 0; i < radom_V.length; i++){
            btnArr[i] = new Button();

        }
        for(int i = 0; i < 10; i++){
            System.out.println(btnArr);
        }
    }
    //亂數陣列生成
    public void radom(){
        for(int i = 0; i < radom_V.length; i++){
            for(int j = 0; j < radom_V.length; j++){
                int temp = (int)(Math.random()*10);
                if(temp == radom_V[j]){
                    i++;
                    j++;
                    break;
                }
                else{
                    radom_V[i] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
