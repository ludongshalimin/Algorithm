package com.leetcode.muke;

import java.util.Arrays;

/**
 * house robber
 * 你是一个专业的小偷，打算洗劫一条街的所有房子。
 * 每一个房子里都有不同价值的宝物，
 * 但是，如果你选择偷窃连续的两栋房子，
 * 就会触发报警系统。
 * 编程求出你最多可以偷窃价值多少的宝物？
如 [ 3, 4, 1, 2 ]，则返回6 [ 3, (4), 1, (2) ]
如 [ 4, 3, 1, 2 ]，则返回6 [ (4), 3, 1, (2) ]

 * @author weifeng
 * 状态定义：考虑偷取 [x…n-1] 范围里的房子 （函数的定义）
 * 状态转移：获得的价值
 * f(0) = max{ v(0) + f(2) , v(1) + f(3) , v(2) + f(4) , … , 
                             v(n-3) + f(n-1) , v(n-2) , v(n-1) }  (状态转移方程)

 *
 */
//抢劫房子，要求是两个房子不能够紧紧挨着，求获得的抢劫房子所获得最大的价值
//f(n)表示抢劫以n为结尾的房子所获得最大的价值
//f(n) = max(f(n-1),f(n-2)+ arr[n])
class Solution_houseRobber_0618{
	//1，递归的解法
	public int getMaxValue(int[] arr){
		int n = arr.length;
		return getMaxValue(arr,n-1);
	}
	private int getMaxValue(int[] arr,int n){//以n为结尾的所获得最大的价值
		if(n == 0) return arr[0];
		if(n == 1) return Math.max(arr[0],arr[1]);
		int res = Integer.MIN_VALUE;
		res = Math.max(Math.max(res,getMaxValue(arr,n-1)),getMaxValue(arr,n-2) + arr[n]);
		return res;
		
	}
	//2,动态规划
	//memo[n]表示以n索引结尾的所获得最大的价值
	public int getMaxValueDy(int[] arr){
		int n = arr.length;
		int[] memo = new int[n];
		memo[0] = arr[0];
		memo[1] = Math.max(arr[0],arr[1]);
		for(int i = 2;i<n;i++){
			memo[i] = Math.max(memo[i-1],memo[i-2] + arr[i]);
		}
		return memo[n-1];
	}
}
class Solution_houseRobber_0518{
	//递归的方法解决问题
	//f(n):偷[0..n]所获得的最大的价值
	//= f(n-1) ；arr[n] + f(n-2)
	private int[] memo;
	public int getMaxValue(int[] arr){
		int n = arr.length;    //偷[0..n-1]个房子，得到的值是最大的
		memo = new int[n+1];
		Arrays.fill(memo,-1);
		return getMaxValue(arr,n-1);
	}
	private int getMaxValue(int[]arr,int n){  //考虑抢劫【0..n】
		if(n==0)return arr[0];
		if(n ==1) return Math.max(arr[1],arr[0]);
		if(memo[n]!=-1) return memo[n];
		int maxvalue = Integer.MIN_VALUE;
		for(int i = n;n>=3;n--){
			maxvalue = Math.max(maxvalue,Math.max(getMaxValue(arr,n-1),getMaxValue(arr,n-2)+arr[n]));
		}
		memo[n] = maxvalue;
		return maxvalue;
	}
	//使用动态规划的套路
	//从底向上的memo[i]表示偷【0..i]所获得最大的价值
	public int getMaxValueDynamic(int[] arr){
		int n= arr.length;
		int[] memo = new int[n+1];     //memo[n]表示的【0..n】所获得最大的价值
		memo[1] = arr[0];
		memo[2] = Math.max(arr[0],arr[1]);
		for(int i = 3;i<n+1;i++){   //这里的i代表的是索引
			memo[i] = Math.max(memo[i-1],memo[i-2]+arr[i-1]);
		}
		return memo[n];
	}
}
class Solution_HouseRobber_0506{
	//考略用动态规划做
	public int getMaxValue(int[] arr){
		int n = arr.length;
		int[] memo = new int[n]; //memo[i]表示偷盗【0..i】所偷的最大的利益
		memo[0] = arr[0];
		memo[1] = Math.max(arr[0],arr[1]);
		for(int i =2;i<n;i++){
			memo[i] = Math.max(memo[i-1],memo[i-2]+arr[i]);
		}
		return memo[n-1];
		
	}
}
public class M41_HouseRobber {
	//使用记忆化搜索的方式进行
	public class Solution1{
		//memo[i]表示抢劫nums[i..n)所能获得的最大的价值
		private int[] memo;
		public int rob(int[] nums){
			memo = new int[nums.length];
			Arrays.fill(memo,-1);
			return tryRob(nums,0);
		}
		//考略抢劫nums[index...nums.size())这个范围里面的所有房子
		private int tryRob(int[] nums,int index){
			if(index >= nums.length) return 0;
			if(memo[index] != -1) return memo[index];
			int res = 0;
			for(int i = index;i<nums.length;i++){
				res = Math.max(res,nums[i]+tryRob(nums,i+2));						
			}
			memo[index] = res;
			return res;
		}	
	}
	//使用动态规划的方式进行
	public class Solution2{
		public int rob(int[] nums){
			int n = nums.length;
			if(n == 0) return 0;
			//memo[i]表示抢劫nums[i...n-1]所获得的最大收益
			int [] memo = new int[nums.length];
			memo[n-1] = nums[n-1];
			for(int i = n-2;i>=0;i--){
				for(int j = i;j<n;j++){
					memo[i] = Math.max(memo[i],nums[j] +(j+2<n?memo[j+2]:0));
				}
			}
			return memo[0];
		}
	}
	//改变状态定义，深刻的理解下面这个问题的求解方式，从底到到上的进行求解
	public class Solution3{
	    public int rob(int[] nums) {

	        int n = nums.length;
	        if(n == 0)
	            return 0;

	        // memo[i] 表示考虑抢劫 nums[0...i] 所能获得的最大收益，还是从底层这样的思考比较好
	        int[] memo = new int[nums.length];
	        memo[0] = nums[0];
	        for(int i = 1 ; i < n ; i ++)
	            memo[i] = Math.max(memo[i - 1],
	                               nums[i] + (i - 2 >= 0 ? memo[i - 2] : 0));

	        return memo[n-1];
	    }
	}
	public static void main(String[] args){
		int[] arr = {4,3,1,2};
		Solution_HouseRobber_0506 s1 = new Solution_HouseRobber_0506();
		System.out.println(s1.getMaxValue(arr));
		Solution_houseRobber_0518 s2 = new Solution_houseRobber_0518();
		System.out.println(s2.getMaxValue(arr));
		System.out.println(s2.getMaxValueDynamic(arr));
		System.out.println("sasa");
		Solution_houseRobber_0618 sss = new Solution_houseRobber_0618();
		System.out.println(sss.getMaxValue(arr));
		System.out.println(sss.getMaxValueDy(arr));
	}
}
