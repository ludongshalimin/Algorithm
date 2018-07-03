package com.sort;

import com.test.SortTestHelper;

//希尔排序也称为缩小增量排序
/*
 * 先将待排序的数组元素分成多个子序列，使得每一个子序列的元素个数相对较少，然后对各个子序列分别进行直接插入排序
 * 待整个待排序列“基本有序”后，最后再对所有元素进行一次直接插入排序
 */
public class ShellSort {
	private ShellSort(){};
	public static void sort(Comparable[] arr){
		int n = arr.length;
		
		//产生序列1,4,13,40,121...
		//产生的序列什么作用？？
		int h = 1;
		while(h < n/3){
			h=3*h+1;
		}
		
		while( h >=1 ){
			for(int i = h; i<n; i++){  //h最后会退化到1，所以最后所有元素进行一次直接插入排序
				Comparable t = arr[i];
				int j = i;
				for(;j >= h&&arr[j-h].compareTo(t)>0;j-=h){
					arr[j] = arr[j-h];
				}
				arr[j] = t;
			}
			h /= 3;
		}
	}
	
	public static void main(String[] args){
//		int[] arr = {43,13,14,3,5,33,66};
//		ShellSort.sort(arr);
//		for (int i = 0;i<arr.length;i++){
//			System.out.print(arr[i]);
//			System.out.print(' ');
//		}
		int N= 100000;
		Integer[] arr = SortTestHelper.generateRandomArray(N,0,1000000);
		SortTestHelper.testSort("com.sort.ShellSort",arr);
		//近乎有序的数组
		int swapTimes = 100;
		Integer[] arr1 = SortTestHelper.generateNearOrderArray(N, swapTimes);
		SortTestHelper.testSort("com.sort.ShellSort",arr1);
		//含有大量重复键值得数组
		Integer[] arr2 = SortTestHelper.generateRandomArray(N,0,10);
		SortTestHelper.testSort("com.sort.ShellSort",arr2);
	}
}
