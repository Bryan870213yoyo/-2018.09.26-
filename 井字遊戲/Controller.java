package 井字遊戲;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class Controller {
    //加入成員參數 start按鈕 & 九個井字遊戲按鈕
    @FXML Button btn1,btn2,btn3,btn4,btn5,
                 btn6,btn7,btn8,btn9,start;
    //做出按鈕陣列依序加入功能

    //判斷OX的質
    boolean determine_v = true;
    //判斷是否要結束的質
    boolean Victory = false;
    //從start加入方法到九個按鈕內
    public void addAction(){
        Button btnArr[] = new Button[9];
        //將按鈕加入陣列以便統一加入功能
        btnArr[0] = btn1;
        btnArr[1] = btn2;
        btnArr[2] = btn3;
        btnArr[3] = btn4;
        btnArr[4] = btn5;
        btnArr[5] = btn6;
        btnArr[6] = btn7;
        btnArr[7] = btn8;
        btnArr[8] = btn9;

        for (int i = 0; i < btnArr.length; i++) {
            btnArr[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Button temp = (Button) event.getSource();   //取得按鈕地址
                    click_it(temp);                             //主要開始加方法的開頭
                    System.out.println(temp.getId());            //確認呼叫按鈕的名稱
                }
            });
        }
    }
        //開始加入功能
    public void click_it(Button btn){
        determine(btn);
    }
        //判斷是要Ｏ還是Ｘ的方法
    public void determine(Button btn){
        if(determine_v == true){
            btn.setText("Ｏ");
            determine_v = false;
            btn.setDisable(true);           //按下後，不能再按
            judge();                        //加入勝利判斷方法
            if(Victory){ finish(); }        //結束廢棄按鈕方法加入處
        }
        else if(determine_v == false){
            btn.setText("Ｘ");
            determine_v = true;
            btn.setDisable(true);           //按下後，不能再按
            judge();                        //加入勝利判斷方法
            if(Victory){ finish(); }        //結束廢棄按鈕方法加入處
        }
    }
        //判斷勝利的方法
    public void judge(){
                                    //橫向的贏法
        if      (btn1.getText() == btn2.getText() && btn2.getText() == btn3.getText()){
            winner();
        }
        else if (btn4.getText() == btn5.getText() && btn5.getText() == btn6.getText()){
            winner();
        }
        else if (btn7.getText() == btn8.getText() && btn8.getText() == btn9.getText()){
            winner();
        }
                                    //縱向的贏法
        else if (btn1.getText() == btn4.getText() && btn4.getText() == btn7.getText()){
            winner();
        }
        else if (btn2.getText() == btn5.getText() && btn5.getText() == btn8.getText()){
            winner();
        }
        else if (btn3.getText() == btn6.getText() && btn6.getText() == btn9.getText()){
            winner();
        }
                                    //交叉的贏法
        else if (btn1.getText() == btn5.getText() && btn5.getText() == btn9.getText()){
            winner();
        }
        else if (btn3.getText() == btn5.getText() && btn5.getText() == btn7.getText()){
            winner();
        }
    }
        //勝利方判斷以及輸出
    public void winner(){
        if (determine_v == false){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Finish");
            alert.setHeaderText(null);
            alert.setContentText("The Ｏ is winner");
            alert.showAndWait();
            Victory = true;
        }
        else if (determine_v == true){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Finish");
            alert.setHeaderText(null);
            alert.setContentText("The Ｘ is winner");
            alert.showAndWait();
            Victory = true;
        }
    }
        //結束後廢棄按鈕的方法
    public void finish(){
        Button btnArr2[] = new Button[9];
        //將按鈕加入陣列以便統一加入功能
        btnArr2[0] = btn1;
        btnArr2[1] = btn2;
        btnArr2[2] = btn3;
        btnArr2[3] = btn4;
        btnArr2[4] = btn5;
        btnArr2[5] = btn6;
        btnArr2[6] = btn7;
        btnArr2[7] = btn8;
        btnArr2[8] = btn9;
        for(int i = 0; i < btnArr2.length; i++){
            Button temp2 = btnArr2[i];
            temp2.setDisable(true);
        }
    }
}