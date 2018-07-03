package com.bishi;

import java.util.LinkedList;
import java.util.Scanner;
public class bytedance3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] nn = in.nextLine().trim().split("\\s+");
        String[] mm = in.nextLine().trim().split("\\s+");
        LinkedList<Integer> n = new LinkedList<Integer>();
        LinkedList<Integer> m = new LinkedList<Integer>();
        for(int i=0;i<nn.length; i++){
        	n.add(Integer.parseInt(nn[i]));
        }
        for(int i = 0;i<mm.length;i++){
        	m.add(Integer.parseInt(mm[i]));
        }
        int k =-1;
        int res =-1;
        for(int i =0;i<m.size()&&n.size()!=0;i++){
            for(int j=0;j<m.get(i)&&n.size()!=0;j++){
                k++;
                k = k%n.size();
            }
            res = n.remove(n.indexOf(n.get(k)));
            k--;
            if(i+1==m.size()&& n.size()!=0){
                i=0;  //重头开始来
            }
        } 
        System.out.println(res);
        
    }
}