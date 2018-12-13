package sample;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Pong extends Element implements Runnable{

    /*==========資料成員=========*/
    private Pong thisRef = this;
    private float m; //斜率
    private Random rand = new Random();
    private int v=10,nowX,nowY,nextX,nextY,preX,preY; /*移動速率、目前的x位置、目前的y位置、下一個x位置、下一個y位置
                                                        移動前的x、移動前的y位置*/
    private boolean xFlag; //控制x方向的flag
    /*==========================*/

    /*===========建構元==========*/
    public Pong(String style, int layoutX, int layoutY, int width, int height){

        super(style,layoutX,layoutY,width,height);

        nowX = layoutX;
        nowY = layoutY;

        /*-----決定斜率為正or負&&xFlag-----*/
        if (rand.nextInt(11)%2 == 0){
            m = rand.nextInt(2)+1;
        }else {
            m = -(rand.nextInt(2)+1);
        }
        if (rand.nextInt(4)%2 == 0){
            xFlag = true;
        }else {
            xFlag = false;
        }
        /*--------------------------------*/

    }
    /*===========================*/

    /*============方法============*/
    public void update() {

        timer = new Timer(80, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                for (int i=1; i<eleArr.size(); i++){


                    Element temp = eleArr.get(i);

                    if (nextX < temp.getLayoutX()+temp.getWidth() && //撞右邊
                            nextX+thisRef.getWidth() > temp.getLayoutX() &&
                            nextY<temp.getLayoutY()+temp.getHeight() && //撞下邊
                            nextY+thisRef.getHeight()>temp.getLayoutY()) {

                        if (preY > temp.getLayoutY()+temp.getHeight() ||//上一個y高於這個物件的y+height
                                preY+thisRef.getHeight() < temp.getLayoutY() ){//上一個y低於這個物件的y
                            /*總之就是撞到這個物件的上下邊↑*/
                            m = -m;
                        }else { //撞到左右邊
                            System.out.println("here");
                            xFlag = !xFlag;
                        }


                    }



                }

                preX = nowX;
                preY = nowY;

                if ( (nextX<0) || (nextX>970) || (nextY<0) || (nextY>570) ){//碰到scene的其中一邊

                    if ( (nextY<=0) || (nextY>=570) ){//碰到上邊or下邊
                        if ( (nextY<=0) ){
                            nowY = 0;
                        }else {
                            nowY = 570;
                        }
                        m = -m;
                    }else{//碰到左邊or右邊

                        if (nextX<0){
                            nowX = 0;
                        }else {
                            nowX=970;
                        }
                        xFlag = !xFlag;

                    }


                }


                if (xFlag){
                    nowX += v;
                    nextX = nowX+v;
                }else {
                    nowX -= v;
                    nextX = nowX-v;
                }


                nowY = (int)m*v+nowY;

                nextY = (int)m*v+nowY;

                thisRef.setLayoutX(nowX);
                thisRef.setLayoutY(nowY);

//                System.out.println("x:"+nowX+",y:"+nowY+",m:"+m+",xFlag:"+xFlag);
//                System.out.println("nextX:"+nextX+",nextY:"+nextY);
//                System.out.println("--------------------------------");
//                System.out.println("m:"+m);
            }
        });

        timer.start();

    }

    @Override
    public void run() {
        update();
    }
    /*===========================*/

}
