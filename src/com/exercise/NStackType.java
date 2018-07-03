package com.exercise;
/**
 * abcdef 依次入栈，元素可在栈里面停留或者出栈，求有多少种出栈的顺序
 * @author weifeng
 * 	1、在原序列中相对位置比它小的，必须是逆序；
	2、在原序列中相对位置比它大的，顺序没有要求；
	3、以上两点可以间插进行
 * 	f(0) = 1
 *  f(1) = 1
 *  f(2) = 2
 *  f(3) = 5
 *	f(4) = f(0)*f(3) + f(1)*f(2) + f(2) * f(1) + f(3)*f(0) =
 *		 = 5 +2+2+5 =14
 */
import java.util.Scanner;
public class NStackType {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] sum = new int[n+1];
		int[] f = new int[n+1];
		sum[0] = 1;
		sum[1] = 1;
		for(int i = 2;i<=n;i++){
			sum[i] = 0;
		}
		for(int j = 2;j<=n;j++){
			for(int i =1;i<=j;i++){
				f[i] = sum[i-1] * sum[j-i];
				sum[j]+=f[i];
			}
		}
		System.out.println(sum[n]);
	}
}
