package com.others;
/**
 * 对于一个有序数组，如何求两个值的和等于target
 * 给定一个数组，求两个数组之和>某值的个数
 * 给定一个数组，求三个数组元素构成多少个三角形
 * @author weifeng
 *
 */
public class M01_Triangle {
	public int getCountTwo(int[] arr,int target){//第一种方案，直接暴力，o(n^2)
		int n = arr.length;                      //第二种方案，先排序再对撞指针，o(nlogn)+o(n)
		int count = 0;
		for(int i = 0;i<n;i++){
			for(int j = 0;j<n;j++){
				if(j==i)continue;
				if(arr[i] + arr[j] >target){
					count ++;
				}
			}
		}
		return  count/2;
	}
	public int getCountTwoI(int[] arr,int target){  //假设这时候是已经排好序的
		int n = arr.length;
		int count = 0;
		int i=0;
		int j = n-1;
		while(i<j){
			if(arr[i]+arr[j] < target){
				i++;
			}else{
				count+=j-i;
				j--;
			}
		}
		return count;
		
	}
	public int getCountThree(int[] arr){  //o(n^3)的复杂度
		int n = arr.length;
		int count = 0;
		if(n<3)return 0;
		for(int i = 0;i<n;i++){
			for(int j = 0;j<n;j++){
				if(i==j)continue;
				for(int k = 0;k<n;k++){
					if(i==k||j==k)continue;
					if(arr[i]+arr[j]>arr[k]&&arr[i]+arr[k]>arr[j]&&arr[j]+arr[k]>arr[i]){
						count++;
					}
				}
			}
		}
		return count/3/2;  //为什么是除以6呢？因为是三重循环，没有遵照顺序的方式方式，1,2,3,、1,3,2 均算作了一个
	}
	public int getCountThreeII(int[] arr){  //先排序o(nlogn)，然后利用o(n^2),对撞指针，
		int n = arr.length;                 //两个较小边的和>第三边，则可以构成三角形
		int count = 0;
		//假设这地方已经排好序了
		if(n<3)return 0;
		for(int k=n-1;k>=2;k--){
			int l = 0;
			int r = k-1;
			while(l<r){
				if(arr[l]+arr[r] >arr[k]){
					count +=r-l ;
					r--;   //这个地方r不能就这样减减了，因为
				}else{
					l++;
				}
			}
		}
		return count;
	}
	public static void main(String[] args){
		int[] arr = {3,5,7,8,9};
		M01_Triangle ss = new M01_Triangle();
		System.out.println(ss.getCountTwo(arr,7));
		System.out.println(ss.getCountTwoI(arr,7));
		System.out.println(ss.getCountThree(arr));
		System.out.println(ss.getCountThreeII(arr));
	}
}
