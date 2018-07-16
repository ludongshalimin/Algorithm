package com.array;

//最大子数组之和
/**
 * 一个有n个元素的数组，这n个元素，可以是正数也可以是负数，
 * 数组中连续的一个或者多个元素可以组成一个连续的子数组，
 * 一个数组可能有多个这种连续的
 * 子数组，求子数组和的最大值。
 * @author weifeng
 * 请注意这个数据是连续的
 * 
 * 动态规划的思想：
 * f(n)以arr[n]为结尾的子数组和的最大值
 * f(n) = Math.max(f(n-1),f(n-1)+arr[n],arr[n])
 *
 */
class Solution_MaxSubArray0715{
	//[0..n]的最大值
	//这里的最大值包含三部分F(n) = F(n-1) + arr[n]
	//						= F(n-1)
	//						= arr[n]
	//这样做显然是不合适的，因为要求的是连续的
	//这样上面的情况，是谈不上连续的，所以应该怎么处理呢？
//	private int getMax(int[] arr,int n){
//		
//	}
	//memo[i]表示的是以i为结尾的最大的子数组之和
	public int getMaxValueDy(int[] arr){
		int n = arr.length;
		int[] memo = new int[n];
		memo[0] = arr[0];
		int maxValue = Integer.MIN_VALUE;
		for(int i = 1;i<n;i++){
			memo[i] = Math.max(memo[i-1] + arr[i],arr[i]);
			maxValue = Math.max(maxValue,memo[i]);
		}
		return maxValue;
	}
	
	//动态规划的简化版本
	public int getMaxValue(int[] arr){
		int n = arr.length;
		int n_end = 0;
		int n_all = 0;
		for(int i = 0;i<n;i++){
			n_end = Math.max(n_end + arr[i],arr[i]);
			n_all = Math.max(n_end,n_all);
		}
		return n_all;
	}
}
class Solution_MaxSubArray0605{
	public int getMaxD(int[] arr){  //动态规划的套路，memo[n]表示的是以arr[n]为结尾的最大数的和
		int n = arr.length;
		int[] memo = new int[n];
		int resMax = Integer.MIN_VALUE;
		for(int i = 0;i<n;i++){
			memo[i] = arr[i];
		}
		for(int i = 1;i<n;i++){
			memo[i] = Math.max(memo[i],memo[i-1]+arr[i]);
			resMax = Math.max(memo[i],resMax);
		}
		return resMax;
	}
	public int getMax(int[] arr){
		int n = arr.length;
		int n_end =  arr[0];   //表示的是以arr[n]为结尾的最大子数组的和
		int n_all =  arr[0];   //表示的是包含
		for(int i = 0;i<n;i++){
			n_end = Math.max(arr[i],n_end + arr[i]);
			n_all = Math.max(n_end,n_all);
		}
		return n_all;
	}
}
class Solution_MaxSubArray{
	//如何体现连续性
	//memo[i]代表索引为[0..i]的最大连续子数组之和
	//n_all:包含0....arr[i-1]的最大子数组的和
	//n_end:包含
	//n_all = n_end
	//memo[i]  = max(memo[i-1]+arr[i],arr[i],memo[i-1]),这样是错误的如何体现连续性呢？就是必须要计算的arr[i]
	//memo[i] = max(memo[i-1]+arr[i],arr[i]), 这个答案是对的，就是必须是arr[i]
	//res = max(memo[i],res)
	public int maxSub(int[] arr){
		int n = arr.length;
		int res = Integer.MIN_VALUE;
		int[] memo = new int[n];  //这里的空间复杂度是o(n)
		memo[0] = arr[0];
		res = arr[0];
		for(int i = 1;i<n;i++){   //初始化
			memo[i] = Math.max(memo[i-1]+arr[i],arr[i]); //不是连续的，会倾向于选择所有的非零的数字之和
			res = Math.max(memo[i],res);
		}
		return res;
	}
	public int maxSubI(int[] arr){
		int n = arr.length;
		int all=arr[0];   //表示的是包含i的最大连续子数组之和
		int end = arr[0]; //以i结尾的最大连续子数组之和
		for(int i = 0;i<n;i++){
			end = Math.max(arr[i],end+arr[i]);
			all = Math.max(end,all);
		}
		return all;
	}
	
}



public class MaxSubArray {
	//暴力解法,算法的复杂度为o(n^3)
	public static int maxSubSum(int[] arr){
		int n = arr.length;
		int maxSum = 0;
		for( int i = 0;i<n;i++){
			for(int j = i ;j<n;j++){
				int tempSum = 0;
				for(int k=i;k<j;k++){
					tempSum += arr[k];
				}
				if(tempSum > maxSum){
					maxSum = tempSum;
				}
			}
		}
		return maxSum;
	}
	//动态规划的思路求解
	/**
	 * 根据数组的最后一个元素arr[n-1]与最大子数组的关系分为以下三种情况
	 * 1:最大子数组的包含arr[n-1],即以arr[n-1]结尾。
	 * 2：arr[n-1]单独构成最大子数组
	 * 3：最大子数组不包含arr[n-1],那么求arr[1....n-1]的最大子数组可以转换成为求arr[1....n-2]的最大子数组
	 * 假设已经计算出：
	 *(arr[0],....arr[i-2])最大的一段数组和all[i-2],同时也计算出(arr[0],...,arr[i-1])中包含arr[i-1]的最大一段数组和为end[i-1]
	 *则可以利用以下关系：all[i-1] = max{arr[i-1],end[i-1],all[i-2]}
	 * @param args
	 */
	private static int max(int m,int n){
		return m > n ? m:n;
	}
	public static int maxSubSumII(int[] arr){
		int n = arr.length;
		int nAll = arr[0]; //有n个数字数组的最大子数组之和
		int nEnd = arr[0]; //有n个数字数组包含最后一个元素的子数组的最大和
		for(int i = 0;i<n;i++){
			nEnd = max(nEnd + arr[i],arr[i]);
			nAll = max(nEnd,nAll);
		}
		return nAll;
	}
//	
//	//自己写的最大子数组之和,这个是最大上升子数组的和
//	public static int maxSumII(int[] arr){
//		int n = arr.length;
//		int[] memo= Arrays.copyOfRange(arr,0,n);
//		for(int i = 0;i<n;i++){
//			if(i+1<n&&arr[i]<arr[i+1]){
//				memo[i+1]=memo[i]+arr[i+1];
//			}
//		}
//		int resmax=memo[0];
//		for(int i=1;i<n;i++){
//			if(resmax<memo[i]){
//				resmax = memo[i];
//			}
//		}
//		return resmax;
//	}
	//自己思考实现,这个是所有的正数的相加得到的和
	private static int getSum(int[]arr ,int index){
		if(index == 0)return arr[0];
		int res = 0;
		res = Math.max(Math.max(arr[index],getSum(arr,index-1)),arr[index]+getSum(arr,index-1));
		return res;
		
	}
	public static int maxSubSumMine(int[] arr){
		int n = arr.length;
		return getSum(arr,n-1);
	}
	public static void main(String[] args){
		int[] arr={1,-2,4,8,-4,7,-1,-5,3,4,-2};
		System.out.println(maxSubSum(arr));
		System.out.println(maxSubSumII(arr));
		
		Solution_MaxSubArray ss = new Solution_MaxSubArray();
		System.out.println(ss.maxSub(arr));
		System.out.println(ss.maxSubI(arr));

		System.out.println("hahah");
		Solution_MaxSubArray0605 sss = new Solution_MaxSubArray0605();
		System.out.println(sss.getMax(arr));
		System.out.println(sss.getMaxD(arr));
		
		System.out.println("0716");
		Solution_MaxSubArray0715 ssss = new Solution_MaxSubArray0715();
		System.out.println(ssss.getMaxValue(arr));
		System.out.println(ssss.getMaxValueDy(arr));
	}
}
