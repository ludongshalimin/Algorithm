package com.array;
//如何用o(n)的时间复杂度求解数组中第二大的元素
//先定义两个变量，一个用来存储数组中的最大值，一个用来存储数组元素中第二大的值，初始值为负值
/*
遍历整个数组：
如果数组元素的值比最大数变量的值大，则将第二大变量的值更新为最大数变量的值，最大数变量的值更新为该数组元素的值。
否则，判断数组元素的值是否比第二大元素的值大，如果比第二大元素的值大则，更新第二大元素的值为数组元素的值

 * */
class Solution_SecondMax0605{
	public int getSecondMaxII(int[] arr){
		int n = arr.length;
		if(n<2) return Integer.MIN_VALUE;   //我们要求第二最大的数，所以首先是要有至少两个数
		int first=Math.max(arr[0],arr[1]) ;  //第一大的数据
		int second= arr[0]>arr[1]?arr[1]:arr[0];  //第二大的数据
		for(int i = 2;i<n;i++){
			if(first <arr[i]){
				second = first;
				first = arr[i];
			}else if(first >=arr[i] && second < arr[i]){
				second = arr[i];
			}else{
				
			}
		}
		return  second;
	}
}
class Solution_SecondMax{
	public int getSecMax(int[] arr){
		int n = arr.length;
		if(n < 2) return Integer.MIN_VALUE;  //这是两个
		int firstMax = Math.max(arr[0],arr[1]);
		int secMax = Math.min(arr[0],arr[1]);
		for(int i = 2;i<n;i++){
			if(arr[i] > firstMax){
				secMax = firstMax;
				firstMax = arr[i];
			}else if(arr[i] >secMax && arr[i] < firstMax){
				secMax = arr[i];
			}else{
				
			}
		}
		return secMax;
	}
}
class SolutionSecMax{
	public int getSecMax(int[] arr){
		int n = arr.length;
		int firstMax = Integer.MIN_VALUE;
		int secondMax = Integer.MIN_VALUE;
		for(int i = 0;i<n;i++){
			if(arr[i] > firstMax){
				secondMax = firstMax;
				firstMax = arr[i];		
			}else if( arr[i]<firstMax&&arr[i] > secondMax){
				secondMax = arr[i];
			}else{
				
			}
		}
		return secondMax;
	}
}
public class SecondMax {
	public static int FindSecondMax(int[] arr){
		int n = arr.length;
		int maxnumber = arr[0];
		int secMax = Integer.MIN_VALUE;
		for(int i = 1;i < n;i++){
			if(arr[i] > maxnumber){
				secMax = maxnumber;
				maxnumber = arr[i];
			}else{
				if(arr[i] > secMax){
					secMax = arr[i];
				}
			}
		}
		return secMax;
	}
	public static void main(String[] args){
		int[] arr = {3,5,11,6,22,10};
		System.out.println(FindSecondMax(arr));
		SolutionSecMax ss = new SolutionSecMax();
		System.out.println(ss.getSecMax(arr));
		
		System.out.println("hahah");
		Solution_SecondMax0605 sss = new Solution_SecondMax0605();
		System.out.println(sss.getSecondMaxII(arr));
	}
}
