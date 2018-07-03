package com.heap;

import com.test.SortTestHelper;

public class HeapSortII {
	private HeapSortII(){}
	//heapSortII,借助heapify过程创建
	//heapify过程：给定一个数组，构造成一颗完全二叉树
	//创建堆得过程时间复杂度为o(N),将所有元素从堆中取出来，时间复杂度为o(nlogn)
	public static void sort(Comparable[] arr){
		int n = arr.length;
		MaxHeap<Comparable> maxHeap = new MaxHeap<Comparable>(arr);
		for(int i = n-1;i>=0;i--){
			arr[i] = maxHeap.extractMax();
		}
	}
	public static void main(String[] args){
		int N = 1000000;
		Integer[] arr = SortTestHelper.generateRandomArray(N,0,100000);
		SortTestHelper.testSort("com.heap.HeapSortII",arr);
		return;
		
	}
}
