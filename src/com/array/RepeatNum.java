package com.array;
/**
 * 问题描述：数组a[n],1~n-1这N-1个数存放在a[n]中，某个数重复了1次，写出一个函数
 * 找出被重复的数字，要求每个数组元素只能访问1次，并且不能用辅助存储空间
 * @author weifeng
 * 注意挖掘题目中的信息：1~n-1个数，然后数组是n的存储空间，所以的是有一个数据是重复的
 * 所以如何找到这个重复的数据
 * 
 * 1：累加求和
 * 主要是利用了 这n个数和索引的关系
 *
 */
class Solution_repeatNum{
	public int repeatNum(int[] arr){
		int n = arr.length;
		int res  = 0;
		for(int i = 0;i<n;i++){   //所以最后只剩下了一个重复的数字减去-n
			res += arr[i]-(i+1);
		}
		return res+n;
	}
}
public class RepeatNum {
	public static int findRepeatNum(int[] arr){
		int n = arr.length;
		int sumTotal = 0;
		for(int i = 0;i<n;i++){
			sumTotal +=((i+1)-arr[i]);
		}
		return n-sumTotal;
	}
	public static void main(String[] args){
		int[] arr = {5,6,7,9,4,2,3,1,4,8};
		System.out.println(findRepeatNum(arr));
		Solution_repeatNum ss = new Solution_repeatNum();
		System.out.println("hahah");
		System.out.println(ss.repeatNum(arr));
	}
}
