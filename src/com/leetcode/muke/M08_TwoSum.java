package com.leetcode.muke;

import java.util.HashMap;

/**
 * 给出一个整型数组nums。返回这个数组中两个数字的索引值i和j，使得nums[i] + nums[j] 等于一个给定的target值。
索引从0开始计算还是从1开始计算？
没有解怎么办？
有多个解怎么办？ 保证有唯一解

 * 使用查找表解决经典的two sum问题，
 * 这里面的array是没有顺序的
 * 如果把array进行排序，然后使用对撞指针的方式进行o(nlogn) +o(n)的复杂度
 * @author weifeng
 * 注意这个two sum和对撞指针的区别，这个two sum是没有顺序的
 * 
 * 这个two sum 是没有顺序的
 *
 */
public class M08_TwoSum {
	public int[] twoSum(int[] nums,int target){
		HashMap<Integer,Integer> record = new HashMap<Integer,Integer>();
		for(int i = 0;i<nums.length;i++){
			int complement = target-nums[i];
			if(record.containsKey(complement)){   //我要看的是里面有没有这两个数的和
				int[] res = {i,record.get(complement)};  //找得到两个数和为target，返回这两个数的索引
				return res;
			}         //解决覆盖问题，如果原来的Map中的已经有了，然后就会发生覆盖，不过对于我们这个问题来说没有问题，因为，是要找到两个数据的和为target
			record.put(nums[i],i);
		}
		throw new IllegalStateException("no result");
	}
	private static void printArr(int[] nums){
		for(int num:nums){
			System.out.print(num +" ");
		}
		System.out.println();
	}
	public static void main(String[] args){
		int[] nums = {0,4,3,0};
		int target = 0;
		printArr((new M08_TwoSum()).twoSum(nums,target));
	}
}
