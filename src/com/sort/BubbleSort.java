package com.sort;

public class BubbleSort {
	private BubbleSort(){};
	private static void swap(Comparable[] arr ,int i,int j){
		Comparable t = arr[i];
		arr[i] = arr[j];
		arr[j] = t;
	}
	public static void sortI(Comparable[] arr){
		int n = arr.length;
		for(int i = 0;i<n;i++){     //外层控制循环趟数，内环控制，每一趟排序多少次
			for(int j = 0;j<n-i-1;j++){
				if(arr[j].compareTo(arr[j+1]) > 0){
					Comparable temp = arr[j+1];
					arr[j+1] = arr[j];
					arr[j] = temp;
				}
			}
		}
				
	}
	public static void sort(Comparable[] arr){
		int n = arr.length;
		//每一趟的排序都将最大的元素放在了最后面，像气泡一样
		int newn; //使用newn进行优化，记录交换的位置	
		do{
			newn=0;
			for(int i =1;i<n;i++){
				if(arr[i-1].compareTo(arr[i])>0){
					swap(arr,i-1,i);
					newn = i;   //记录每一次交换的位置，进行优化
				}
			}
			n = newn;
		}while(newn > 0);
	}
	public static void main(String[] args){
		Comparable[] arr = {43,13,14,3,5,33,66};
		BubbleSort.sortI(arr);
		for (int i = 0;i<arr.length;i++){
			System.out.print(arr[i]);
			System.out.print(' ');
		}
	}
}
