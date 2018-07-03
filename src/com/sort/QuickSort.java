package com.sort;

import com.test.SortTestHelper;

public class QuickSort {
	private QuickSort(){}
	private static void swap(Comparable[] arr,int i,int j){
		Comparable t = arr[i];
		arr[i] = arr[j];
		arr[j] = t;
	}
	private static int partition(Comparable[] arr,int l,int r){
		Comparable v = arr[l];
		int j = l;
		for (int i = l+1;i<=r;i++){
			if(arr[i].compareTo( v)<0){
				j++;
				swap(arr,j,i);
			}
		}
		swap(arr,j,l);
		return j;
	}
	private static void sort(Comparable[] arr,int l,int r){
		if(l>=r)return;
		int p = partition(arr,l,r);
		sort(arr,l,p-1);
		sort(arr,p+1,r);
	}
	public static void sort(Comparable[] arr){
		int n = arr.length;
		sort(arr,0,n-1);
	}
	public static void main(String[] args){
//		int[] arr = {43,13,14,3,5,33,66};
//		QuickSort.sort(arr);
//		for(int i =0;i<arr.length;i++){
//			System.out.println(arr[i]);
//			System.out.println(' ');
//		}
		//一般性测试
		int N= 100000;
		Integer[] arr = SortTestHelper.generateRandomArray(N,0,1000000);
		SortTestHelper.testSort("com.sort.QuickSort",arr);
		//近乎有序的数组
		int swapTimes = 100;
		Integer[] arr1 = SortTestHelper.generateNearOrderArray(N, swapTimes);
		SortTestHelper.testSort("com.sort.QuickSort",arr1);
		//含有大量重复键值得数组
		Integer[] arr2 = SortTestHelper.generateRandomArray(N,0,10);
		SortTestHelper.testSort("com.sort.QuickSort",arr2);
	}
}
