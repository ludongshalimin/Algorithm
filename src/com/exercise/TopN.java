package com.exercise;
//在一亿个数中找出前K大的数，
//思路是以K个元素,构造一个最小堆，
//如果当前的元素比，堆顶的元素小，则略过，如果比堆顶中的元素大，则替换堆顶的元素，然后维护最小堆
//直到遍历完，所有的数据，最后堆的K个元素就是这个数组中的前K大的元素
class Solution_TopN0606{
	public int[] getTop(int[] arr,int k){
		int n = arr.length;
		int[] topk = new int[k];
		for(int i = 0;i<k;i++){
			topk[i] = arr[i];
		}
		//构造一个最小堆
		for(int i = (k-1-1)/2;i>=0;i--){
			shiftDown(topk,k,i);      //构造一个堆的shiftDown操作，k个节点，以i为节点进行shiftDown操作
		}
		for(int i = k;i<n;i++){
			if(topk[0]<arr[i]){
				topk[0] = arr[i];
				shiftDown(topk,k,0);
			}
		}
		return topk;
	}
	private void swap(int[] arr,int i,int j){
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	private void shiftDown(int[] arr,int n,int k){
		while((2*k+1)<n){
			int j = 2*k +1;
			if(j+1<n && arr[j]>arr[j+1]){  //作为最小堆，要拿最小的数进行比较
				j++;
			}
			if(arr[k]<arr[j])break;  //表明已经在合适的位置上了，不需要再进行shiftdown操作了
			swap(arr,k,j);
			k = j;
		}
	}
}
//在一亿个正整数中求出前1000个最大的，时间复杂度是nlogm
class Solution_TopN{
	public int[] getTopM(int[] arr,int m){
		int n = arr.length;
		int[] res = new int[m];
		for(int i = 0;i<m;i++){
			res[i] = arr[i];
		}
		for (int i =(m-1-1)/2;i>=0;i--){   //首先将这m个元素组成最小堆
			shiftDown(res,m,i);    //m个节点以i为父节点进行shiftdown操作
		}
		for(int i=m;i<n;i++){
			if(arr[i] <= res[0]){
				continue;
			}else{
				res[0] = arr[i];
				shiftDown(res,m,0);
			}
		}
		return res;
	}
	private void shiftDown(int[] arr,int m,int k){ //m个节点，以k为父节点进行shiftdown操作
		while(2*k+1<m){
			int j = 2*k+1;
			if(j+1 < m && arr[j]>arr[j+1]){
				j++;
			}
			if(arr[k] < arr[j]) break;
			swap(arr,k,j);
			k = j;
		}
	}
	private void swap(int[] arr,int i ,int j){
		int temp = arr[i];
		arr[i] =arr[j];
		arr[j] = temp;
	}
}




//在N个元素中，选出前M个最大的元素
//使用M个元素，维护一个最小堆
public class TopN {
	int[] res ={};
	public void getTopN(int[] arr,int n){
		int len = arr.length;
		res = new int[n];
		for(int i = 0;i<n;i++){   //利用arr[0]...arr[n-1]原地堆排序构建一个最小堆
			res[i]= arr[i];
		}
		for(int i = (n-1-1)/2;i>=0;i--){  //构建一个最小堆
			shiftDown(res,n,i);  //n个元素，以i为父节点进行shiftdown操作
		}
		for(int i=n;i<len;i++){
			if(res[0]<arr[i]){
				res[0] = arr[i];
				shiftDown(res,n,0);
			}
		}
		
	}
	private void shiftDown(int[] arr,int n ,int k){   //n个元素以k为父节点进行shiftdown操作
		
		while(2*k+1<n){
			int j = 2*k+1;   //左孩子
			if(j+1<n&&arr[j+1]<arr[j]){  //最小的元素进行交换
				j++;
			}
			if(arr[k]<arr[j])break; //父节点已经小于两个子节点，已经在最小的位置的合适位置了，所以不用动了
			swap(arr,j,k);
			k=j;
		}
	}
	private void swap(int[] arr,int a,int b){
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;		
	}
	public int[] getTopNN(int[] arr,int n){
		if(res.length == 0){
			getTopN(arr,n);
		}
		return res;
	}
	public static void main(String[] args){
		int[] arr = {3,5,7,8,11,10,3,4,5,6,8,4,5,2,9,11,34,55,99};
		int []ress ;
		TopN ss = new TopN();
		ress = ss.getTopNN(arr,5);
		for(int i = 0;i<ress.length;i++){
			System.out.println(ress[i]);
		}
		
		System.out.println("lalal");
		Solution_TopN s = new Solution_TopN();
		int[] res = s.getTopM(arr,5);
		for(int i = 0;i<res.length;i++){
			System.out.println(res[i]);
		}

		System.out.println("sasa");
		Solution_TopN0606 sss = new Solution_TopN0606();
		int[] arrr = {4,8,2,3,5,1,0,3,2,9,9};
		int[] ress1 = sss.getTop(arrr,3);
		for(int i:ress1){
			System.out.println(i);
		}
	}
}
