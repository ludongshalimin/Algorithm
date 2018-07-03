package com.sort;

public class InsertionSort {
	//不允许产生实例
	private InsertionSort(){};
	
	private static void swap(Comparable[] arr,int i,int j){
		Comparable t = arr[i];
		arr[i] = arr[j];
		arr[j] = t;
	}
	
	//这个函数相当于从位置0一直排序
	public static void sort(Comparable[] arr){
		int n = arr.length;
		
		for(int i = 0;i<n;i++){
			//寻找arr[i]合适的插入位置
			Comparable t = arr[i];
			int j = i;
			for(;j>0 && arr[j-1].compareTo(t)>0;j--){
				arr[j] = arr[j-1];
			}
			arr[j] = t;
		}
	}
    // 对arr[l...r]的区间使用InsertionSort排序
    public static void sort(Comparable[] arr, int l, int r){

        for( int i = l + 1 ; i <= r ; i ++ ){
            Comparable e = arr[i];
            int j = i;
            for( ; j > l && arr[j-1].compareTo(e) > 0 ; j--)
                arr[j] = arr[j-1];
            arr[j] = e;
        }
    }
	public static void main(String[] args){
		Comparable[] arr = {43,13,14,3,5,33,66};
		InsertionSort.sort(arr);
		for (int i = 0;i<arr.length;i++){
			System.out.print(arr[i]);
			System.out.print(' ');
		}
//		int N= 10000;
//		Integer[] arr = SortTestHelper.generateRandomArray(N,0,100000);
//		SortTestHelper.testSort("com.sort.InsertionSort",arr);
	}
}
