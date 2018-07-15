package com.leetcode.muke;

import java.util.HashSet;
import java.util.LinkedList;

/**
 * 给出一个整形数组nums和一个整数K，是否存在索引i和j，使得
 * nums[i] == nums[j]且i和j之间的差不超过K
 * @author weifeng
 *
 */
class Solution_duplicate0608{
	public boolean iscontain(int[] arr,int k){
		int n = arr.length;
		LinkedList<Integer> hs = new LinkedList<Integer>();
		int i = 0;
		while(i<n){
			if(hs.contains(arr[i])){  //相等的话
				return true;
			}else{
				hs.addLast(arr[i]);
			}
			if(hs.size() > k){
				hs.removeFirst();
			}
			i++;
		}
		return false;
	}
}
class solution_duplicate0518{
	public boolean getduplicate(int[] arr,int k){
		int n = arr.length;
		int i = 0;
		LinkedList<Integer> ss = new LinkedList<Integer>();
		while(i<n){
			if(ss.contains(arr[i])){
				return true;
			}else{
				ss.add(arr[i]);
			}
			if(ss.size() >k){
				ss.removeFirst();    //也可以删除指定的元素
			}
		}
		return false;
	}
}
public class M11_ContainDuplicateII {
	public boolean containsNearbyDuplicate(int[]nums,int k){
		if(nums == null || nums.length <=1){
			return false;
		}
		if(k <= 0)return false;
		HashSet<Integer> record = new HashSet<Integer>();
		for(int i =0;i<nums.length;i++){
			if(record.contains(nums[i])){
				return true;
			}
			record.add(nums[i]);
			if(record.size() == k+1){  //要保证的是record的size大小不能超过k
				record.remove(nums[i-k]);
			}
		}
		return false;
	}
	private static void printBool(boolean b){
		System.out.println(b?"true":"false");
	}
	public static void main(String[] args){
		int[] nums = {1,2,1};
		int k = 2;
		printBool((new M11_ContainDuplicateII()).containsNearbyDuplicate(nums,k));
	}

	
}
