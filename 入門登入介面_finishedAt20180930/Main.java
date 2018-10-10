package 入門登入介面_finishedAt20180930;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        //開始將各個stage加入到StageManger中
        StageManger.addStage("loginStage","login interface","Fxml_login.fxml", 300,300);
        StageManger.addStage("randomKeyboardStage","random keyboard interface","Fxml_randomKeyboard.fxml",250,250);
        StageManger.addStage("mainFrameStage","main frame interface","Fxml_mainFrame.fxml",450,300);
        StageManger.addStage("OOXXGameStage", "Ｂｒｙａｎ’ｓ　ＯＯＸＸ　ＧＡＭＥ","Fxml_OOXX_Game.fxml",450,450);
        StageManger.addStage("encryptAndDecryptStage", "Encrypt & Decrypt interface", "Fxml_encryptAndDecrypt.fxml", 500,400);
        //設兩行備用
//        StageManger.addStage("","","",123,123);
//        StageManger.addStage("","","",123,123);
        //StageManger的mainFrame跟OOXX GAME的開關設定
        //Ｂｒｙａｎ’ｓ　ＯＯＸＸ　ＧＡＭＥ關閉時的設定
        StageManger.getStage("OOXXGameStage").setOnCloseRequest(event -> {
            System.out.println("closing OOXX");
            StageManger.getStage("mainFrameStage").show();
        });
        //StageManger的mainFrame跟Emcrypt & Decrypt interface的開關設定
        //Emcrypt & Decrypt interface關閉時的設定
        StageManger.getStage("encryptAndDecryptStage").setOnCloseRequest(event -> {
            System.out.println("closing encryptAndDecrypt");
            StageManger.getStage("mainFrameStage").show();
        });

        //default setting of the first showing window.
        StageManger.getStage("loginStage").show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
