package com.bishi;

import java.util.HashMap;

/**
 * 2018-5-10 13:03:57
 * 今天去快手面试，出了一道题
 * 给定一个整形的数组，这个数组里面的数，只出现一次或者两次，求出现一次的数组
 * 
 * 3,4,8,3,8，6   --》  4,6
 * @author weifeng
 *
 */
/**
 * 一个整形数组，有两个数出现了一次，其他的数只出现了两次，返回这两个数
 * @author weifeng
 *
 */
class kuaishou_Solution{
	private int findFirstBit1(int num){   //找到num表示的二进制数中的从左到右，出现的第一个1
		int n = 0;
		while(((num&1)== 0) && n<32){
			num = num >> 1;
			n++;
		}
		return n;
	}
	private boolean isBit1(int num,int index){
		num =  num >> index;
		return ((num & 1)==1);
	}
	public int[] getSingle(int[] arr){  //先求出来全部的异或的和，必然重复的数组的异或是为0，然后找到异或结果的第一个不为0的bit 索引
		int n = arr.length;             //按照索引，然后将数据分为两部分进行异或，异或后的结果就是最终的结果
		int xorres = 0;                //我们知道在一个数组中，如果仅有一个数是只出现一次，这个很好找
	    for (int i = 0; i< n; ++ i){
	    	xorres ^= arr[i];
	    }
	    int intindexOf1 = findFirstBit1(xorres);
	    int[] res = new int[2];
	    for (int j = 0; j< n; ++ j){
	      if(isBit1(arr[j], intindexOf1)){
	           res[0] ^= arr[j];
	      }else{
	           res[1] ^= arr[j];
	      }
	    }
	    return res;
	}
}
public class kuaishou {
	public int[] getSingle(int[] arr){
		int n = arr.length;
		
//		LinkedList<Integer> ll = new LinkedList<Integer>();
		HashMap<Integer,Integer> ll = new HashMap<Integer,Integer>();
		for(int i = 0 ;i<n;i++){
			if(ll.containsKey(arr[i])){
				ll.remove(arr[i]);
				continue;
			}
			ll.put(arr[i],i);
		}
		int j = 0;
		int nn = ll.size();
		int[] res = new int[nn];
		for( int resultint:ll.keySet()){
			res[j] = resultint;
			j++;
		}
		return res;
	}
	public static void main(String[] args){
		
		int[] arr = {3,8,8,4,3,6,6,5};
		kuaishou ss = new kuaishou();
		int []res = ss.getSingle(arr);
		for(int i:res){
			System.out.print(i);
			System.out.print(" ");
		}
		
	}
}

