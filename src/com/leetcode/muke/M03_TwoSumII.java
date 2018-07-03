package com.leetcode.muke;
/**
 * 对撞指针，两个数的和。
 * 给定一个有序整形数组和一个整数target,在其中寻找两个元素
 * 使其和为target。返回这两个树的索引
 * @author weifeng
 *使用对撞指针
 *
 *注意是有序的哦，充分挖掘数据中的信息
 *因为是有顺序的所以可以用对撞指针
 */
public class M03_TwoSumII {
	public int[] twoSum(int[] numbers,int target){
		int i = 0;
		int j = numbers.length-1;
		int[] arr = new int[2];
        for (i = 0;i<j;){
            if((numbers[j] +numbers[i]) >target){
                j--;
                continue;
            }
            if((numbers[j]+numbers[i])<target){
                i++;
                continue;
            }
            if((numbers[j] + numbers[i]) ==target){
                arr[0]=i+1;
                arr[1]=j+1;
                return arr;
            }
        }
		return null;
	}
}
