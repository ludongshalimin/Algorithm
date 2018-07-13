package com.leetcode.muke;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
/*
 * 给定一个数组，比如【10,9,2,5,3,7,102,18】
 * 发现最长的数组的长度，并且求出最长的数组
 * 【2,3,7,101]
 */
class Solution_LongestSubIncreasingSequence_0712{
	private int[]memo;
	private int getMaxLength(int[] arr,int n){
		int maxlength = 1;    //因为最小就是1
		if(memo[n] != -1)return memo[n];
		for(int i= 0;i<n;i++){
			if(arr[n] > arr[i]){
				maxlength = Math.max(maxlength,getMaxLength(arr,i) + 1);
			}
		}
		memo[n] = maxlength;
		return maxlength;
	}
	public int getMaxLength(int[] arr){
		int n = arr.length;
		if(n == 0) return 0;
		int maxlength = 1;    //因为最小就是1
		memo = new int[n];
		Arrays.fill(memo,-1);
		for(int i = 0;i<n;i++){
			maxlength = Math.max(maxlength,getMaxLength(arr,i));
		}
		return maxlength;
	}
	public int getMaxLengthDy(int[] arr){
		int n = arr.length;
		if(n==0) return 0;
		if(n==1) return 1;
		
		int[] memo = new int[n];  //memo[i]表示的是以i为结尾的最长增长子序列的长度
		memo[0] = 1;
		memo[1] = (arr[1]>arr[0]?2:1);
		for(int i = 2;i<n;i++){
			int maxlength = 1;   //最小就是1，因为自身就是一个数
			for(int j = 0;j<i;j++){
				if(arr[i] > arr[j]){
					maxlength = Math.max(maxlength,memo[j] + 1);
				}
			}
			memo[i] = maxlength;
		}
		int lastmax = 0;
		for(int i = 0;i<n;i++){
			lastmax = Math.max(lastmax,memo[i]);
		}
		return lastmax;  //这里一定要注意，最大值并不一定出现在最后的位置
	}
	//找到最长的长度的字符串,
	//这里我考略的是使用嵌套的数组容器，这里面还有一些问题，这个字符串可能并不是一个
	//输出所有的最长的路径
	public void printLIS(int[] arr){
		int n = arr.length;
		ArrayList<LinkedList<Integer>> ls = new ArrayList<LinkedList<Integer>>();
		for(int i = 0;i<n;i++){   //将元素add到每一个元素的开头
			LinkedList<Integer> res = new LinkedList<Integer>();
			res.add(arr[i]);
			ls.add(res);
		}
		for(int i=0;i<n;i++){
			for(int j = i;j<n;j++){
				if(arr[j] > ls.get(i).getLast()){
					ls.get(i).add(arr[j]);
				}
			}
		}
		int maxlength = 0;
		for(int i = 0;i<n;i++){
			maxlength = Math.max(maxlength,ls.get(i).size());
		}
		System.out.println("max length:"+maxlength);
		//打印或者输出最长的
		for(int i = 0;i<n;i++){
			if(ls.get(i).size() == maxlength){
				LinkedList<Integer> ll = ls.get(i);
				StringBuilder ss = new StringBuilder();
				for(int k:ll){
					ss.append(new String().valueOf(k));
					ss.append(",");
				}
				System.out.println(new String(ss));
			}
		}
		
	}
}
class Solution_getMaxLength0520{
	//递归的实现,F(n)表示以arr[0]...以arr[n]为结尾的最长上升子数组的长度
	//状态转移,F(n)=   F(n-1)  如果arr[n-1]>=arr[n]
	//          =   1+F(n-1) 如果arr[n-1]<arr[n]
	public int getMaxLength(int[]arr){
		int n = arr.length;
		int maxvalue = 1;
		for(int i = 0;i<n;i++){
			maxvalue = Math.max(maxvalue,getMax(arr,i));
		}
		return maxvalue;
		
	}  
	//是以index为结尾额最长上升的长度
	private int getMax(int[] arr,int index){
		//表示的是以arr[index]为结尾的
		int maxvalue=1;
		for(int i = 0;i<index;i++){
			if(arr[index]>arr[i]){
				maxvalue = Math.max(maxvalue,getMax(arr,i) + 1);
			}
		}
		return maxvalue;
	}
}
class SolutionII{
	//如何得到最长上升子数组，得到最长上升子数组的长度
	public void subUp(int[] arr){
		int n = arr.length;
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>(n);  //res的每一个元素还是一个ArrayList
		for(int i = 0;i<n;i++){
			res.add(new ArrayList<Integer>(n));
			res.get(i).add(arr[i]);
		}
		for(int i=0;i<n;i++){   //当前的元素
			for(int j = 0;j<i;j++){  //从头到尾找得元素
				if(res.get(j).get(res.get(j).size() - 1) < arr[i]){
					res.get(j).add(arr[i]);
				}
			}
		}
		int maxlen = 0;
		for(int i = 0;i<res.size();i++){
			maxlen = Math.max(res.get(i).size(),maxlen);
		}
		System.out.println("max len:");
		System.out.println(maxlen);
		System.out.println("max len num");
		for(int i = 0;i<res.size();i++){
			if(maxlen == res.get(i).size()){
				for(int j = 0;j<res.get(i).size();j++){
					System.out.print(res.get(i).get(j));
					System.out.print(" ");
				}
			}
			System.out.println();
		}
		System.out.println();
	}
}

public class M43_LongestSubIncreasingString {
	public class Solution1{
		private int[] memo;
	
	    public int lengthOfLIS(int[] nums) {
	
	        if(nums.length == 0)
	            return 0;
	
	        memo = new int[nums.length];
	        Arrays.fill(memo, -1);
	        int res = 1;    //每个元素就是一个子序列，所以最小的子序列的长度应该是1
	        for(int i = 0 ; i < nums.length ; i ++)   //获得最长的长度
	            res = Math.max(res, getMaxLength(nums, i));
	
	        return res;
	    }
	
	    // 以 nums[index] 为结尾的最长上升子序列的长度
	    //从0.i.index遍历一遍，如果arr[index]>arr[i] res = Math.max(res,1+getMax(nums,i))
	    private int getMaxLength(int[] nums, int index){
	
	        if(memo[index] != -1)
	            return memo[index];
	
	        int res = 1;
	        for(int i = 0 ; i <= index-1 ; i ++)
	            if(nums[index] > nums[i])
	                res = Math.max(res, 1 + getMaxLength(nums, i));
	
	        return memo[index] = res;   //现在的目标是以i为结尾的最长上升子序列的长度
	    }
	}
	//动态规划解决这个问题
	public class Solution2{
		public int lengthOfLIS(int[] nums) {
	        if(nums.length == 0)
	            return 0;
	
	        // memo[i] 表示以 nums[i] 为结尾的最长上升子序列的长度
	        int memo[] = new int[nums.length];
	        Arrays.fill(memo, 1);
	        for(int i = 1 ; i < nums.length ; i ++) //我要求以i为结尾的最长上升子序列的长度，就要从头开始遍历
	            for(int j = 0 ; j < i ; j ++)   //需要从头开始遍历，然后i当前的位置
	                if(nums[i] > nums[j])
	                    memo[i] = Math.max(memo[i], 1 + memo[j]);
	
	        int res = memo[0];  //最后，所有以i为结尾的值都被求出来了，还需要遍历所有的结果数组，求出最大值也就是结果值
	        for(int i = 1 ; i < nums.length ; i ++)
	            res = Math.max(res, memo[i]);
	
	        return res;
		}

	}
	public static void main(String[] args) {
	     int nums1[] = {10, 9, 2, 5, 3, 7, 101, 18};
	     System.out.println(new M43_LongestSubIncreasingString().new Solution1().lengthOfLIS(nums1));
	        // 4
		// ---
		int nums2[] = {4, 10, 4, 3, 8, 9};
		System.out.println(new M43_LongestSubIncreasingString().new Solution2().lengthOfLIS(nums2));
		// 3
		// ---
		int nums3[] = {2, 2};
		System.out.println(new M43_LongestSubIncreasingString().new Solution1().lengthOfLIS(nums3));
		// 1		
		// ---	
		int nums4[] = {1, 3, 6, 7, 9, 4, 10, 5, 6};
		System.out.println(new M43_LongestSubIncreasingString().new Solution2().lengthOfLIS(nums4));
		// 6
		System.out.println("hahah");
		SolutionII ss = new SolutionII();
		ss.subUp(nums1);
		ss.subUp(nums2);
		ss.subUp(nums3);
		ss.subUp(nums4);
		
		Solution_getMaxLength0520 sss = new Solution_getMaxLength0520();
		System.out.println(sss.getMaxLength(nums1));
		System.out.println(sss.getMaxLength(nums2));
		System.out.println(sss.getMaxLength(nums3));
		System.out.println(sss.getMaxLength(nums4));
		
		System.out.println("0712");
		Solution_LongestSubIncreasingSequence_0712 ss3 = new Solution_LongestSubIncreasingSequence_0712();
		System.out.println(ss3.getMaxLength(nums1));
		System.out.println(ss3.getMaxLength(nums2));
		System.out.println(ss3.getMaxLength(nums3));
		System.out.println(ss3.getMaxLength(nums4));
		System.out.println(ss3.getMaxLengthDy(nums1));
		System.out.println(ss3.getMaxLengthDy(nums2));
		System.out.println(ss3.getMaxLengthDy(nums3));
		System.out.println(ss3.getMaxLengthDy(nums4));
		ss3.printLIS(nums1);
	
	}
}
