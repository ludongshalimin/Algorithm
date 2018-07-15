package com.leetcode.muke;
/**
 * 给定一个整型数组和一个数字s，
 * 找到数组中最短的一个连续子数组，使得连续子数组的数字和sum >= s，
 * 返回这个最短的连续子数组的长度值
 * 如，给定数组[2, 3, 1, 2, 4, 3], s = 7
 * 答案为[4, 3]，返回2

 * @author weifeng
 * 滑动窗口
 *
 *注意：最短的连续子数组
 */
class Solution_shortestLen0715{
	//最短的使得连续子数组的和sum> = s
	public int getLen(int[] arr,int s){
		int n = arr.length;
		int i =0;
		int j = -1;
		int sum = 0;
		int min = n+1;
		while(i<n){
			if(j+1<n&& sum<s){
				j++;
				sum += arr[j];
			}else{
				sum -= arr[i];
				i++;
			}
			//已经到头了，或者sum>=s
			if(sum >= s){
				min = Math.min(min,j-i+1);
			}
		}
		if(min == n+1){
			min = 0;
		}
		return min;
	}
}
class Solution_shortestLen0622{
	public int getShortestLen(int[] arr,int s){
		int n = arr.length;
		int sum = 0;
		int i = 0;
		int j = -1;   //我们的窗口是不存在的
		int minLen = Integer.MAX_VALUE;
		while(i<n){
			if(j+1<n&&sum <s){
				j++;
				sum = sum +arr[j];
			}else{
				sum =sum - arr[i];
				i++;
			}
			if(sum >= s){
				minLen = Math.min(minLen,j-i+1);  //这是两个索引值的相减，所以需要加1
			}
		}
		return minLen;
	}
}
class Solution_shortestLen0608{
	public int getLen(int[] arr,int s){
		int n = arr.length;
		int minLen = n+1;
		int i =0;
		int j =0;
		int sum = 0;
		while(i<n){
			if(j<n&&sum<s){
				sum = sum+arr[j];
				j++;
			}else{   //这里面有两种情况一种是j已经到头了，一种是
				sum = sum - arr[i];
				i++;
			}
			if(sum >= s){    //如果大于就进行计算
				minLen = Math.min(minLen,j-i);
			}
		}
		if(minLen == n+1){
			minLen = 0;
		}
		return minLen;
	}
}
class Solution_shortestLen0517{
	public int shortestLen(int[] arr,int s){
		int n = arr.length;
		int minLen = n;  //最大设为n
		int sum = 0;
		int j =0;
		int i =0;
		while(j<n){
			if(i<n && sum<s){
				sum += arr[i];
				i++;
			}else{    //要么已经大于等于n，要么大于等于
				sum -= arr[j];
				j++;
			}
			if(sum >= s){     //保证只大于等于s的时候，才进行运算
				minLen = Math.min(minLen,i-j);
			}
		}
		if(minLen == n)minLen = 0;
		return minLen;
	}
}

public class M04_MinimumSizeSubarraySum {
	public static int minSubArrayLen(int[]nums,int s){
		if(s<= 0||nums == null)return -1;
		int l = 0,r = -1; //nums[l...r]为我们的滑动窗口，为-1表示的是窗口不存在
		int sum = 0;
		int res = nums.length + 1;  //res是取不到这个值得,表示的是取到的最大的上界
		while(l < nums.length){
			if(r + 1 < nums.length && sum <s){   //先尝试，这样保证了r 最大就是length -1
				r++;
				sum += nums[r];
			}else{   //r +1 >length 或者sum >=s,由于上文中保证了r的大小，所以这里只有sum > s
				sum -= nums[l];
				l++;
			}
			if(sum >= s){   //只要是大于，就求一个最小的长度
				res = Math.min(res,r-l+1);   //因为l..r代表的是索引，所以需要加1
			}
		}
		if(res == nums.length + 1){
			return 0;
		}
		return res;
	}
	public static void main(String[] args){
//		Test instance = new Test();
		int[] arr = {7, 3, 1, 2, 4, 3};
		//System.out.println(instance.minLength(arr,7));
		System.out.println(minSubArrayLen(arr,7));
		Solution_shortestLen0517 sss = new Solution_shortestLen0517();
		System.out.println(sss.shortestLen(arr,7));
		
		System.out.println("sasa");
		Solution_shortestLen0608 ss = new Solution_shortestLen0608();
		System.out.println(ss.getLen(arr,7));
		
		System.out.println("0623");
		Solution_shortestLen0622 s3 = new Solution_shortestLen0622();
		System.out.println(s3.getShortestLen(arr,7));
		System.out.println("0715");
		Solution_shortestLen0715 s4 = new Solution_shortestLen0715();
		System.out.println(s4.getLen(arr,7));
	}
}
