package com.exercise;

import com.sort.QuickSort;

//o(N)的复杂度求解数组中第N大的元素，注意是第N大的元素
//为什么是O(N)：因为数据O（N） +O（n/2) +O(n/4)......=O(2n)  所以是O(N)方的复杂度
//第N大(小)的元素
//Quick Sort的思路求数组中第n大元素,使用最小堆
class Solution_NMax0716{
	private void swap(int[] arr,int i ,int j){
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	private int partition(int[] arr,int l,int r ){
		swap(arr,l,((int)Math.random()*(r-l+1))+l);
		int v = arr[l];
		int i = l+1;
		int j = r;
		while(true){
			while(i<=r && arr[i]<v)i++;
			while(j>=l+1 && arr[j]>v)j--;
			if(i>j)break;
			swap(arr,i,j);
			i++;
			j--;
		}
		swap(arr,l,j);
		return j;
	}
	private int sort(int[]arr,int l,int r,int m){
		int p = partition(arr,l,r);
		if(p == m){
			return arr[p];
		}
		if(arr[p] >arr[m]){
			return sort(arr,l,p-1,m);  // 对于pd的位置已经找到了
		}else{
			return sort(arr,p+1,r,m);
		}
		
	}
	public int sort(int[] arr,int m){
		int n = arr.length;
		return sort(arr,0,n-1,m);
	}
}
class Soulution_NMax0606{   //求一个数组中的第k大的数， 如果排序，则是o(nlogn+k)
	private void swap(int[] arr,int i,int j){
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	private int partition(int[] arr,int l,int r){
		int n = arr.length;
		swap(arr,l,(int)(Math.random()*(r-l+1))+l);
		int v = arr[l];
		int i = l+1;
		int j = r;
		while(true){
			while(i<=r&&arr[i]<v)i++;
			while(j>=l+1&&arr[j]>v)j--;
			if(i>j) break;
			swap(arr,i,j);
			i++;
			j--;
		}
		swap(arr,l,j);
		return j;
	}
	private int getKMin(int[] arr,int l, int r,int k){
		if(l==r)return arr[l];
		int p = partition(arr,l,r);
		if(p==k)return arr[p];
		else if(k<p){
			return getKMin(arr,l,p-1,k);
		}else{
			return getKMin(arr,p+1,r,k);
		}
	}
	public int getKMin(int[] arr,int k){
		return getKMin(arr,0,arr.length-1,k);
	}
}
public class NMaxMinNum {
	private NMaxMinNum(){}
	
	private static void swap(Comparable[] arr,int i,int j){
		Comparable t = arr[i];
		arr[i] = arr[j];
		arr[j] = t;
	}
	//对arr[l..r]部分进行partition操作
	//返回p，使得arr[l...p-1]<arr[p];arr[p+1] > arr[p]
	private static int partition(Comparable[] arr, int l,int r){
		//随机的在arr[l..r]范围内，选择一个数值作为标定点		
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
	//选择第N小的数
	private static Comparable selectNMin(Comparable[] nums,int l,int r,int k){
		if(l == r){
			return nums[l];
		}
		//partition之后，nums[p]的正确位置就在索引p上
		int p = partition(nums,l,r);
		if(k == p){
			return nums[p];
		}
		else if( k < p){   //只需要在nums[l..p-1]中找第k小的元素即可
			return selectNMin(nums,l,p-1,k);
		}else{
			return selectNMin(nums,p+1,r,k); //如果k>p，则需要在nums[p+1...r]中找第K小元素
		}
	}
	//从索引到N的元素中选择第K小的元素
	public static Comparable selectNMin(Comparable[] nums,int n,int k){
		assert k >= 0 && k < n;
		return selectNMin(nums,0,n-1,k);
	}
//	//选择第K大的数，相当于选择n-k小的数，或者变换partition
//	public static Comparable selectNMax(Comparable[] nums,int n,int k){
//		assert k >= 0 && k < n;
//		int kk = n-k;
//		return selectNMin(nums,0,n-1,kk);
//	}
	public static void main(String[] args){
		Integer[] arr = new Integer[10];
		for(int i = 0;i<10;i++){
			//arr[i] = new Integer((int)(Math.random()*10));
			arr[i] = new Integer(i);
		}

		swap(arr,1,3);
		swap(arr,5,8);
		for(int j = 0;j<10;j++){
			System.out.print(arr[j] + " ");
		}
		System.out.println();
		
			
		QuickSort.sort(arr);
		for(int j = 0;j<10;j++){
			System.out.print(arr[j] + " " );
			
		}
		System.out.println();
		System.out.println(selectNMin(arr,arr.length,3));
//		System.out.println(selectNMax(arr,arr.length,3));
		
		System.out.println("hahah");
		int[] arrr = {3,8,2,1,9,0,5,4};
		Soulution_NMax0606 ss = new Soulution_NMax0606();
		for(int i = 0;i<arrr.length;i++){
			System.out.println(ss.getKMin(arrr,i));
		}
		System.out.println("0716");
		Solution_NMax0716 sss = new Solution_NMax0716();
		for(int i = 0;i<arrr.length;i++){
			System.out.println(sss.sort(arrr,i));
		}
	}
}
