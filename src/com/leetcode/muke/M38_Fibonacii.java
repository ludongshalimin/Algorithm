package com.leetcode.muke;

import java.util.Arrays;

/**
 * 斐波那契数：11235813
 * f(N) = f(n-1) + f(n-2)
 * @author weifeng
 *1：递归，从顶向下的解决问题
 *	什么是从上到下的解决问题
 *	f(n) = f(n-1)+f(n-2)
 *	我如果求第N的结果，就先求出N-1的结果和N-2的结果
 *	如果要求出N-1的结果，就要求出N-2和N-3的结果
 *	如果要求出N-2的结果，就要求出N-2和N-4的结果
 *  显然出现了递归的过程。
 *  递归的终止条件是。。。
 *2：记忆化搜索，发现重叠子结构，对于从顶向下的解决问题进行标记，减少不必要的计算，空间换时间
 *3：动态规划，从底向上的解决问题
 *	我的目的是求第N的结果。
 *	从最开始的结果先求。一步步的求得最后的结果
 *	f(n) = f(n-1) + f(n-2)的这个公式
 *	我可以先求出，f(3),f(4),f(5),f(6)......f(n)递归的求出
 */
public class M38_Fibonacii {
	//递归
	public class solution1{
		private int num = 0;
		public int fib(int n){
			num ++ ;
			if(n == 0){
				return 0;
			}
			if(n == 1){
				return 1;
			}
			return fib(n-1) +fib(n-2);
		}
		public int getNum(){
			return num;
		}	
	}
	//记忆化搜索,因为出现了重叠子结构，所以要进行记忆化搜索减少不必要的数据计算
	public class solution2{
		private int num = 0;
		public int fib(int n){
			int [] memo = new int[n+1];
			Arrays.fill(memo,-1);
			return fib(n,memo);
		}
		private int fib(int n,int[] memo){
			num++;
			if(n == 0)return 0;
			if(n == 1)return 1;
			if(memo[n] == -1){    //如果等于 -1说明没有被计算过，需要进行重新计算
				memo[n] = fib(n-1,memo) +
						fib(n-2,memo);
			}
			return memo[n];
		}
		public int getSum(){
			return num;
		}
	}
	public class solution{
		public int fib(int n){
			int[] memo = new int[n+1];
			Arrays.fill(memo,-1);
			memo[0] = 0;
			memo[1] = 1;
			for(int i = 2;i<=n;i++){
				memo[i] = memo[i-1] + memo[i-2];
			}
			return memo[n];
		}
	}
}
