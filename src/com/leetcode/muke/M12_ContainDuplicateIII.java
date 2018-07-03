package com.leetcode.muke;

import java.util.TreeSet;

/**
 * 给出一个整形数组nums，是否存在索引i和j，
 * 使得nums[i]和nums[j]之间的差别不超过给定的整数t，
 * 且i和j之间的差别不超过给定的整数k。
 * @author weifeng
 * 既要求索引，又要求值，这里面用到了二叉树的顺序性
 * 索引值之差，不能大于一个整数，然后
 *
 */
public class M12_ContainDuplicateIII {
	public boolean containsNearbyAlmostDuplicate(int[] nums,int k,int t){
		//为什么用longlong，因为在leetcode测试上会有大数据溢出
		TreeSet<Long>record = new TreeSet<Long>();
		for(int i = 0;i<nums.length;i++){  //这里面用到了ceiling，ceil取了大于这个数的最小值
			if(record.ceiling((long)nums[i] - (long)t) != null &&    //首先是这个ceil得存在， 在t的范围内  nums[i]-t  nums[i]  nums[i]+t
					(record.ceiling((long)nums[i])-t) <= (long)nums[i] + (long)t){
				return true;
			}
			record.add((long)nums[i]);
			if(record.size() == k+1){     //不光要保证两个重复的数，索引不能超过K。两个索引上的值，也不能超过t
				record.remove((long)nums[i-k]);
			}
		}
		return false;
	}
	private static void printBool(boolean b){
		System.out.println(b?"true":"false");
	}
	public static void main(String[] args){
		
	}
}
