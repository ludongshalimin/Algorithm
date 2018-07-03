package com.sort;

import com.test.SortTestHelper;

public class SelectionSort {
	//算法不允许产生任何实例，默认排序从小到大
	private SelectionSort(){};
	//交换数组i和j的位置
	private static void swap(Comparable[] arr ,int i,int j){
		Comparable t = arr[i];
		arr[i] = arr[j];
		arr[j] = t;
	}
	//选择法：从第二个位置开始找到最小的，然后和第一个位置进行交换；从第三个位置往后找到最小的和第二个位置进行交换
	public static void sort(Comparable[] arr){
		int n = arr.length;
		for(int i = 0;i<n;i++){
			int minIndex = i;     
			for(int j = i+1;j<n;j++){
				if(arr[j].compareTo(arr[minIndex]) < 0){
					minIndex = j;
				}
			}
			swap(arr,i,minIndex);
		}
	}
	public static void main(String[] args){
//		int[] arr = {43,13,14,3,5,33,66};
//		SelectionSort.sort(arr);
//		for (int i = 0;i<arr.length;i++){
//			System.out.print(arr[i]);
//			System.out.print(' ');
//		}
		int N= 10000;
		Integer[] arr = SortTestHelper.generateRandomArray(N,0,1000000);
		SortTestHelper.testSort("com.sort.SelectionSort",arr);
		//近乎有序的数组
		int swapTimes = 100;
		Integer[] arr1 = SortTestHelper.generateNearOrderArray(N, swapTimes);
		SortTestHelper.testSort("com.sort.SelectionSort",arr1);
		//含有大量重复键值得数组
		Integer[] arr2 = SortTestHelper.generateRandomArray(N,0,10);
		SortTestHelper.testSort("com.sort.SelectionSort",arr2);
	}
	
}
