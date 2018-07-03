package com.leetcode.muke;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeMap;

/**
 * 给定两个数组nums，求两个数组的交集。
如nums1 = [1, 2, 2, 1], nums2 = [2, 2]
结果为[2, 2]
出现的顺序可以是任意的

数组的交集 
 * @author weifeng
 *
 */
class Solution_intersectionII0608{
	public int[] getInter(int[] arr1,int[] arr2){
		HashMap<Integer,Integer> hs = new HashMap<Integer,Integer>();
		LinkedList<Integer> ll = new LinkedList<Integer>();
		int n1 = arr1.length;
		int n2 = arr2.length;
		for(int i = 0;i<n1;i++){
			hs.put(arr1[i],1);
		}
		for(int i = 0;i<n2;i++){
			if(hs.containsKey(arr2[i])){
				ll.add(arr2[i]);
			}
		}
		int n = ll.size();
		int[] res= new int[n];
		int i = 0;
		for(int re:ll){
			res[i] = re;
			i++;
		}
		return res;
	}
}
public class M07_IntersectionOfTwoArrays {
	public int[] intersect(int[]nums1,int[] nums2){
		TreeMap<Integer,Integer> record = new TreeMap<Integer,Integer>();
		for(int num:nums1){
			if(!record.containsKey(num)){
				record.put(num,1);
			}
			else{
				record.put(num,record.get(num) +1);
			}
		}
		ArrayList<Integer> result = new ArrayList<Integer>();
		for(int num:nums2){
			if(record.containsKey(num) && record.get(num)>0){  //记录里面有这个数据，然后个数还不是等于0,所以还需要继续添加
				result.add(num);
				record.put(num,record.get(num)-1);
			}
		}
		int [] ret = new int[result.size()];
		int index = 0;
		for(Integer num:result){
			ret[index++] = num;
		}
		return ret;
	}
    public int[] intersectionmine(int[] nums1, int[] nums2) {
        int[] resarr;
        LinkedList<Integer> mylist = new LinkedList<Integer>();
        LinkedList<Integer> reslist = new LinkedList<Integer>();  //结果列表中进行
        for(int i = 0;i<nums1.length;i++){
            mylist.add(nums1[i]);
        }
        for(int j = 0;j<nums2.length;j++){
            if(mylist.contains(nums2[j])){
                reslist.add(nums2[j]);
                mylist.remove(mylist.indexOf(nums2[j]));   //这里的num2[j]会当成索引
            }
        }
        resarr = new int[reslist.size()];
        for(int k = 0;k<reslist.size();k++){
            resarr[k] =reslist.get(k);
        }
        return resarr;
    }
	private static void printArr(int[] arr){
		for(int e:arr){
			System.out.print(e + " ");
		}
		System.out.println();
	}
	public static void main(String[] args){
		int[] nums1 = {1};
		int[] nums2 = {1};
		int [] res = (new M07_IntersectionOfTwoArrays()).intersectionmine(nums1,nums2);
		printArr(res);
		
		System.out.println("0608");
		Solution_intersectionII0608 ss = new Solution_intersectionII0608();
		int[] arr1 = {1,2,2,1};
		int[] arr2 = {2,2};
		for(int i:ss.getInter(nums1,nums2)){
			System.out.print(' ');
			System.out.print(i);
		}
		System.out.println();
		System.out.println("end");
	}
}
