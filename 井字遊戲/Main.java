package 井字遊戲;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));     //導入fxml的介面布置
        primaryStage.setTitle("Ｂｒｙａｎ’ｓ　ＯＯＸＸ　ＧＡＭＥ");                     //視窗的標題
        primaryStage.setScene(new Scene(root, 450, 450));                //視窗大小
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}