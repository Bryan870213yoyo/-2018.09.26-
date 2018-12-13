import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        StageManager.addStage("gameStage","Pong","table.fxml",1000,600);

        StageManager.getStage("gameStage").show();

        Board board1 = (Board)SaveReference.getReference("board1");
        Board board2 = (Board)SaveReference.getReference("board2");
        StageManager.getStage("gameStage").getScene().getRoot().requestFocus();
        StageManager.getStage("gameStage").getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                switch (event.getCode()){
                    case W:
                        board1.setDir(1);
                        break;
                    case S:
                        board1.setDir(2);
                        break;
                }
                switch (event.getCode()){
                    case UP:
                        board2.setDir(1);
                        break;
                    case DOWN:
                        board2.setDir(2);
                        break;
                }
            }
        });

    }


    public static void main(String[] args) {
        launch(args);
    }

}
