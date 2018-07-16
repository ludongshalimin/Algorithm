package com.exercise;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 数组中某数字减去其右边的某数字得到一个数对之差，求所有数对之差的最大值
 * 例如数组：
 * {2,3，1,16,7,5,11,9}，数对之差的最大值是11（16-5）
 * @author weifeng
 *方法1：让每个数字逐个减去它右边的所有数字，并通过比较得到数对之差的最大值，总的时间复杂度是O(n2)
 *合适的要记忆：不合适的方法不需要记忆，放在脑后
 */
class Solution_getMax_0716{
	//1:右边减掉左边得到的最大的值，股票求最大的差价
	public int getMax(int[] arr){   
		int n = arr.length;
		int min = arr[0];
		int maxvalue = Integer.MIN_VALUE;
		for(int i = 0;i<n;i++){
			if(min > arr[i]){
				min = arr[i];
			}
			maxvalue = Math.max(maxvalue,arr[i]-min);
		}
		return maxvalue;
	}
	//2:左减掉右最大,那就是需要保存最大的数值
	public int getMaxValue(int[] arr){
		int n = arr.length;
		int max = arr[0];
		int maxvalue = Integer.MIN_VALUE;
		for(int i = 0;i<n;i++){
			if(arr[i]>max){
				max = arr[i];
			}
			maxvalue = Math.max(maxvalue,max-arr[i]);
		}
		return maxvalue;
		
	}
}
class Solution_getMax0607{
	public int getMax(int[] arr){         //o(n2)
		int n = arr.length;
		int maxSub = Integer.MIN_VALUE;
		for(int i = 0;i<n;i++){
			for(int j =0;j<i;j++){
				maxSub = Math.max(maxSub,arr[j]-arr[i]);
			}
		}
		return maxSub;
	}
	public int getMaxII(int[] arr){    //o(n)
		int n = arr.length;
		int maxSub = arr[0]-arr[1];
		int maxR = Math.max(arr[0],arr[1]);
		for(int i= 2;i<n;i++){
			maxSub = Math.max(maxSub,maxR-arr[i]);
			if(arr[i]>maxR){
				maxR = arr[i];
			}
		}
		return maxR;
	}
}
class Solution_getMax0506{
	//以当前的数为减数的最大值
	public int getRightSubMax(int[] arr){
		int n = arr.length;	
		int max = Math.max(arr[0],arr[1]);
		int res = arr[0] - arr[1];
		for(int i = 2;i<n;i++){
			res = Math.max(res,max-arr[i]);
			if(arr[i] > max){
				max = arr[i];
			}
		}
		return res;
	}
}
class Solution_geMaxSub0501{
	public int getMaxSub(int[] arr){
		int n = arr.length;
		int res = arr[0] - arr[1];
		int maxNum = Math.max(arr[0],arr[1]);
		for(int i = 0;i<n;i++){
			res = Math.max(res,maxNum-arr[i]);
			if(arr[i] > maxNum){
				maxNum = arr[i];
			}
		}
		return res;
	}
}

public class MaxSubDiff {
	class Solution0{   //蛮力法，求出一个数的所有右边的差值，然后返回这个差值的最大值
		public int getSubMax(int[] arr){
			int n = arr.length;
			int res = Integer.MIN_VALUE;
			for(int i = 0;i<n;i++){
				for(int j = i;j<n;j++){
					res = Math.max(res,arr[i]-arr[j]);
				}
			}
			return res;
		}
		
	}
	class Solution1{   //第一种解法：分治法
		/**
		 * 我们把数组分成两个子数组，
		 * 被减数和减数都在第一个子数组中，
		 * 被减数和减数都在第二个子数组中
		 * 被减数在第一个子数组中，是第一个子数组的最大值，减数在第二个子数组中是第二子数组的最小值
		 * 1,2,3中这三个差值的最大值就是整个数组中的问题的求解
		 */
		private int getMax(int[] arr ,int l,int r,AtomicInteger max,AtomicInteger min){  //这样的一种方法是暴力法时间复杂度为o(n2)
			if(l == r){
				max.set(arr[l]);
				min.set(arr[r]);
				return Integer.MIN_VALUE;
			}
			int mid = l+(r-l)/2;  //在l-mid,mid-r上左右两个子数组进行
			AtomicInteger lMax = new AtomicInteger(0);
			AtomicInteger lMin = new AtomicInteger(0);
			int leftMax = getMax(arr,l,mid,lMax,lMin);
			
			AtomicInteger rMax = new AtomicInteger(0);
			AtomicInteger rMin = new AtomicInteger(0);
			int rightMax = getMax(arr,mid+1,r,rMax,rMin);
			
			//对应第三种情况
			int mixMax = lMax.get() - rMin.get();
			//求数组的最大值和最小值
			if(lMax.get()>rMax.get()){
				max.set(lMax.get());
			}else{
				max.set(rMax.get());
				
			}
			if(lMin.get()<rMin.get()){
				min.set(lMin.get());
			}else{
				min.set(rMin.get());
			}
			//求最大的差值
			int allMax = (leftMax>rightMax)?leftMax:rightMax;
			allMax = (allMax>mixMax)?allMax:mixMax;
			
			return allMax;
			
		}
		public int getSubMax(int[] arr){
			int n = arr.length;
			AtomicInteger max = new AtomicInteger(0);
			AtomicInteger min = new AtomicInteger(0);
			int res = getMax(arr,0,n-1,max,min);
			return res;
		}
	}
	class Solution2{
		/**
		 * 转换法：转化成求子数组最大和的问题
		 * 构建一个长度为n-1的辅助数组 array2[n-1],且
		 * array2[i] = array[i]- array[i+1];(0<=i<n-1)
		 * 如果累加辅助数组array2从 i到j，即array2[i]+array2[i+1]+...array2[j]
		 * 有(array2[i]-array[i+1]) + (array[i+1]-array[i+2]) +...+(array[j]-arr[j+1])
		 * =arr[i] -array[j+1]
		 * 转化成：求辅助数组的最大连续子数组之和，即可得结果
		 */
		public int getSubMax(int[] arr){
			int n = arr.length;
			int res = 0;
			int [] aux = new int[n-1];   //初始化辅助数组，求这个辅助数组的最大连续子数组之和
			for(int i =0;i<n&&i+1<n;i++){
				aux[i] = arr[i] -arr[i+1];
			}
			int nAll = aux[0];   //有n个数的最大子数组之和
			int nEnd = aux[0];   //有n个数的包含最后一个元素的子数组之和
			for(int i = 0;i< n-1;i++){
				nEnd = Math.max(nEnd+aux[i],aux[i]);
				nAll = Math.max(nEnd,nAll);
			}
			return nAll;
		}
		
	}
	class Solution3{
		/**
		 * 动态规划：
		 * 定义：定义maxDiff[i]是以数组中第i个数字为减数的所有的数对之差的最大值，也就是说
		 * 	   对于任意j(j<i),maxDiff[i] >= arr[j]-arr[i].
		 * 假设现在已经求得了maxDiff[i],怎么求得maxDiff[i+1],对于maxDiff[i],肯定存在一个j,满足array[j]减去array[i]之差是最大的，
		 * 也就是array[j]应该是array[i]之前的所有数字的最大值。
		 * 当我们求maxDiff[i+1]的时候，我们需要找到第i+1个数字之前的最大值。
		 * 第i+1个数字之前的最大值有两种可能：
		 * 这个最大值可能是第i个数字之前的最大值，也可能这个最大值就是第i个数字。
		 * 我们用第i+1个数字之前的最大值减去array[i+1]，就得到了maxDiff[i+1].
		 */
		public int getSubMax(int[] arr){
			int n = arr.length;
			if(arr==null || n<2){
				return Integer.MIN_VALUE;
			}
			int max = arr[0];
			int maxDiff = max-arr[1];  //初始化对数差
			for(int i = 2;i<n;i++){
				if(arr[i-1] > max){    //i-1之前的最大值
					max = arr[i-1];
				}
				if(max-arr[i] > maxDiff){
					maxDiff = max-arr[i];
				}
			}
			return maxDiff;
			
		}
	}
	public static void main(String[] args){
		int[] arr = {2,3,20,16,13,8,14,9};
		MaxSubDiff test = new MaxSubDiff();
		Solution0 solution0 = test.new Solution0();
		System.out.println(solution0.getSubMax(arr));
		Solution1 solution1 = test.new Solution1();
		System.out.println(solution1.getSubMax(arr));
		Solution2 solution2 = test.new Solution2();
		System.out.println(solution2.getSubMax(arr));
		Solution3 solution3 = test.new Solution3();
		System.out.println(solution3.getSubMax(arr));
		

		
		System.out.println("lala");
		Solution_geMaxSub0501 s2 = new Solution_geMaxSub0501();
		System.out.println(s2.getMaxSub(arr));
		
		System.out.println("sasa");
		Solution_getMax0506 s3 = new Solution_getMax0506();
		System.out.print(s3.getRightSubMax(arr));
		
		System.out.println("0715");
		Solution_getMax_0716 s4 = new Solution_getMax_0716();
		System.out.println(s4.getMax(arr));
		System.out.println(s4.getMaxValue(arr));
	}
}
