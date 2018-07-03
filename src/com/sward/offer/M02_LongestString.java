package com.sward.offer;

import java.util.Arrays;

/**
 * 一根绳子的长度是n,现在要简称若干段，每段的长度为k[0],k[1],...k[m]
 * 求每段长度乘积的最大值
 * @author weifeng
 * 其实就是一个整数，分成若干个数，求分得的数的乘积最大
 * f(N)表示N拆解以后乘积的最大值
 * f(N) = Math.max(1*(n-1),2*(n-2),3*(n-3),...(n-1)*1..,1*f(n-1),2*f(n-2),3*f(n-3)....
 * 
 */
public class M02_LongestString {
	//1：使用递归的方式
	int[] memo ;
	public int getMaxMul(int n){
		int res = Integer.MIN_VALUE;
		memo = new int[n];
		Arrays.fill(memo,-1);
		for(int i = 1;i<n;i++){
			res = Math.max(Math.max(res,i*(n-i)),i*getMaxMulValue(n-i));
		}
		return res;
	}
	private int getMaxMulValue(int n){
		if(n==1 || n ==2)return 1;
		int res = Integer.MIN_VALUE;
		if(memo[n] != -1) return memo[n];   //使用记忆化搜索的方法
		for(int i = 1;i<n;i++){
			res = Math.max(Math.max(res,i*(n-i)),i*getMaxMulValue(n-i));
		}
		memo[n] = res;
		return res;
	}
	//2：使用动态规划
	//memo[n]表示拆分n所获得的最大的乘积
	//
	public int getMaxValueDy(int n){
		int res = Integer.MIN_VALUE;
		int[] memo = new int[n+1];
		memo[1] = 1;
		memo[2] = 1;
		for(int i = 2;i<=n;i++ ){
			for(int j = 1;j<i;j++){
				memo[i] = Math.max(Math.max(memo[i],j*(i-j)),j*memo[i-j]);
			}
		}
		return memo[n];
	}
	public static void main(String []args){
		M02_LongestString ss = new M02_LongestString();
		System.out.println(ss.getMaxMul(8));
		System.out.println(ss.getMaxValueDy(8));
	}
}
