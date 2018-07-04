package com.others;
/**
 * leetcode 第55题
 * 
 * @author weifeng
 * 给一个非零的数组，从数组的起始位置开始，
 * 每一个数组的值代表了跳跃的最大的长度
 * 问，是否可以到达最后的索引
 * 最终提交结果：记忆化搜索的方法过了
 *          动态规划的方法，过了74个case(总共75个)
 */
public class M06_JumpGames {
	//f(n)表示，从0开始可以到达索引n
    private boolean[] visit;
    private boolean[] status;
    private boolean isArrived(int[]nums,int n){  
		if(n==0)return true;
        if(visit[n])return status[n];
        visit[n] = true;
		for(int i =0;i<n;i++){
			if(nums[i]>=n-i&&isArrived(nums,i)){
                status[n] = true;
				return true;
			}
		}
        status[n] = false;
		return false;
	}
    public boolean jump(int[] nums) {
        int n = nums.length;
        visit = new boolean[n];
        status = new boolean[n];
        return isArrived(nums,n-1);
    }
    public boolean jumpDy(int[] nums){
    	int n = nums.length;
    	boolean[] memo = new boolean[n];  //memo[n]代表了是否可以从0经过跳跃到n索引的位置
    	memo[0] = true;
    	for(int i =0;i<n;i++){
    		for(int j = 0;j<i;j++){
    			if(nums[j]>=i-j&&memo[j]){
    				memo[i] = true;
    			}
    		}
    	}
    	return memo[n-1];
    }
    public static void main(String[] args){
    	int[] arr0 ={2,3,1,1,4};
    	int[] arr1 = {3,2,1,0,4};
    	M06_JumpGames ss = new M06_JumpGames();
    	System.out.println(ss.jump(arr0));
    	System.out.println(ss.jump(arr1));
    	System.out.println("dp" + " " + ss.jumpDy(arr0));
    	System.out.println("dp" + " " + ss.jumpDy(arr1));
    	
    }
}
