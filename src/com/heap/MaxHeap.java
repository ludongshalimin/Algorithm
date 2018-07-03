package com.heap;
//实现一个最大堆
/*
 * 什么是最大堆
 * 
 * 1：任何一个节点都不大于它的父节点
 * 2：是一棵完全二叉树，除最后一层节点外，其它层节点个数最大，节点集中在左侧
 * 
 * 利用数组存储二叉堆,索引0位置没有用
 * parent(i) =i/2;
 * left child(i) = 2*i;
 * right child(i) = 2*i+1;
 * 
 * 这里尤其要区分是从0开始的还是从1开始的
 */
public class MaxHeap <Item extends Comparable> {
	protected Item[] data;
	protected int count;    //
	protected int capacity; //容量，最大可以盛下的元素的个数
	
	//构造函数，构造 一个空堆，可容纳capacity个元素
	public MaxHeap(int capacity){
		data = (Item[]) new Comparable[capacity + 1];
		count = 0;
		this.capacity = capacity;
	}
	//传递一个数组的引用然后，通过这个数组创建一个堆
	//通过给定一个数组创建一个最大堆
	//该构造过程的时间复杂度为o(n)
	public MaxHeap(Item arr[]){
		int n = arr.length;
		
		data = (Item[]) new Comparable[n+1];
		capacity = n;
		for(int i = 1;i<n;i++){
			data[i] = arr[i];
		}
		count = n;
		for(int i = count/2;i>=1;i--){ //对每一个父节点执行shiftDown操作，构造一个堆
			shiftDown(i);
		}
	}
	//返回堆中的元素的个数
	public int size(){
		return count;
	}
	//返回一个布尔值，表示堆中是否为空
	public boolean isEmpty(){
		return count == 0;
	}
	//向最大堆中插入一个新的元素item
	public void insert(Item item){     //插入一个元素
		assert count + 1 <= capacity;
		data[count + 1] = item;
		count ++;
		shiftUp(count);   //插入的过程中调用shiftup，维护一个堆
	}
    //从最大堆中取出堆顶元素，即堆中所存储的最大数据，取出一个元素后需要重新维护一个堆
	public Item extractMax(){     //堆顶弹出一个元素
		assert count > 0;
		Item ret = data[1];
		swap(1,count);
		count --;
		shiftDown(1);
		return ret;
	}
	//获取最大堆中的堆顶元素，但是不取走
	public Item getMax(){
		assert( count > 0);
		return data[1];
	}
	//交换堆中索引为i 和j的两个元素
	private void swap(int i,int j){
		Item t = data[i];
		data[i] = data[j];
		data[j] = t;
	}
	//最大堆的核心函数
	//从堆中插入一个元素，为了维护最大堆得性质，需要进行对最大堆进行维护
	private void shiftUp(int k){
		while(k > 1 && (data[k/2]).compareTo(data[k]) < 0){
			swap(k,k/2);
			k /=2;
		}
	}
	//取出堆中的一个元素，只能取出根节点的元素，优先级最大的元素，
	//把整个堆得最后一个元素，放在根节点的位置，然后一步一步向下转换，谁大就跟谁换
//	 * parent(i) =i/2;
//	 * left child(i) = 2*i;
//	 * right child(i) = 2*i+1;
	private void shiftDown(int k){    //对以索引为k的父节点进行shiftdown操作
		while( 2*k <= count){   //左孩子，不超过最大的索引
			int j = 2*k; 
			if(j+1 <= count && data[j+1].compareTo(data[j]) > 0){
				j++;
				//data[j]是data[2*k] 和 data[2*k+1]中的最大值
			}
			if(data[k].compareTo(data[j]) >= 0)break;
			swap(k,j);
			k=j;
		}
	}
	
	//测试最大堆
	public static void main(String[] args){
		MaxHeap <Integer> maxheap = new MaxHeap<Integer>(100);
		int N = 50; // 堆中元素的个数
		int M = 100; //堆中的元素的取值范围 【0，m】
		for(int i = 0;i<N;i++){
			maxheap.insert(new Integer((int)(Math.random() * M)));
		}
		Integer[] arr = new Integer[N];
		//将maxheap中的数据逐渐使用extractMax取出来
		//取出来的数据应该是按照从大到小的顺序取出来的
		
		for(int i = 0;i<N;i++){
			arr[i] = maxheap.extractMax();
			System.out.print(arr[i] + " ");
		}
		System.out.println();
		
		//确保arr数组是从大道小排列的
		for(int i = 1;i<N;i++){
			assert arr[i-1] >=arr[i];
		}
	}
}
