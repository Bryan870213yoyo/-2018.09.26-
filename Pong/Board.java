package sample;

public class Board extends Element implements Runnable{

    public Board(String style, double layoutX, double layoutY, int width, int height) {
        super(style, layoutX, layoutY, width, height);
    }

    @Override
    public void run() {
        while(true){

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
