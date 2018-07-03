package com.heap;

import com.test.SortTestHelper;

public class HeapSortI {
	private HeapSortI(){}
	//对整个arr数组使用heapsortI排序
	//HeapSortI，将所有的元素依次添加到堆中，在将所有的元素从堆中依次取出来，即完成了排序
	//创建堆和从堆中取元素，时间复杂度均是o(nlogn)
	public static void sort(Comparable[] arr){
		int n = arr.length;
		MaxHeap<Comparable> maxHeap = new MaxHeap<Comparable>(n);
		for(int i = 0;i<n;i++){
			maxHeap.insert(arr[i]);   //往堆里面插入元素
		}
		for(int i = n-1;i>=0;i--){
			arr[i] = maxHeap.extractMax();  //从堆里面取出数据，都是从大往小的取
		}
	}
	public static void main(String[] args){
		int N = 1000000;
		Integer[]  arr = SortTestHelper.generateRandomArray(N,0,100000);
		SortTestHelper.testSort("com.heap.HeapSortI",arr);
		return;
	}
}
 