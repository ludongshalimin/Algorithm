package com.leetcode.muke;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 回溯法解决组合问题
 * 组合问题
 * 
 * 给出两个整数n和k，求在1...n这n个数字中选出k个数字的所有组合。
如 n = 4 ，k = 2
结果为 [ [1,2] , [1,3] , [1,4] , [2,3] , [2,4] , [3,4] ]

 * @author weifeng
 *
 */
public class M35_Combination {
	private ArrayList<List<Integer>> res;
	public List<List<Integer>> combine(int n,int k){
		res = new ArrayList<List<Integer>>();
		if(n <= 0||k<=0||k>n)return res;
		LinkedList<Integer> c = new LinkedList<Integer>();
		generateCombinations(n,k,1,c);   //从1开始，CnK
		return res;
	}
	//排列是从1...n  的然后取出c(n,k)
	// 求解C(n,k), 当前已经找到的组合存储在c中, 需要从start开始搜索新的元素
	private void generateCombinations(int n ,int k,int start,LinkedList<Integer> c){
		if(c.size() == k){   //表示已经满了容量了，找到含有k个元素的组合了
			res.add((List<Integer>)c.clone());
			return;
		}
		for(int i = start;i<=n;i++){
			c.addLast(i);
			generateCombinations(n,k,i+1,c);
			c.removeLast();
		}
		return;

    }

}
