package com.others;

import java.util.Arrays;

/**
 * jump games的递进版本
 * @author weifeng
 * 给一个非零的数组，从数组的起始位置开始，
 * 每一个数组的值代表了跳跃的最大的长度
 * 目标是到达最后的索引的最小跳跃数
 * 提交结果：都过了91个case(共计92个),最后一个又长，数又大
 */
public class M07_JumpGamesII {
	//f(n)表示，从0开始到索引n，所需要的最小跳跃数
	private int[] memo;
	private int minStep(int[] nums,int n){
		int min = nums.length;  //最大的值
		if(memo[n] != -1)return memo[n];
		if(n ==0){
			memo[0] = 0;
			return 0;
		}
		for(int i = 0;i<n;i++){
			if(nums[i] >= n-i){
				min = Math.min(min,minStep(nums,i)+1);
			}
		}
		if(min == nums.length){  //如果还等于这个值，相当于没有变化
			memo[n] = 0;
			return 0;
		}
		memo[n] = min;
		return min;
	}
	public int jump(int[] nums) {
	    int n = nums.length;
	    memo = new int[n];
	    Arrays.fill(memo,-1);
	    return minStep(nums,n-1);
	    
	}
	public int jumpDy(int[] nums){
		int n = nums.length;
		int[]memo1 = new int[n];  //memo[i]表示，0到i所需的最短的跳跃次数,由于本身都能跳跃到自己，所以不需要再进行初始化了
		for(int i =0;i<n;i++){
			int min = n;
			for(int j =0;j<i;j++){
				if(nums[j]>=i-j){
					min = Math.min(min,memo1[j] + 1);
				}
			}
			if(min != n){       //表示存在最小值，如果不存在则此位置表示为0
				memo1[i] = min;
			}
		}
		return memo1[n-1];
	}
	public static void main(String[] args){
		int[] arr = {2,3,1,1,4};
		M07_JumpGamesII ss = new M07_JumpGamesII();
		System.out.println(ss.jump(arr));
		System.out.println("dp:" + " " + ss.jumpDy(arr));
	}
}
