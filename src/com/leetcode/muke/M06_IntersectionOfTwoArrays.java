package com.leetcode.muke;

import java.util.HashSet;
import java.util.TreeSet;

/**
 * 给定两个数组nums，求两个数组的公共元素。
如nums1 = [1, 2, 2, 1], nums2 = [2, 2]
结果为[2]
结果中每个元素只能出现一次
出现的顺序可以是任意的

公共元素
 * @author weifeng
 *
 */
class Solution_intersection0608{
	public int[] inter(int[] arr1,int[] arr2){
		HashSet<Integer> hm = new HashSet<Integer>();
		HashSet<Integer> res = new HashSet<Integer>();
		int n1 = arr1.length;
		int n2 = arr2.length;
		for(int i = 0;i<n1;i++){
			hm.add(arr1[i]);
		}
		for(int i = 0;i<n2;i++){
			if(hm.contains(arr2[i])){
				res.add(arr2[i]);
			}
		}
		int n = res.size();
		int[] resint = new int[n];
		int i = 0;
		for(int re:res){
			resint[i] = re;
			i++;
		}
		return resint;
	}
}
class Solution_intersection{
	public int[] intersection(int[] nums1,int[] nums2){
		HashSet<Integer> hs = new HashSet<Integer>();
		HashSet<Integer> res = new HashSet<Integer>();
		int n1 = nums1.length;
		int n2 = nums2.length;
		for(int i = 0;i<n1;i++){
			hs.add(nums1[i]);
		}
		for(int j =0;j<n2;j++){
			if(hs.contains(nums2[j])){
				res.add(nums2[j]);
			}
		}
		int n = res.size();
		int[] re = new int[n];	
		int i = 0;
		for(int result:res){
			re[i] = result;
			i++;
		}
		return re;
	}
}
public class M06_IntersectionOfTwoArrays {
	public int[] intersection(int[]nums1,int[]nums2){
		TreeSet<Integer>record = new TreeSet<Integer>();
		for(int num:nums1){
			record.add(num);
		}
		TreeSet<Integer>resultSet = new TreeSet<Integer>();
		for(int num:nums2){
			if(record.contains(num)){
				resultSet.add(num);
			}
		}
		int [] res = new int[resultSet.size()];
		int index = 0;
		for(Integer num:resultSet){
			res[index++] = num;
		}
		return res;
		
	}

	private static void printArr(int[] arr){
		for(int e:arr){
			System.out.print(e + " ");
			
		}
		System.out.println();
	}
	public static void main(String[] args){
		int[] nums1 = {1,2,2,2,3};
		int[] nums2 = {1,2,2,2,3};
		int[] res = (new M06_IntersectionOfTwoArrays()).intersection(nums1,nums2);
		printArr(res);
		Solution_intersection ss = new Solution_intersection();
		for(int re:ss.intersection(nums1,nums2)){
			System.out.print(' ');
			System.out.println(re);
		}
		System.out.println("0608");
		Solution_intersection0608 sss = new Solution_intersection0608();
		for(int re:sss.inter(nums1,nums2)){
			System.out.print(re);
			System.out.print(' ');
		}
		System.out.println("end");
	}
}
