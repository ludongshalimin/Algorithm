package com.leetcode.muke;

import java.util.HashMap;

/**
 * 给出四个整形数组A,B,C,D，寻找有多少i,j,k,l的组合，
 * 使得A[i] + B[j] + C[k] + D[l] == 0。
 * 其中，A,B,C,D中均含有相同的元素个数N，且0<=N<=500。

 * @author weifeng
 * 这里面给出的数据规模是什么意思，也就确定了用什么样的算法来实现
 * 因为是无序问题，所以用对撞指针合适么？显然不合适
 */
public class M09_FourSum {
	public int fourSumCount(int[]A,int[] B,int[] C,int[]D){
		if(A==null||B==null||C==null||D==null){
			throw new IllegalArgumentException("no result");
		}
		HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
		for(int i = 0;i<C.length;i++){
			for(int j = 0;j<D.length;j++){
				int sum = C[i] + D[j];
				if(map.containsKey(sum)){
					map.put(sum,map.get(sum) + 1);
				}else{
					map.put(sum,1);
				}
			}
		}
		int res = 0;
		for(int i =0;i<A.length;i++){
			for(int j =0;j<B.length;j++){
				int sum = C[i] + D[j];   //因为a,b,c，d含有相同的元素，所以可以直接进行相同的加减
				if(map.containsKey(-A[i]-B[j])){
					res += map.get(-A[i]-B[j]);
				}
			}
		}
		return res;
	}
	public static void main(String[] args){
		int[] a = {1,2};
		int[] b = {-2,-1};
		int[] c = {-1,2};
		int[] d= {0,2};
		System.out.println((new M09_FourSum()).fourSumCount(a,b,c,d));
	}
}
