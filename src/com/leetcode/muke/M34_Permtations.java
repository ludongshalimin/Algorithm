package com.leetcode.muke;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 排列问题
 * 给定一个整型数组，其中的每一个元素都各不相，返回这些元素所有排列的可能。
如对于 [1, 2, 3]
返回 [ [1,2,3] , [1,3,2] , [2,1,3] ,
 [2,3,1] , [3,1,2] , [3,2,1] ]

 * @author weifeng
 *树形问题
 *Perms( nums[0…n-1] ) = {取出一个数字} + perms( nums[{0…n-1} - 这个数字] )

 */
public class M34_Permtations {
	private ArrayList<List<Integer>> res;
	private boolean[] used;
	public List<List<Integer>> permute(int[]nums){
		res = new ArrayList<List<Integer>>();
		if(nums == null || nums.length == 0)return res;
		used = new boolean[nums.length];
		LinkedList<Integer> p = new LinkedList<Integer>();
		generatePermutation(nums,0,p);
		return res;
	}
	//p中保存了一个有index-1个元素的排列
	//向这个排列的末尾添加第index个元素，获得一个有index的排列
	private void generatePermutation(int[]nums,int index,LinkedList<Integer> p){
		if(index == nums.length){                      //已经遍历到头了，找到了一个排列
			res.add((LinkedList<Integer>)p.clone());  //p是一个链表，复制一个链表
			return;
		}
		for(int i = 0;i<nums.length;i++){
			if(!used[i]){    //used[i]来判断第i个元素是否被使用过了
				used[i] = true;
				p.addLast(nums[i]);
				generatePermutation(nums,index+1,p);  //递归要回去，但是我们设置的变量也要回去，进行维护
				p.removeLast();   //不是退还一个，而是把所有的标记的退还回去，递归的退还
				used[i] = false;
			}
		}
		return;
	}
}
