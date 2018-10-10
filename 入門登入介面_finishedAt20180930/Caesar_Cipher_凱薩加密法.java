package 入門登入介面_finishedAt20180930;

import java.util.ArrayList;
import java.util.Scanner;

public class Caesar_Cipher_凱薩加密法 {
    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);
        String input = scn.next();              //建立輸入字串
        String output = "";                     //建立輸出字串
            //進行加密並將文字加入輸出字串
        for(int i = 0; i < input.length(); i++){
            output = output + (char)((int)input.charAt(i)+2);
        }
        System.out.println(output);             //輸出
    }
}