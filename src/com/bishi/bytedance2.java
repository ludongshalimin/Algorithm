package com.bishi;

import java.util.LinkedList;
import java.util.Scanner;
public class bytedance2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();
        LinkedList<String> mm = new LinkedList<String>();
        LinkedList<String> nn = new LinkedList<String>();
        int i = 0;
        while(i<m){
            mm.add(in.nextLine().trim());
        }
        in.nextLine();
        i=0;
        while(i<n){
            nn.add(in.nextLine().trim());
        }
        for(int j=0;j<nn.size();j++){
            for(int k=0;k<mm.size();k++){
                if(nn.get(j).indexOf(mm.get(k))!=-1){
                    System.out.println("1");
                    System.out.println();
                    break;
                }
            }
            System.out.println("-1");
            System.out.println();
        }
        
    }
}
