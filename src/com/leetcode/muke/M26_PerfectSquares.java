package com.leetcode.muke;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 给出一个正整数n，寻找最少的完全平方数，使他们的和为n。
 * @author weifeng
 *对问题建模：
整个问题转化为一个图论问题。
从n到0，每个数字表示一个节点；
如果两个数字x到y相差一个完全平方数，则连接一条边。
我们得到了一个无权图。
原问题转化成，求这个无权图中从n到0的最短路径
 */
//使用递归的方法进行解决
class Solution_perfectsqures0518{
	public int perfectsqures(int n){   //使用动态规划的方法进行
		int res = n;  //因为最大也就是n个1
		if(n ==1)return 1;
		for(int i = 1;i<n&&i*i<n;i++){
			res = Math.min(n,1+ perfectsqures(n-i*i));
		}
		return res;
	}
	private int [] memo;             //使用记忆化搜索的方法进行
	public int getPerfectLen(int n){
		memo = new int[n+1];
		Arrays.fill(memo,-1);
		return getPerfectMinLen(n);
		
	}
	private int getPerfectMinLen(int n){
		int res = n;  //因为最大也就是n个1
		if(n ==1)return 1;
		if(memo[n] != -1) return memo[n];
		for(int i = 1;i<n&&i*i<n;i++){
			res = Math.min(n,1+ getPerfectMinLen(n-i*i));
		}
		memo[n] = res;
		return res;
	}
}
public class M26_PerfectSquares {
	private class Pair<Key ,Value>{
		Key node;
		Value integer;
		Pair(Key node,Value i){
			this.node = node;
			this.integer = i;
		}
		Key getKey(){
			return this.node;
		}
		Value getValue(){
			return this.integer;
		}
	}
	public int numSuqares(int n){
		LinkedList<Pair<Integer,Integer>> queue = new LinkedList<Pair<Integer,Integer>>() ;
		queue.addLast(new Pair<Integer,Integer>(n,0));
		boolean[] visited = new boolean[n+1];
		visited[n] = true;
		while(!queue.isEmpty()){
			Pair<Integer,Integer> front = queue.removeFirst();
			int num = front.getKey();   //这里面是自己实现的方法
			int step = front.getValue();
			if(num ==0)return step;   //谁先为0就是先退出，说明遍历到了
			for(int i = 1;num-i*i >= 0;i++){  //一个数到0的方式很多，推入了很多重复的元素
				if(!visited[num-i*i]){
					queue.addLast(new Pair(num-i*i,step +1));   //会有性能问题，会重复推入，对一个数字有非常多的可能性到达指定的数字
					visited[num-i*i] = true;
				}
			}
		}
		throw new IllegalStateException("no solution");
	}
	public static void main(String[] args){
		System.out.println(new M26_PerfectSquares().numSuqares(5));
		System.out.println(new M26_PerfectSquares().numSuqares(11));
		System.out.println(new Solution_perfectsqures0518().perfectsqures(5));
		System.out.println(new Solution_perfectsqures0518().perfectsqures(11));
		System.out.println(new Solution_perfectsqures0518().getPerfectLen(5));
		System.out.println(new Solution_perfectsqures0518().getPerfectLen(11));
	}
}
