package com.leetcode.muke;

/**
 * 给定一个数组nums，写一个函数，将数组中所有的0挪到数组的末尾，而维持其他所有非0元素的相对位置。
 * 举例： nums = [0, 1, 0, 3, 12]，函数运行后结果为[1, 3, 12, 0, 0]
 * @author weifeng
 *两个指针
 */
//既然两个不为零的数的相对位置不变，则需要移动不为零的数
class Solution_SwapZero0607{
	private void swap(int[] arr,int i,int j){
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	public void swapzero(int[] arr){
		int n = arr.length;
		int i = 0;
		int j = 0;   //齐头并进的两个指针
		while(j<n){
			if(arr[j] !=0){
				swap(arr,i,j);
				i++;
			}
			j++;
		}
	}
}
class Solution_moveZeros0517{
	private void swap(int[] arr,int i,int j){
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	public void moveZeros(int[] arr){   //把所有的零移动到右边
		int n = arr.length;
		int i = 0;
		int j = 0;
		while(j<n){
			if(arr[j]!=0){
				swap(arr,i,j);
				i++;
			}
			j++;
		}
	}
}
class Solution_moveZeros{
	private void swap(int[]arr,int i ,int j){
		int temp =arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	public void moveZeros(int[] arr){
		int n = arr.length;
		for(int i =0,j=0;j<n;j++){
			if(arr[j] == 0){
				swap(arr,i,j);
				i++;
			}
		}
	}
}
public class M01_MoveZeros {
    public void moveZeroes(int[] nums) {
        int n = nums.length;
        int i = 0;
        int j = 0;
        for (;j<n;j++){
            if(nums[j] != 0){
                int temp = nums[j];
                nums[j]=nums[i];
                nums[i]=temp;
                i++;
            }
        }
    }
public static void main(String args[]){

    int[] arr = {0, 1, 0, 3, 12};
    int[] arr1 = {1,0,3,0,12};

//    (new M01_MoveZeros()).moveZeroes(arr);
//
//    for(int i = 0 ; i < arr.length ; i ++)
//        System.out.print(arr[i] + " ");
//    System.out.println();
    Solution_moveZeros ss = new Solution_moveZeros();
    ss.moveZeros(arr);
    for(int i =0;i<arr.length;i++){
    	System.out.print(arr[i]);
    	System.out.print(" ");
    }
    System.out.println();
    ss.moveZeros(arr1);
    for(int i =0;i<arr1.length;i++){
    	System.out.print(arr1[i]);
    	System.out.print(" ");
    }
    System.out.println();
    Solution_moveZeros0517 sss = new Solution_moveZeros0517();
    sss.moveZeros(arr1);
    for(int i =0;i<arr1.length;i++){
    	System.out.print(arr1[i]);
    	System.out.print(" ");
    }
    
    System.out.println("sasasasa");
    int[] arrr = {3,0,1,2,0,3,0,2};
    Solution_SwapZero0607 ssss = new Solution_SwapZero0607();
    ssss.swapzero(arrr);
    for(int i:arrr){
    	System.out.println(i);
    }
    System.out.println("sasa");
    int[] arrrr = {3,0,1,2,0,3,2,2,0,9};
    Solution_SwapZero0607 sssss = new Solution_SwapZero0607();
    sssss.swapzero(arrrr);
    for(int i:arrrr){
    	System.out.println(i);
    }
}
}
