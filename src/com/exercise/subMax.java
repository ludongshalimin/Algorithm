package com.exercise;
//给定一个数组，arr,求 max(arr[j] - arr[i]) 其中0<=i<j<arr.length;
//这是一道在滴滴面试的一道题，当时采用了一种比较笨的方法做出来的

//在去阿里高德面试的时候，也遇到了这个问题
class Solution_subMax_0716{
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
}
class Solution_minSub0606{
	public int getMinSub(int[] arr){
		int n = arr.length;
		int minValue = arr[1] - arr[0];
		int min = Math.min(arr[1],arr[0]);
		for(int i = 2;i<n;i++){
			minValue = Math.min(minValue,arr[i]-min);
			if(arr[i]<min){
				min = arr[i];
			}
		}
		return minValue;
	}
}
class Solution_minSub0506{
	//以arr[i]为被减数的最大值
	public int getLeftSubMax(int[] arr){
		int n = arr.length;
		int min = Math.min(arr[0],arr[1]);
		int res = arr[1] - arr[0];
		
		for(int i = 2;i<n;i++){
			res = Math.max(res,arr[i]-min);
			if(arr[i] < min){
				min = arr[i];
			}
		}
		return res;
	}
}
class Solution_0501{
	//思路:以当前的额数为被减数，所获得的最大值，如果，当前的数小于前面保存的最小的数，则更新最小的数
	public int getSubMax(int[] arr){
		int n = arr.length;
		int res = arr[1] - arr[0];
		int minNum = Math.min(arr[1],arr[0]);
		for(int i = 2;i<n;i++){
			res = Math.max(res,arr[i] - minNum);
			if(minNum > arr[i]){
				minNum = arr[i];
			}
		}
		return res;
	}
}
public class subMax {
	public int subMax1(int[] arr){
		int n = arr.length;
		int res = 0;
		for(int j=0;j<n;j++){
			for(int i =0;i<j;i++){ //每次都需要遍历一遍，这样严重的消耗了时间
				res = Math.max(res,arr[j]-arr[i]);
			}
		}
		return res;
	}
	/*
	 */
	/**
	 * 动态规划的思想，最小值一定出现在最左边
	 * @param arr
	 * @return
	 */
	public int subMax(int[]arr){
		int n = arr.length;
		if(n<2)return 0;   //这种异常的情况怎么处理，既然0<=i<j<n就要必须要求有两个以上的数
		int min =(arr[0]>arr[1]?arr[1]:arr[0]);
		int res = (arr[0]>arr[1]?arr[0]-min:arr[1]-min);
		for(int i =2;i<n;i++){
			if(arr[i]-min > res){
				res = arr[i]-min;
			}
			if(arr[i]<min){
				min = arr[i];
			}
		}
		return res;
	}
	public static void main(String[]args){
		int [] arr = {5,8,2,9,4,3,2};
		subMax test = new subMax();
		System.out.println(test.subMax1(arr));
		
		System.out.println("lalal");
		Solution_0501 s1 = new Solution_0501();
		System.out.println(s1.getSubMax(arr));
		
		System.out.println("sasasaa");
		Solution_minSub0506 s2 = new Solution_minSub0506();
		System.out.println(s2.getLeftSubMax(arr));
		
		System.out.println("0715");
		Solution_subMax_0716   s3 = new Solution_subMax_0716();
		System.out.println(s3.getMax(arr));
	}
}
