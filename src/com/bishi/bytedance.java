package com.bishi;

import java.util.Scanner;
public class bytedance {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int sum =0;
        while (in.hasNextLine()) {
            String curline = in.nextLine();
            char [] arrchar = curline.toCharArray();
            for(int i = 0;i<arrchar.length;i++){
                if(arrchar[i] == '/'&&
                   ((i+1) < arrchar.length && (arrchar[i+1]=='/' || arrchar[i+1] == '*'))
                   &&(i-1>=0? arrchar[i-1]!='\"' : true)){
                    sum +=1;
                    i++;
                }
            }
        }
        System.out.println(sum);
    }
}