package com.sort;

import com.test.SortTestHelper;

//对于含有大量重复键值得数组，快排的效率会大大降低。比如100万的数据进行排序，每一个数据的取值范围在[1,10]之间
//为什么? arr[l........p] <= arr[p] < arr[p+1.....r]
//对于无论怎么划分等号，等号的值给左边还是给右边，都会导致一边的数据倾斜
//如何解决这个问题：arr[l]....arr[i]......arr[j]....arr[r]

public class QuickSortIII {
	private QuickSortIII(){}
    private static void swap(Comparable[] arr, int i, int j) {
        Comparable t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
	private static int partition(Comparable[] arr,int l,int r){
		swap(arr,l,(int)(Math.random()*(r-l+1)) + l);
		Comparable v = arr[l];
		int i = l+1;
		int j = r;
		while(true){
			while(i<=r&&arr[i].compareTo(v)<0)i++;
			while(j>=l+1&&arr[j].compareTo(v) > 0)j--;
			if(i > j)break;
			swap(arr,i,j);//在这个位置，i和j首次出现了 。arr[i]>v首次出现，arr[j]<v首次出现
			i++;
			j--;
		}
		swap(arr,l,j);
		return j;
	}

    private static void sort(Comparable[] arr, int l, int r){

        // 对于小规模数组, 使用插入排序
        if( r - l <= 15 ){
            InsertionSort.sort(arr,l,r);  //在此位置不能使用InsertionSort.sort(arr),因为这样相当于每一个分支都进行了一次排序
            return;
        }

        int p = partition(arr, l, r);
        sort(arr, l, p-1 );
        sort(arr, p+1, r);
    }
	public static void sort(Comparable[] arr){
		int n = arr.length;
		sort(arr,0,n-1);
	}
	public static void main(String[] args){
//		int[] arr = {43,13,14,3,5,33,66};
//		QuickSort.sort(arr);
//		for (int i = 0;i<arr.length;i++){
//			System.out.print(arr[i]);
//			System.out.print(' ');
//		}
		int N= 100000;
		Integer[] arr = SortTestHelper.generateRandomArray(N,0,1000000);
		SortTestHelper.testSort("com.sort.QuickSortIII",arr);
		//近乎有序的数组
		int swapTimes = 100;
		Integer[] arr1 = SortTestHelper.generateNearOrderArray(N, swapTimes);
		SortTestHelper.testSort("com.sort.QuickSortIII",arr1);
		//含有大量重复键值得数组
		Integer[] arr2 = SortTestHelper.generateRandomArray(N,0,10);
		SortTestHelper.testSort("com.sort.QuickSortIII",arr2);
	}
}
