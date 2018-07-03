package com.sort;

import com.test.SortTestHelper;

//优化
//对于小的数组，采用插入排序
//对于近乎有序的数组(1,2,3,4...)
//归并排序可以很好的分成两部分
//快速排序，由于partition导致了分配的严重不均衡，导致退化成o(n^2)的复杂度
//随机化的思想，第一次partition选择的最小,1/n,第二次选择的最小，1/(n-1)....
public class QuickSortII {
	private QuickSortII(){}
	private static void swap(Comparable[] arr, int i,int j){
		Comparable t = arr[i];
		arr[i] = arr[j];
		arr[j] = t;
	}
	private static int partition(Comparable[] arr,int l,int r){
		//随机化的思想

		swap(arr,(int)(Math.random()*(r-l+1) + l),l);
		
		Comparable v = arr[l];
		int j = l;
		for (int i = l+1;i<=r;i++){
			if(arr[i].compareTo( v)<0){
				j++;
				swap(arr,i,j);
			}
		}
		swap(arr,l,j);
		return j;
	}
	private static void sort(Comparable[] arr,int l,int r){
		if(r - l <= 15){
            InsertionSort.sort(arr,l,r);  //在此位置不能使用InsertionSort.sort(arr),因为这样相当于每一个分支都进行了一次排序
            return;
		}
		int p = partition(arr,l,r);
		sort(arr,l,p-1);
		sort(arr,p+1,r);
	}
	public static void sort(Comparable[] arr){
		int n = arr.length;
		sort(arr,0,n-1);
	}
	public static void main(String[] args){
//		int[] arr ={43,13,14,3,5,33,66};
//		QuickSortII.sort(arr);
//		for(int i = 0;i<arr.length;i++){
//			System.out.print(arr[i]);
//			System.out.print(' ');
//		}
		int N= 100000;
		Integer[] arr = SortTestHelper.generateRandomArray(N,0,1000000);
		SortTestHelper.testSort("com.sort.QuickSortII",arr);
		//近乎有序的数组
		int swapTimes = 100;
		Integer[] arr1 = SortTestHelper.generateNearOrderArray(N, swapTimes);
		SortTestHelper.testSort("com.sort.QuickSortII",arr1);
		//含有大量重复键值得数组
		Integer[] arr2 = SortTestHelper.generateRandomArray(N,0,10);
		SortTestHelper.testSort("com.sort.QuickSortII",arr2);
	}
}
