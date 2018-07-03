package com.sort;
import java.util.Arrays;

import com.test.SortTestHelper;
//对于mergesort进行优化
//优化1：对于小的数据使用插入排序
//优化2：对于已经有序的数组不在进行merge
public class MergeSortII {
	private MergeSortII(){}
	private static void merge(Comparable[] arr,int l,int mid,int r){
		Comparable[] aux = Arrays.copyOfRange(arr,l,r+1);
		
		//初始化，i指向左半部分的起位置l,j指向右半部分起始索引位置mid+1
		int i = l;
		int j = mid + 1;
		
		for (int k = l;k<= r;k++){
			if(i>mid){  //如果左半部分已经全部处理完了
				arr[k] = aux[j-l];
				j++;
			}else if(j>r){//如果右半部分已经全部处理完了
				arr[k] = aux[i-l];
				i++;
			}else if(aux[i-l].compareTo(aux[j-l])<0){  //左半部分小于右半部分
				arr[k] = aux[i-l];
				i++;
			}else {     //左半部分>=右半部分所指元素
				arr[k] = aux[j-l];
				j++;
			}
		}
	}

	private static void sort(Comparable[] arr,int l,int r){
		if(r - l <= 15){
			InsertionSort.sort(arr,l,r);
			return;
		}
		int mid = (l+r)/2;
		sort(arr,l,mid);
		sort(arr,mid+1,r);
		if(arr[mid].compareTo(arr[mid + 1]) < 0){
			merge(arr,l,mid,r);
		}
	}
	public static void sort(Comparable[] arr){
		int n =arr.length;
		sort(arr,0,n-1);
	}
	public static void main(String[] args){
//		int[] arr = {43,13,14,3,5,33,66};
//		MergeSortII.sort(arr);
//		for(int i=0;i<arr.length;i++){
//			System.out.println(arr[i]);
//			System.out.println(' ');
//		}
		//一般性测试
		int N= 1000000;
		Integer[] arr = SortTestHelper.generateRandomArray(N,0,1000000);
		SortTestHelper.testSort("com.sort.MergeSortII",arr);
		//近乎有序的数组
		int swapTimes = 100;
		Integer[] arr1 = SortTestHelper.generateNearOrderArray(N, swapTimes);
		SortTestHelper.testSort("com.sort.MergeSortII",arr1);
		//含有大量重复键值得数组
		Integer[] arr2 = SortTestHelper.generateRandomArray(N,0,10);
		SortTestHelper.testSort("com.sort.MergeSortII",arr2);
	}
}
