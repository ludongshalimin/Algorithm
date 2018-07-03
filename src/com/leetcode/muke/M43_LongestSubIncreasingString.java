package com.leetcode.muke;

import java.util.ArrayList;
import java.util.Arrays;
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
	}
}
