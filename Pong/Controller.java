package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    boolean A1 = false;
    boolean A2 = false;
    boolean B1 = false;
    boolean B2 = false;

    private String boardStyle = "-fx-background-color: white;";
    private String pongStyle = "-fx-background-color: white;";

    private Pong pong = new Pong(pongStyle,485,285,30,30);
    private Board board1 = new Board(boardStyle,100,200,20,120);
    private Board board2 = new Board(boardStyle,900,200,20,120);

    private Thread pongThread = new Thread(pong);
    private Thread boardA = new Thread(board1);
    private Thread boardB = new Thread(board2);


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        SaveReference.addReference("board1",board1);
        SaveReference.addReference("board2",board2);
        SaveReference.addReference("A1",A1);
        SaveReference.addReference("A2",A2);
        SaveReference.addReference("B1",B1);
        SaveReference.addReference("B2",B2);

        anchorPane.getChildren().add(pong);
        anchorPane.getChildren().add(board1);
        anchorPane.getChildren().add(board2);
        pongThread.start();
        boardA.start();
        boardB.start();


    }


    public void OKP(KeyEvent keyEvent) {
        A1 = true;
        A2 = true;
        B1 = true;
        B2 = true;
    }

    public void OKR(KeyEvent keyEvent) {
        A1 = false;
        A2 = false;
        B1 = false;
        B2 = false;
    }
}
