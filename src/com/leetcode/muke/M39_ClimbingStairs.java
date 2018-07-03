package com.leetcode.muke;

import java.util.Arrays;

/**
 * 有一个楼梯，总共有n阶台阶。
 * 每一次，可以上一个台阶，也可以上两个台阶。
 * 问，爬上这样的一个楼梯，一共有多少不同的方法？
 * @author weifeng
 *
 */
public class M39_ClimbingStairs {
	//n表示爬上n个台阶
	//step(N)表示爬上n个台阶，可以进行的方式
	//1：使用递归的方式，进行
	public class Solution1{
		public int steps(int n){
			if(n == 0)return 1;
			if(n == 1) return 1;
			return steps(n -1) +steps(n -2);
		}
	}
	//2：发现重复的递归的过程，进行记忆化搜索的过程
	public class Solution2{
		private int[] memo;
		public int steps(int n){
			memo = new int[n+1];
			Arrays.fill(memo,-1);
			return calcWays(n);
		}
		private int calcWays(int n){
			if(n == 0)return 1;
			if(n == 1) return 1;
			if(memo[n] == -1){
				memo[n] = calcWays(n-1)+calcWays(n-2);
			}
			return memo[n];
		}
	}
	//动态规划的方式进行
	public class Solution3{
		private int[] memo;
		public int steps(int n){
			memo = new int[n+1];
			Arrays.fill(memo,-1);
			memo[0]= 1;
			memo[1]= 1;           //memo[i]表示，登上i层台阶的所有的方法路径数
			for(int i = 2;i<=n;i++){
				memo[i] = memo[i-1] +memo[i-2];
			}
			return memo[n];
		}
	}
	
}
