package com.exercise;

import java.util.Arrays;
//什么是逆序对，逆序对的个数
//求解逆序对，衡量数组的有序程度
//mergesort的思路求解逆序对o(nlogn)
//重点是merge过程，merge的两个阶段都是排好序的


//逆序对的个数
class Solution_InverseCount_0716{
	private int merge(int[]arr,int l,int mid,int r){  //表示的是以[l..r]
		int[] aux = Arrays.copyOfRange(arr,l,r+1);
		int i = l;
		int j = mid+1;
		int reverse = 0;
		for(int k = l;k<=r;k++){
			if(i>mid){         // 前面arr[i]一直小于arr[j]s
				arr[k] = aux[j-l];
				j++;
			}
			else if(j>r){
				arr[k] = aux[i-l];   //前面的arr[j]一直小于arr[i]
				i++;
			}
			else if(aux[i-l]>aux[j-l]){ //构成了一个逆序，因为两边都是排好序的
				arr[k] = aux[j-l];
				j++;
				reverse += (mid-i+1);
			}else{
				arr[k] = aux[i-l];
				i++;
			}
		}
		return reverse;
	}
	private int inverseCount(int[]arr,int l,int r){
		if(l>=r)return 0;    //递归总要有终止条件的
		int mid = l + (r-l)/2;
		int res = 0;
		res += inverseCount(arr,l,mid);
		res += inverseCount(arr,mid+1,r);
		res += merge(arr,l,mid,r);

		return res;
	}
	public int inverseCount(int[] arr){
		int n = arr.length;
		int[] aux = Arrays.copyOfRange(arr,0,n);
		return inverseCount(aux,0,n-1);
	}
	//最简单暴力的方式
	public int inverseCountI(int[] arr){
		int n = arr.length;
		int inverseCount = 0;
		for(int i = 0;i<n;i++){
			for(int j = 0;j<i;j++){
				if(arr[j] > arr[i]){
					inverseCount ++;
				}
			}
		}
		return inverseCount;
	}
}
class Solution_InverseCount_0607{
	public int inverseCountI(int[] arr){
		int n = arr.length;
		int count = 0;
		for(int i = 0;i<n;i++){
			for(int j = 0;j<i;j++){
				if(arr[j]>arr[i]){
					count++;
				}
			}
		}
		return count;
	}
	private int mergeCount(int[] arr,int l,int mid,int r){
		int[] aux = Arrays.copyOfRange(arr,l,r+1);
		int i = l;
		int j = mid + 1;
		int count =0;
		for(int k = l;k<=r;k++){
			if(i > mid){
				arr[k] = aux[j-l];
				j++;
			}else if(j>r){
				arr[k] = aux[i-l];
				i++;
			}else if(aux[i-l]<=aux[j-l]){
				arr[k] = aux[i-l];
				i++;
			}else{  //aux[i-l] > aux[j-l]  ,那么aux[i-l]之后的数据都是大于aux[j-l]
				arr[k] = aux[j-l];
				j++;
				count += mid -i +1;
			}
		}
		return count;
	}
	private int inverseCount(int[] arr,int l,int r){
		if(l>=r)return 0;
		int mid = l+(r-l)/2;
		int count = 0;
		count += inverseCount(arr,l,mid);
		count += inverseCount(arr,mid+1,r);
		count += mergeCount(arr,l,mid,r);
		return count;
	}
	public int inverseCount(int[] arr){
		return inverseCount(arr,0,arr.length-1);
	}
}
class Solution_NInverse{
	//最简单的套路o(n2)遍历
	public int countInverse(int[] arr){
		int n = arr.length;
		int res = 0;
		for(int i = 0;i<n;i++){//当前的数字
			for(int j=i+1;j<n;j++){
				if(arr[i] > arr[j]){
					res++;
				}
			}
		}
		return res;
	}
	
}
public class InverseOrderPair {
	//归并的时候，左右两边均已经排好序了
	private static long merge(Comparable[] arr,int l,int mid,int r){
		Comparable[] aux = Arrays.copyOfRange(arr,l,r+1);
		long res = 0L;
		int i = l;
		int j = mid + 1;
		for(int k = l;k<=r;k++){
			if(i > mid){
				arr[k] = aux[j-l];
				j++;
			}else if(j > r){
				arr[k] = aux[i-l];
				i++;
			}else if( aux[i-l].compareTo(aux[j-l]) <= 0){
				arr[k] = aux[i-l];
				i ++;
			}else{
				arr[k] = aux[j-l];
				j++;
				//此时，因为右半部分K所指的元素小
				//这个元素和左半部分的所有未处理的元素构成了逆序数对
				//左半部分此时未处理的元素的个数为mid-i+1;
				res += mid - i + 1;
			}
		}
		return res;
	}
	//对arr[l...r]求逆序对的个数
	private static long InverseCount(Comparable[] arr,int l ,int r){
		if(l >= r){
			return 0L;
		}
		int mid = l + (r-l)/2;
		long res1 = InverseCount(arr,l,mid);  //求出arr[l...mid]范围的逆序对
		long res2 = InverseCount(arr,mid+1,r); //求出arr[mid+1,r]范围的逆序对
		return res1 + res2 + merge(arr,l,mid,r);
	}
	public static long InverseCount(Comparable[] arr){
		int n = arr.length;
		return InverseCount(arr,0,n-1);
	}
	public static void main(String[] args){
		Integer[] arr = {4,9,12,3,6};
		int[]arrr = {4,9,12,3,6};
		System.out.println(InverseCount(arr));
		Solution_NInverse ss = new Solution_NInverse();
		System.out.println(ss.countInverse(arrr));
		
		Solution_InverseCount_0607 sss = new Solution_InverseCount_0607();
		System.out.println(sss.inverseCountI(arrr));
		System.out.println(sss.inverseCount(arrr));
		
		System.out.println("0716");
		int[] arrrr = {4,9,12,3,6};
		Solution_InverseCount_0716 ssss = new Solution_InverseCount_0716();
		System.out.println(ssss.inverseCount(arrrr));
		System.out.println(ssss.inverseCountI(arrrr));
	}
}
