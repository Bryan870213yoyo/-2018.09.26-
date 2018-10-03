package 入門登入介面_finishedAt20180930;

import java.util.Scanner;
import java.util.Random;

public class 亂數產生器 {
    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);
        int intArr[] = new int[10];
        boolean addornot = true;
        Random ran = new Random();
        for(int i = 0; i < intArr.length; i++){
             int temp = ran.nextInt(10);
             for(int j = 0; j < i; j++){
                 if(temp == intArr[j]){
                     addornot = false;
                     i--;
                     break;
                 }
                 else{
                     addornot = true;
                 }
             }
             if(addornot){
                 intArr[i] = temp;
             }
        }
        //列印亂數陣列
        for (int i = 0; i < intArr.length; i++){
            System.out.print(Integer.toString(intArr[i]) + " ");
        }
    }
}
