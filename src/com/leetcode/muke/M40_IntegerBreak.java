package com.leetcode.muke;

import java.util.Arrays;

/**
 * 给定一个正数n，可以将其分割成多个数字的和(分割成多个数字的和要求是，多个数是正数，所以)
 * 若要让这些数字的乘积最大，求分割的方法（至少要分成两个数）。
 * 算法返回这个最大的乘积。
 * @author weifeng
 *
 */
/**
 * 是不是一个递归的问题
 * @author weifeng
 *
 */
class Solution_IntegerBreak0518{
	public int integerBreak(int n){    //递归的解法
		int maxbreak = Integer.MIN_VALUE;
		if(n == 1)return 1;
		if(n==2) return 2;
		for(int i = 1;i<n;i++){
			maxbreak = Math.max(Math.max(maxbreak,i*(n-i)),i*integerBreak(n-i));
		}
		return maxbreak;
	}
	//记忆化搜索的方法
	private int[] memo;
	public int integerBreakII(int n){
		memo = new int[n+1];
		Arrays.fill(memo,-1);
		return integerbreakprivate(n);
	}
	private int integerbreakprivate(int n){
		if(n==2)return 2;
		if(memo[n] != -1) return memo[n];
		int maxbreaklen = Integer.MIN_VALUE;
		for(int i=1;i<n;i++){
			maxbreaklen = Math.max(maxbreaklen,Math.max(i*(n-i),i*integerbreakprivate(n-i)));
		}
		memo[n] = maxbreaklen;
		return maxbreaklen;
		
	}
	//动态规划的方法解决问题
	public int integerbreakDynamic(int n){
		int[] memo = new int[n+1];    //memo[n]对n进行分裂然后获得的最大的乘积
		Arrays.fill(memo,-1);
		memo[1] = 1;
		memo[2] = 1;
		for(int i = 2;i<=n;i++){   //memo[i]表示对i进行分割，所获得的最大的数值
			for(int j = 1;j<i;j++){
				memo[i] = Math.max(Math.max(memo[i],j*(i-j)),j*memo[i-j]);
			}
		}
		return memo[n];	
	}
}
class Solution_IntegerBreak0506{
	//递归的方法,当然也可以用标记的方法,getBreakMax得到的最大值进行储存，如果得到了最大值，则直接将该值进行返回
	public int getBreakMax_Depth(int n){
		int res = Integer.MIN_VALUE;
		for(int i = 1;i<n;i++){
			res = Math.max(res,Math.max(i*(n-i),i*getBreakMax_Depth(n-i)));
		}
		return res;
	}
	//动态规划的方法
	public int getBreakMax_Dynamic(int n){
		int[] memo = new int[n+1];  //memo[i]代表了当前分割i获得的最大的乘积
		Arrays.fill(memo,-1);
		memo[1] = 1;
		memo[2] = 1;
		for(int i = 2;i<n+1;i++){
			for(int j = 1;j<i;j++){
				memo[i] = Math.max(Math.max(memo[i],j*(i-j)),j*memo[i-j]);
			}
		}
		return memo[n];
	}
}
class Solution_IntegerBreak_0501{
	//第一种方法，使用递归的方法
	public int integerBreak(int n){
		int res = Integer.MIN_VALUE;
		for(int i = 1;i<n;i++){
			res = Math.max(Math.max(res,i*(n-i)),i*integerBreak(n-i));
		}
		return res;
	}
	//第二中方法，使用动态规划的思想
	public int integerBreakDynamic(int n){
		int[] memo = new int[n+1];
		Arrays.fill(memo,-1);
		memo[1] = 1;
		for(int i = 2;i<=n;i++){
			for(int j = 1;j<i;j++){
				memo[i] = Math.max(Math.max(memo[i],j*(i-j)),j *memo[i-j]);
			}
		}
		return memo[n];
	}
}
public class M40_IntegerBreak {
	//1：使用递归解决
	public class Solution1{
		public int integerBreak(int n){
			return breakInteger(n);
		}
		//将N进行分割(至少分割两部分)可以获得的最大乘积
		private int breakInteger(int n){
			//终止条件：1   n-1
			if(n == 1)return 1;
			int res = -1;
			for(int i = 1;i<= n-1;i++){  //分解成i,n-i
				res = max3(res,i*(n-i),i*breakInteger(n-i));
			}
			return res;
		}
		private int max3(int a,int b,int c){
			return Math.max(Math.max(a,b),c);
		}
	}
	//2:记忆化搜索解决
	public class Solution2{
		private int[] memo;
		public int integerBreak(int n){
			memo = new int[n+1];
			Arrays.fill(memo,-1);
			return breakInteger(n);
		}
		//将N进行分割(至少分割两部分)可以获得的最大乘积
		private int breakInteger(int n){
			//终止条件：1   n-1
			if(n == 1)return 1;
			if(memo[n] != -1)return memo[n];  //如果已经计算过了，则不用进行计算，直接返回
			
			int res = -1;
			for(int i = 1;i<= n-1;i++){
				res = max3(res,i*(n-i),i*breakInteger(n-i));
			}
			memo[n] = res;
			return res;
		}
		private int max3(int a,int b,int c){
			return Math.max(Math.max(a,b),c);
		}
	}
	//3:使用动态规划的方式进行解决
	public class Solution3{
		private int[] memo;
		public int integerBreak(int n){
			memo = new int[n+1];    //memo[i]表示，对i进行拆分以后，所能得到的最大的乘积值
			Arrays.fill(memo,-1);
			memo[1] =1 ;
			for(int i = 2;i<= n;i++){   //对于i尝试进行分割
				for(int j = 1;j<=i-1;j++) //每次尝试进行分割 j i-j
					memo[i]= max3(memo[i],j*(i-j),j*memo[i-j]);
			}
			return memo[n];
		}
		private int max3(int a,int b,int c){
			return Math.max(Math.max(a,b),c);
		}
	}
	public static void main(String[] args){
		Solution_IntegerBreak_0501 ss = new Solution_IntegerBreak_0501();
		System.out.println(ss.integerBreak(10));
		System.out.println(ss.integerBreakDynamic(10));
		
		System.out.println("sasa");
		Solution_IntegerBreak0506 s1 = new Solution_IntegerBreak0506();
		System.out.println(s1.getBreakMax_Depth(10));
		System.out.println(s1.getBreakMax_Dynamic(10));
		
		System.out.println("hahah");
		Solution_IntegerBreak0518 s2 = new Solution_IntegerBreak0518();
		System.out.println(s2.integerBreak(10));
		System.out.println(s2.integerBreakII(10));
		System.out.println(s2.integerbreakDynamic(10));
		System.out.println("end");
	}
	
}
