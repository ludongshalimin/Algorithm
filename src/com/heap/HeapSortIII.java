package com.heap;

import com.test.SortTestHelper;

//不使用额外的最大堆，直接在数组上进行原地排序
//借用heapify过程构建一个堆

//使用原地堆进行排序，首先是要建立一个堆，其次要在堆上进行排序操作
//parent(i) = (i-1)/2;
//leftchild(i) = 2*i+1;
//rightchile(i) = 2*i+2;
public class HeapSortIII {
	private HeapSortIII(){}
	private static void swap(Object[] arr,int i ,int j){
		Object t = arr[i];
		arr[i] = arr[j];
		arr[j] = t;
	}
	public static void sort(Comparable[] arr){
		int n = arr.length;
		
		//堆从索引0开始
		//(从最后的索引-1)/2,开始
		//最后一个元素的索引 = n-1
		
		//构建一个堆
		for(int i = (n-1-1)/2;i>=0;i--){
			shiftDown(arr,n,i);
		}
		//进行原地堆排序，i= n-1是最后的索引
		for(int i=n-1;i>0;i--){
			swap(arr,0,i);
			shiftDown(arr,i,0); //将剩下的元素继续组建成一棵堆
		}
	}
	//对数组n个节点，k作为父节点进行shiftdown
	private static  void shiftDown(Comparable[] arr,int n,int k){
		while(2*k + 1 < n){ //左孩子，
			int j= 2*k+1;
			if(j+1<n&&arr[j+1].compareTo(arr[j]) > 0){
				j +=1;
			}
			//arr[j]存放的是孩子中的极大值者
			if(arr[k].compareTo(arr[j]) > 0)break;
			swap(arr,k,j);
			k=j;
		}
	}
	public static void main(String[] args){
		int N = 1000000;
		Integer[] arr = SortTestHelper.generateRandomArray(N,0,100000);
		SortTestHelper.testSort("com.heap.HeapSortIII",arr);
	}
}
