package 入門登入介面_finishedAt20180930;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

public class StageManger {
    //建立一個例外拋出器，為了要防止多建立一個不必要的StageManger
    public StageManger(){
        throw new Error("This is a static class.(StageManger)");
    }

    //建立HashMap來儲存stage(名稱, stage)
    private static HashMap<String, Stage> stages = new HashMap<String, Stage>();

    //加入stage 加入基礎視窗設定 再將stage加入HashMap stageArr中
    public static void addStage(String name, String title, String resource, int width, int height){
        Stage stage = new Stage();
        stage.setTitle(title);     // 設置視窗名稱
        stage.setResizable(false); // 設為不可隨意更動大小
        Scene scene = new Scene(loadStage(resource),width,height);
        stage.setScene(scene);
        stages.put(name, stage);
    }

    //loadStage，目的是省下再main將fxml導入Parent的動作
    public static Parent loadStage(String resource){
        Parent root = null;
        try {
            root = FXMLLoader.load(StageManger.class.getResource(resource));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return root;
    }

    //呼叫用StageManger儲存在HashMap中的指定Stage的方法
    public static Stage getStage(String name){
        return stages.get(name);
    }

    //刪除StagerManger中HashMap中指定Stage的方法
    public static void deleteStage(String name){ stages.remove(name); }
}
