package com.heap;

import com.test.SortTestHelper;

public class IndexMinHeapSort {
	private IndexMinHeapSort(){}
	public static void sort(Comparable[] arr){
		int n = arr.length;
		IndexMinHeap <Comparable> indexMinHeap = new IndexMinHeap <Comparable>(n);
		for(int i = 0;i<n;i++){
			indexMinHeap.insert(i,arr[i]);
		}
		for(int i = 0;i < n;i++){
			arr[i] = indexMinHeap.extractMin();
		}
	}
	public static void main(String[] args){
		int N = 1000000;
		Integer[] arr = SortTestHelper.generateRandomArray(N,0,100000);
		SortTestHelper.testSort("com.heap.IndexMinHeapSort",arr);
	}
}
