package com.exercise;
/**
 * 这是阿里巴巴的一道测试题，一个长方形，要求裁剪的周长最短
 * @author weifeng
 * 给一个
 */
public class CircleMin {
    public static int minS(int m,int n){
    	int res = 0;
    	if(m == n) return 4*m;
    	else if(m<n){
    		res = res+4*m+minS(m,n-m);
    	}else {
    		res = res +4*n + minS(m-n,n);
    	}
    	return res; 
    }
    public static void main(String[] args){
    	System.out.println(minS(7,3));
    }
}
