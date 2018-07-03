package com.leetcode.muke;
/*
 * 颜色排序
 * n个有颜色的对象，按照颜色：红色，白色，蓝色排序。0.1.2分别代表颜色
 * 有一种三路快排的思路
 */
class Solution_SortColor0517{
	private void swap(int[] arr,int i ,int j){
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	public void sortcolor(int[] arr){
		int n = arr.length;
		int l = 0;
		int r = n-1;
		int i = 0;
		while(i<r){
			if(arr[i] == 0){
				swap(arr,i,l);
				l++;
				i++;
			}else if(arr[i] == 1){
				i++;
			}else if(arr[i] == 2){
				swap(arr,i,r);    //和后面进行的交换不知道是什么情况，所以这个地方i不能进行++
				r--;
			}
		}
	}
}
class Solution_sortColour{
	private void swap(int[] arr,int i,int j){
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	//红色 白色 蓝色  0  1  2
	public void sortColour(int[] arr){
		int n = arr.length;
		int redIndex =0;
		int blueIndex = n-1;
		int startIndex = 0;
		while(startIndex<blueIndex){
			if(arr[startIndex] == 0){    //红色 
				swap(arr,redIndex,startIndex);
				redIndex ++;
				startIndex++;
			}else if(arr[startIndex] == 1){  //白色
				startIndex++;
			}else if(arr[startIndex] == 2){  //蓝色
				swap(arr,blueIndex,startIndex);
				blueIndex--;
			}else{
			}
			
		}
	}
}
public class M02_SortColors {
	private static void swap(int[] nums,int i ,int j){
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
	public void sortColors(int[] nums){
		int zero = -1;
		int two = nums.length;
		for(int i = 0;i<two;){
			if(nums[i] == 1){     //1代表的是白色
				i++;
			}else if(nums[i] ==2){  //2代表的是蓝色
				two--;
				swap(nums,i,two);
			}
			else{                //如果是0：红色
				zero++;
				swap(nums,i,zero);
				i++;
			}
		}
		
	}
	public static void main(String[] args){
		int[] arr = {0,2,1,2,0,1,2,2,2,1,1,0,0};
		Solution_sortColour ss = new Solution_sortColour();
		ss.sortColour(arr);
		for(int i = 0;i<arr.length;i++){
			System.out.print(' ');
			System.out.print(arr[i]);
		}
		System.out.println();
		int[] arr1 = {0,2,1,2,0,1,2,2,2,1,1,0,0};
		Solution_SortColor0517 sss = new Solution_SortColor0517();
		ss.sortColour(arr1);
		for(int i = 0;i<arr.length;i++){
			System.out.print(' ');
			System.out.print(arr[i]);
		}
	}
}
