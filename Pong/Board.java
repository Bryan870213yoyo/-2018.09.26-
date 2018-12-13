public class Board extends Element implements Runnable{

    public Board(String style, double layoutX, double layoutY, int width, int height) {
        super(style, layoutX, layoutY, width, height);
    }
    private int Dir = 0;
    public int getDir(){return Dir;}
    public void setDir(int a){Dir = a;}

    @Override
    public void run() {
        while(true){
            switch (Dir){
                case 0:
                    //do nothing
                    break;
                case 1:
                    if(this.getLayoutY() <= 0){ Dir = 0;}
                    this.setLayoutY(this.getLayoutY() - 4);
                    break;
                case 2:
                    if(this.getLayoutY()+120 >= 600){ Dir = 0;}
                    this.setLayoutY(this.getLayoutY() + 4);
                    break;
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
